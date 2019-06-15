package controladores;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import org.joda.time.LocalDate;

import daos.EmpleadoDAO;
import daos.ParamGralesDAO;
import daos.ProductoDAO;
import daos.VentaDAO;
import dto.EmpleadoDTO;
import dto.ItemVentaDTO;
import dto.ParamGralesDTO;
import dto.VentaDTO;
import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;
import enumeraciones.Puesto;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;
import negocio.Empleado;
import negocio.ItemVenta;
import negocio.ParamGrales;
import negocio.Producto;
import negocio.Venta;
import negocio.VentaEfectivo;
import negocio.VentaTarjetaCredito;
import negocio.VentaTarjetaDebito;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ControladorVentas {

	private static ControladorVentas instance;
	private ArrayList<ParamGrales> parametros;

	/*
	 * private String cuit; // los usa el bco + liquidar sueldo private Integer
	 * tc_id_establecimiento; //lo usa la entidad crediticia private String cc_cbu;
	 * //lo usa la entidad bancaria private String ca_cbu; private String
	 * razonSocial; //lo usa la entidad bancaria private String
	 * default_password_banco; private Integer banco_idRol; private Integer
	 * banco_idProducto;
	 */
	public ControladorVentas() {
		this.parametros = ParamGralesDAO.getinstance().getParamGrales();
		/*
		 * this.cuit = ParamGralesDAO.getinstance().getValor("cuit"); String id_est_str
		 * = ParamGralesDAO.getinstance().getValor("tc_id_establecimiento");
		 * this.tc_id_establecimiento = (id_est_str == null ? null :
		 * Integer.valueOf(id_est_str)); this.cc_cbu =
		 * ParamGralesDAO.getinstance().getValor("cc_cbu"); this.ca_cbu =
		 * ParamGralesDAO.getinstance().getValor("ca_cbu"); this.razonSocial =
		 * ParamGralesDAO.getinstance().getValor("razonSocial");
		 * this.default_password_banco =
		 * ParamGralesDAO.getinstance().getValor("default_password_banco"); String
		 * banco_idRolStr = ParamGralesDAO.getinstance().getValor("banco_idRol");
		 * this.banco_idRol = (banco_idRolStr == null ? null :
		 * Integer.valueOf(banco_idRolStr)); String banco_idProductoStr =
		 * ParamGralesDAO.getinstance().getValor("banco_idProducto");
		 * this.banco_idProducto = (banco_idProductoStr == null ? null :
		 * Integer.valueOf(banco_idProductoStr));
		 */
	}

	public static ControladorVentas getInstance() {
		if (instance == null) {
			instance = new ControladorVentas();
		}
		return instance;
	}

	public VentaDTO generarVenta(EmpleadoDTO c, VentaDTO v)
			throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		if (ControladorEmpleados.getInstance().estaLogueado(c)) {
			if (c.getPuesto().getId() >= Puesto.CAJERO.getId()) {

				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(c.getLegajo());

				ArrayList<ItemVenta> items = new ArrayList<ItemVenta>();
				for (ItemVentaDTO id : v.getItems()) {
					ArrayList<Producto> prods = ProductoDAO.getinstance()
							.getProductoByCodigo(id.getProducto().getCodigo());
					if (prods != null) {
						Producto p = prods.get(0);
						// p.getStock().descontarStock(id.getCantidad());
						ItemVenta i = new ItemVenta(p, p.getPrecio(), id.getCantidad());
						items.add(i);
					}
				}
				Venta venta = null;
				// Ver tipo de VENTA
				switch (v.getMedioDePago()) {
				case EFECTIVO:
					venta = new VentaEfectivo(LocalDate.now(), items, emp, EstadoVenta.COBRADA, v.getTotal(),
							v.getMontoRecibido(), v.getVuelto(), v.getTipoFact(), v.getCuit(), LocalDate.now());
					venta.setTotal(venta.calcularTotal());

					v.setTotal(venta.getTotal());
					v.setVuelto(((VentaEfectivo)venta).calcularVuelto());
					if (v.getVuelto() >= (float) 0) {
						venta.grabar();
					} else
						throw new ExcepcionProceso("Monto Recibido menor al total a pagar.");
					break;
				case TARJETA_DEBITO:
					venta = new VentaTarjetaDebito(LocalDate.now(), items, emp, EstadoVenta.FACTURADA, v.getTotal(),
							v.getNumeroTarjeta(), v.getCodigoSeguridad(), v.getNombre(), v.getDni(), v.getFechaVto(),
							v.getNroOperacion(), v.getAprobada(), v.getPin(), v.getTipoCuenta(), v.getTipoFact(),
							v.getCuit(), null);
					venta.setTotal(venta.calcularTotal());

					venta.confirmar(VentaDAO.getProxVenta());
					venta.grabar();

					v.setTotal(venta.getTotal());
					v.setNroOperacion(((VentaTarjetaDebito) venta).getNroOperacion());
					v.setAprobada(((VentaTarjetaDebito) venta).getAprobada());

					/*
					 * v.setAprobada(true); v.setNroOperacion(123456); if (v.getAprobada()) { v =
					 * generarVentaTD(v, items, emp); } else throw new
					 * ExcepcionProceso("Error TD. Venta no aprobada.");
					 */
					break;
				case TARJETA_CREDITO:
					venta = new VentaTarjetaCredito(LocalDate.now(), items, emp, EstadoVenta.FACTURADA, v.getTotal(),
							v.getNumeroTarjeta(), v.getCodigoSeguridad(), v.getNombre(), v.getDni(), v.getFechaVto(),
							v.getNroOperacion(), v.getAprobada(), v.getCantCuotas(), v.getTipoFact(), v.getCuit(),
							null);
					venta.setTotal(venta.calcularTotal());
					venta.confirmar(VentaDAO.getProxVenta());
					venta.grabar();

					v.setTotal(venta.getTotal());
					v.setNroOperacion(((VentaTarjetaCredito) venta).getNroOperacion());
					v.setAprobada(((VentaTarjetaCredito) venta).getAprobada());
					/*
					 * v.setAprobada(true); v.setNroOperacion(456789); //TODO if (v.getAprobada()) {
					 * v = generarVentaTC(v, items, emp); } else throw new
					 * ExcepcionProceso("Error TC. Venta no aprobada.");
					 */
					break;
				default:
					throw new ExcepcionProceso("Medio de pago inválido.");	
				}
				if (venta != null) {
					for (ItemVenta iv : venta.getItems()) {
						iv.getProducto().getStock().descontarStock(iv.getCantidad());
						ControladorProductos.getInstancia().actualizarStock(c, iv.getProducto().getDTO());
						
					}					
				}
				return v;
			} else
				throw new ExcepcionProceso("Error al generar una venta.");
		} else
			throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción.");
	}

	private VentaDTO generarVentaEfectivo(VentaDTO vd, ArrayList<ItemVenta> items, Empleado emp)
			throws ExcepcionProceso {
		VentaEfectivo vta = new VentaEfectivo(LocalDate.now(), items, emp, EstadoVenta.COBRADA, vd.getTotal(),
				vd.getMontoRecibido(), vd.getVuelto(), vd.getTipoFact(), vd.getCuit(), LocalDate.now());
		vta.setTotal(vta.calcularTotal());

		vd.setTotal(vta.getTotal());
		vd.setVuelto(vta.calcularVuelto());
		if (vd.getVuelto() >= (float) 0) {
			VentaDAO.getinstance().add(vta);
			return vd;
		} else
			throw new ExcepcionProceso("Monto Recibido menor al total a pagar.");

	}
	/*
	private VentaDTO generarVentaTD(VentaDTO vd, ArrayList<ItemVenta> items, Empleado emp) {
		VentaTarjetaDebito vta = new VentaTarjetaDebito(LocalDate.now(), items, emp, EstadoVenta.FACTURADA,
				vd.getTotal(), vd.getNumeroTarjeta(), vd.getCodigoSeguridad(), vd.getNombre(), vd.getDni(),
				vd.getFechaVto(), vd.getNroOperacion(), vd.getAprobada(), vd.getPin(), vd.getTipoCuenta(),
				vd.getTipoFact(), vd.getCuit(), null);
		vta.setTotal(vta.calcularTotal());
		VentaDAO.getinstance().add(vta);
		vd.setTotal(vta.getTotal());
		vd.setNroOperacion(vta.getNroOperacion());
		vd.setAprobada(vta.getAprobada());
		return vd;
	}

	private VentaDTO generarVentaTC(VentaDTO vd, ArrayList<ItemVenta> items, Empleado emp) throws ExcepcionProceso {
		VentaTarjetaCredito vta = new VentaTarjetaCredito(LocalDate.now(), items, emp, EstadoVenta.FACTURADA,
				vd.getTotal(), vd.getNumeroTarjeta(), vd.getCodigoSeguridad(), vd.getNombre(), vd.getDni(),
				vd.getFechaVto(), vd.getNroOperacion(), vd.getAprobada(), vd.getCantCuotas(), vd.getTipoFact(),
				vd.getCuit(), null);

		vta.setTotal(vta.calcularTotal());
		// TODO Controlar
		vta.confirmar();

		VentaDAO.getinstance().add(vta);
		vd.setTotal(vta.getTotal());
		vd.setNroOperacion(vta.getNroOperacion());
		vd.setAprobada(vta.getAprobada());
		return vd;
	}
	*/
	public void marcarFacturaCobrada(EmpleadoDTO g, VentaDTO v)
			throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {

		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByIdVenta(v.getId());
				if (ventas.size() > 0) {
					Venta vta = ventas.get(0);
					vta.marcarFacturaCobrada();
					vta.grabar();
				} else
					throw new ExcepcionProceso("No existe una venta con ese nómero de venta.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}
	
	public void marcarFacturasCobradas(EmpleadoDTO g, String periodo) throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {

		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentasByEstadoFechaMedioDePago(null,
						EstadoVenta.FACTURADA, null);
				ArrayList<Integer> ventasCobradas;
				try {
					ventasCobradas = this.obtenerVentasCobradas(periodo);
				} catch (Exception e) {
					throw new ExcepcionProceso("Hubo un error al contactar a la entidad crediticia.");
				}
				for (Venta v : ventas) {
					for (Integer vc : ventasCobradas) {
						if (v.getId().equals(vc)) {
							v.marcarFacturaCobrada();
							v.grabar();
						}
					}
				}
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

	private ArrayList<Integer> obtenerVentasCobradas(String periodo) throws Exception {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("http://paypauli.herokuapp.com/api/consultar/resumen/abonados/9/201906").get().build();
		Response response = client.newCall(request).execute();
		String json = response.body().string();
		try {
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonArray cobrosArr = reader.readArray();
        reader.close();
        ArrayList<Integer> codOps = new ArrayList<Integer>();
        List<JsonObject> cobros = cobrosArr.getValuesAs(JsonObject.class);
		for (JsonObject cobro : cobros) {
			codOps.add(cobro.getInt("comprobante"));
		}
		return codOps;
		}
		catch (Exception e) {
			throw new Exception();
		}
	}

	public ArrayList<VentaDTO> listarFacturasPorNroFactura(EmpleadoDTO g, Integer idVta)
			throws UsuarioSinPermisos, UsuarioNoLogueado {

		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByIdVenta(idVta);
				ArrayList<VentaDTO> vtas = new ArrayList<VentaDTO>();

				for (Venta v : ventas)
					vtas.add(v.getDTO());
				return vtas;
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

	public ArrayList<VentaDTO> listarFacturasPorNroOperacion(EmpleadoDTO g, Integer nroOper)
			throws UsuarioNoLogueado, UsuarioSinPermisos {

		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByNumeroDeOperacion(nroOper);
				ArrayList<VentaDTO> vtas = new ArrayList<VentaDTO>();

				for (Venta v : ventas)
					vtas.add(v.getDTO());

				return vtas;
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

	public ArrayList<VentaDTO> listarFacturas(EmpleadoDTO g, MedioDePago m, LocalDate fch, EstadoVenta e)
			throws UsuarioNoLogueado, UsuarioSinPermisos {

		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentasByEstadoFechaMedioDePago(fch, e, m);
				ArrayList<VentaDTO> vtas = new ArrayList<VentaDTO>();

				for (Venta v : ventas)
					vtas.add(v.getDTO());

				return vtas;
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

	public VentaDTO mostrarFactura(EmpleadoDTO g, VentaDTO v)
			throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		// buscar por id de venta usando el metodo del DAO . tirar excepcion de procesos
		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByIdVenta(v.getId());
				if (ventas.size() > 0) {
					return ventas.get(0).getDTO();
				} else
					throw new ExcepcionProceso("No existe una venta con ese nómero de venta.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

	public void anularFactura(EmpleadoDTO g, VentaDTO v)
			throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<Venta> ventas = VentaDAO.getinstance().getVentaByIdVenta(v.getId());
				if (ventas.size() > 0) {
					ventas.get(0).cancelarVenta();
					ventas.get(0).grabar();
				} else
					throw new ExcepcionProceso("No existe una factura con ese nómero de factura.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

	public String getParamGral(String clave) {
		for (ParamGrales pg : parametros)
			if (pg.getClave().equals(clave))
				return pg.getValor();
		return null;
	}

	public ArrayList<ParamGralesDTO> listarParamGrales(EmpleadoDTO g) throws UsuarioNoLogueado, UsuarioSinPermisos {

		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				ArrayList<ParamGrales> pgs = ParamGralesDAO.getinstance().getParamGrales();
				ArrayList<ParamGralesDTO> pgDTOs = new ArrayList<ParamGralesDTO>();

				for (ParamGrales pg : pgs)
					pgDTOs.add(pg.getDTO());

				return pgDTOs;
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci\u00F3n");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

	public void guardarParamGrales(EmpleadoDTO g, ParamGralesDTO pgDTO) throws UsuarioNoLogueado, UsuarioSinPermisos {

		if (ControladorEmpleados.getInstance().estaLogueado(g)) {
			if (g.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				for (ParamGrales pgc : parametros) {
					if (pgc.getId().equals(pgDTO.getId())) pgc.setValor(pgDTO.getValor()); 
				}
				ParamGrales pg = new ParamGrales(pgDTO.getId(), pgDTO.getClave(), pgDTO.getValor());
				ParamGralesDAO.getinstance().add(pg);
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci\u00F3n");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

}
