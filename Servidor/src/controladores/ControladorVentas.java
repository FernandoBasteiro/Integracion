package controladores;

import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import daos.EmpleadoDAO;
import daos.ProductoDAO;
import daos.VentaDAO;
import dto.EmpleadoDTO;
import dto.ItemVentaDTO;
import dto.VentaDTO;
import enumeraciones.EstadoFactura;
import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;
import enumeraciones.Puesto;
import enumeraciones.TipoFactura;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;
import negocio.Empleado;
import negocio.ItemVenta;
import negocio.Producto;
import negocio.Venta;
import negocio.VentaEfectivo;
import negocio.VentaTarjetaCredito;
import negocio.VentaTarjetaDebito;

public class ControladorVentas {
	
	private static ControladorVentas instance;
	
	public ControladorVentas() {
		super();
	}
	
	public static ControladorVentas getInstance(){
		if(instance == null){
			instance = new ControladorVentas ();
		}
		return instance;
	}
	
	public VentaDTO generarVenta(EmpleadoDTO c, VentaDTO v) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		if (ControladorEmpleados.getInstance().estaLogueado(c)) {
			if (c.getPuesto().getId() >= Puesto.CAJERO.getId()) {
				
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(c.getLegajo());
				
				ArrayList<ItemVenta> items = new ArrayList<ItemVenta>();
				for (ItemVentaDTO id : v.getItems()) {
						ArrayList<Producto> prods = ProductoDAO.getinstance().getProductoByCodigo(id.getProducto().getCodigo());
						if (prods != null) {
							Producto p = prods.get(0);
							p.getStock().descontarStock(id.getCantidad());
							ItemVenta i = new ItemVenta(p, p.getPrecio(), id.getCantidad());
							items.add(i);
						}
				}
				
				//Ver tipo de VENTA
				switch (v.getMedioDePago()) {
					case EFECTIVO:
						v = generarVentaEfectivo(v, items, emp);
						
						break;
					case TARJETA_DEBITO:
						//TODO llamar al BANCO
						v.setAprobada(true);
						v.setNroOperacion(123456);
						if (v.getAprobada()) {
							v = generarVentaTD(v, items, emp);
						}
						else throw new ExcepcionProceso("Error TD. Venta no aprobada.");
						break;
					case TARJETA_CREDITO:
						//TODO llamar a CREDITOS
						v.setAprobada(true);
						v.setNroOperacion(456789);
						if (v.getAprobada()) {
							v = generarVentaTC(v, items, emp);
						}
						else throw new ExcepcionProceso("Error TC. Venta no aprobada.");
						break;
					default: 
						throw new ExcepcionProceso("Medio de pago inválido.");
				}
				return v;
			}
			else throw new ExcepcionProceso("Error al generar una venta.");
		}
		else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción.");
	}	
	
	private VentaDTO generarVentaEfectivo(VentaDTO vd, ArrayList<ItemVenta> items, Empleado emp) throws ExcepcionProceso {			
		VentaEfectivo vta = new VentaEfectivo(LocalDate.now(), items, emp, EstadoVenta.COBRADA, vd.getTotal(), 
				vd.getMontoRecibido(), vd.getVuelto(), vd.getTipoFact(), vd.getCuit(), LocalDate.now());
		vta.setTotal(vta.calcularTotal());
		
		vd.setTotal(vta.getTotal());
		vd.setVuelto(vta.calcularVuelto());
		if(vd.getVuelto()>=(float)0) {
			VentaDAO.getinstance().add(vta);
			return vd;
		} else throw new ExcepcionProceso("Monto Recibido menor al total a pagar.");
		
	}
	
	private VentaDTO generarVentaTD(VentaDTO vd, ArrayList<ItemVenta> items, Empleado emp) {	
		VentaTarjetaDebito vta = new VentaTarjetaDebito(LocalDate.now(), items, emp, EstadoVenta.FACTURADA, vd.getTotal(), vd.getNumeroTarjeta(),
				vd.getCodigoSeguridad(), vd.getNombre(), vd.getDni(), vd.getFechaVto(), vd.getNroOperacion(), vd.getAprobada(),
				vd.getPin(), vd.getTipoCuenta(), vd.getTipoFact(), vd.getCuit(), null);
		vta.setTotal(vta.calcularTotal());
		VentaDAO.getinstance().add(vta);
		vd.setTotal(vta.getTotal());
		vd.setNroOperacion(vta.getNroOperacion());
		vd.setAprobada(vta.getAprobada());
		return vd;
	}
	
	private VentaDTO generarVentaTC(VentaDTO vd, ArrayList<ItemVenta> items, Empleado emp) {	
		VentaTarjetaCredito vta = new VentaTarjetaCredito(LocalDate.now(), items, emp, EstadoVenta.FACTURADA, vd.getTotal(), 
				vd.getNumeroTarjeta(), 	vd.getCodigoSeguridad(), vd.getNombre(), vd.getDni(), vd.getFechaVto(), 
				vd.getNroOperacion(), vd.getAprobada(), vd.getCantCuotas(), vd.getTipoFact(), vd.getCuit(), null);
		
		vta.setTotal(vta.calcularTotal());
		VentaDAO.getinstance().add(vta);
		vd.setTotal(vta.getTotal());
		vd.setNroOperacion(vta.getNroOperacion());
		vd.setAprobada(vta.getAprobada());
		return vd;
	}
	
	public void marcarFacturaCobrada(EmpleadoDTO g, VentaDTO v) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		
		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByIdVenta(v.getId());
				if (ventas != null) {
					Venta vta = ventas.get(0);
					vta.marcarFacturaCobrada();
					vta.grabar();
				}
				else throw new ExcepcionProceso("No existe una venta con ese número de venta.");								
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		}		
		else throw new UsuarioNoLogueado("Usuario no logueado.");		
	}
	
	public ArrayList<VentaDTO> listarFacturasPorNroFactura(EmpleadoDTO g, Integer idVta) throws UsuarioSinPermisos, UsuarioNoLogueado {
		
		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByIdVenta(idVta);
				ArrayList<VentaDTO> vtas = new ArrayList<VentaDTO> ();
				
				for (Venta v: ventas)
						vtas.add(v.getDTO());
				return vtas;								
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		}		
		else throw new UsuarioNoLogueado("Usuario no logueado.");		
	}
	
	public ArrayList<VentaDTO> listarFacturasPorNroOperacion(EmpleadoDTO g, Integer nroOper) throws UsuarioNoLogueado, UsuarioSinPermisos {
		
		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByNumeroDeOperacion(nroOper);
				ArrayList<VentaDTO> vtas = new ArrayList<VentaDTO> ();
				
				for (Venta v: ventas) 
						vtas.add(v.getDTO());
				
				return vtas;								
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		}		
		else throw new UsuarioNoLogueado("Usuario no logueado.");		
	}

	public ArrayList<VentaDTO> listarFacturas(EmpleadoDTO g, MedioDePago m, LocalDate fch, EstadoVenta e) throws UsuarioNoLogueado, UsuarioSinPermisos {
		
		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentasByEstadoFechaMedioDePago(fch, e, m);
				ArrayList<VentaDTO> vtas = new ArrayList<VentaDTO> ();
				
				for (Venta v: ventas)
					vtas.add(v.getDTO());
				
				return vtas;							
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		}		
		else throw new UsuarioNoLogueado("Usuario no logueado.");	
	}
		
	public VentaDTO mostrarFactura(EmpleadoDTO g, VentaDTO v) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		//buscar por id de venta usando el metodo del DAO . tirar excepcion de procesos
		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByIdVenta(v.getId());
				if (ventas != null) {
					return ventas.get(0).getDTO();
				}
				else throw new ExcepcionProceso("No existe una venta con ese número de venta.");								
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		}		
		else throw new UsuarioNoLogueado("Usuario no logueado.");
	}
	
	public void anularFactura(EmpleadoDTO g, VentaDTO v) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByIdVenta(v.getId());
				if (ventas != null) {
					ventas.get(0).cancelarVenta();
				}
				else throw new ExcepcionProceso("Error al anular la factura.");								
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		}		
		else throw new UsuarioNoLogueado("Usuario no logueado.");
	}
}
