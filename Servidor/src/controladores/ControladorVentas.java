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
	
	public void generarVenta(EmpleadoDTO c, VentaDTO v) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		if (ControladorEmpleados.getInstance().estaLogueado(c)) {
			if (c.getPuesto().getId() >= Puesto.CAJERO.getId()) {
				
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(c.getLegajo());
				
				ArrayList<ItemVenta> items = new ArrayList<ItemVenta>();
				for (ItemVentaDTO id : v.getItems()) {
						Producto p = ProductoDAO.getinstance().getProductoByCodigo(id.getProducto().getCodigo());
						p.getStock().descontarStock(id.getCantidad());
						ItemVenta i = new ItemVenta(p, id.getPrecio(), id.getCantidad());
						items.add(i);
				}
				
				//Ver tipo de VENTA
				switch (v.getMedioDePago()) {
					case EFECTIVO:
						generarVentaEfectivo(v, items, emp);
						break;
					case TARJETA_DEBITO:
						if (v.getAprobada()) {
							generarVentaTD(v, items, emp);
						}
						else throw new ExcepcionProceso("Error TD. Venta no aprobada.");
						break;
					case TARJETA_CREDITO:
						if (v.getAprobada()) {
							generarVentaTC(v, items, emp);
						}
						else throw new ExcepcionProceso("Error TC. Venta no aprobada.");
						break;
					default: 
						throw new ExcepcionProceso("Medio de pago inv�lido.");
				}
			}
			else throw new ExcepcionProceso("Error al generar una venta.");
		}
		else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n.");
	}	
	
	private void generarVentaEfectivo(VentaDTO vd, ArrayList<ItemVenta> items, Empleado emp) {			
		VentaEfectivo vta = new VentaEfectivo(LocalDate.now(), items, emp, EstadoVenta.COBRADA, vd.getTotal(), 
				vd.getMontoRecibido(), vd.getVuelto(), vd.getTipoFact(), vd.getCuit(), LocalDate.now());
		VentaDAO.getinstance().add(vta);
	}
	
	private void generarVentaTD(VentaDTO vd, ArrayList<ItemVenta> items, Empleado emp) {	
		VentaTarjetaDebito vta = new VentaTarjetaDebito(LocalDate.now(), items, emp, EstadoVenta.FACTURADA, vd.getTotal(), vd.getNumeroTarjeta(),
				vd.getCodigoSeguridad(), vd.getNombre(), vd.getDni(), vd.getFechaVto(), vd.getNroOperacion(), vd.getAprobada(),
				vd.getPin(), vd.getTipoCuenta(), vd.getTipoFact(), vd.getCuit(), null);
		VentaDAO.getinstance().add(vta);
	}
	
	private void generarVentaTC(VentaDTO vd, ArrayList<ItemVenta> items, Empleado emp) {	
		VentaTarjetaCredito vta = new VentaTarjetaCredito(LocalDate.now(), items, emp, EstadoVenta.FACTURADA, vd.getTotal(), 
				vd.getNumeroTarjeta(), 	vd.getCodigoSeguridad(), vd.getNombre(), vd.getDni(), vd.getFechaVto(), 
				vd.getNroOperacion(), vd.getAprobada(), vd.getCantCuotas(), vd.getTipoFact(), vd.getCuit(), null);
		VentaDAO.getinstance().add(vta);
	}
	
	public void marcarFacturaCobrada(EmpleadoDTO g, VentaDTO v) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		
		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByIdVenta(v.getId());
				if (ventas != null) {
					Venta vta = ventas.get(0);
					vta.marcarFacturaCobrada();
					VentaDAO.getinstance().add(vta);
				}
				else throw new ExcepcionProceso("No existe una venta con ese n�mero de venta.");								
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n");
		}		
		else throw new UsuarioNoLogueado("Usuario no logueado.");		
	}
	
	public ArrayList<VentaDTO> listarFacturasPorNroFactura(EmpleadoDTO g, Integer idVta) throws ExcepcionProceso, UsuarioSinPermisos, UsuarioNoLogueado {
		
		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByIdVenta(idVta);
				if (ventas != null) {
					ArrayList<VentaDTO> vtas = new ArrayList<VentaDTO> ();
					for (Venta v: ventas) {
						vtas.add(v.getDTO());
					}
					return vtas;
				}
				else throw new ExcepcionProceso("No existe una venta con ese n�mero de venta.");								
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n");
		}		
		else throw new UsuarioNoLogueado("Usuario no logueado.");		
	}
	
	public ArrayList<VentaDTO> listarFacturasPorNroOperacion(EmpleadoDTO g, Integer nroOper) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		
		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByNumeroDeOperacion(nroOper);
				if (ventas != null) {
					ArrayList<VentaDTO> vtas = new ArrayList<VentaDTO> ();
					for (Venta v: ventas) {
						vtas.add(v.getDTO());
					}
					return vtas;
				}
				else throw new ExcepcionProceso("No existe una venta con ese n�mero de operaci�n.");								
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n");
		}		
		else throw new UsuarioNoLogueado("Usuario no logueado.");		
	}

	public ArrayList<VentaDTO> listarFacturas(EmpleadoDTO gerente, MedioDePago m, LocalDate fch, EstadoFactura e) {
		//TODO pendiente de hacer en el DAO
		return null;
	}
		
	public VentaDTO mostrarFactura(EmpleadoDTO g, VentaDTO v) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		//buscar por id de venta usando el metodo del DAO . tirar excepcion de procesos
		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByIdVenta(v.getId());
				if (ventas != null) {
					return ventas.get(0).getDTO();
				}
				else throw new ExcepcionProceso("No existe una venta con ese n�mero de venta.");								
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n");
		}		
		else throw new UsuarioNoLogueado("Usuario no logueado.");
	}
}
