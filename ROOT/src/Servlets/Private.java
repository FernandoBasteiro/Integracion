package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delegado.BusinessDelegate;
import dto.EmpleadoDTO;
import dto.ItemVentaDTO;
import dto.ParamGralesDTO;
import dto.ProductoDTO;
import dto.StockDTO;
import dto.VentaDTO;
import enumeraciones.EstadoCivil;
import enumeraciones.EstadoEmpleado;
import enumeraciones.EstadoVenta;
import enumeraciones.Genero;
import enumeraciones.MedioDePago;
import enumeraciones.Puesto;
import enumeraciones.TipoCuenta;
import enumeraciones.TipoFactura;
import excepciones.ComunicacionException;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;


/**
 * Servlet implementation class Private
 */
@WebServlet("/Private")
public class Private extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jspPage = "index.jsp";

		try {
			String action = request.getParameter("action");
			BusinessDelegate bd = BusinessDelegate.getInstance();
			// delegado.DummyDelegate bd = delegado.DummyDelegate.getInstancia();
			if ((action == null) || (action.length() < 1))
				action = "no_action";

			if (action.equals("login")) {
				HttpSession session = request.getSession();
				String sessionId = session.getId();
				Integer legajo = Integer.valueOf(request.getParameter("legajo"));
				String password = request.getParameter("password");
				if (legajo != null && password != null) {
					EmpleadoDTO e = new EmpleadoDTO();
					e.setLegajo(legajo);
					e.setPassword(password);
					e.setSession(sessionId);
					e = bd.iniciarSesion(e);
					session.setAttribute("loggedUsr", e);
				} else {
					jspPage = "index.jsp";
					request.setAttribute("error", "Todos los campos deben estar completos.");
				}

			} else if (action.equals("logout")) {
				HttpSession session = request.getSession();
				session.invalidate();
				jspPage = "index.jsp";
				request.setAttribute("success", "Usuario desconectado correctamente.");
			} else if (action.equals("crearEmpleado")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				EmpleadoDTO nuevo = new EmpleadoDTO();
				Integer legajo = (request.getParameter("legajo") == null ? null
						: Integer.valueOf(request.getParameter("legajo")));
				EstadoEmpleado estado = (request.getParameter("estadoEmpleado") == null ? null
						: EstadoEmpleado.fromId(Integer.valueOf(request.getParameter("estadoEmpleado"))));
				String nombre = request.getParameter("nombreEmpleado");
				String apellido = request.getParameter("apellidoEmpleado");
				String dni = request.getParameter("dniEmpleado");
				String nacionalidad = request.getParameter("nacionalidadEmpleado");
				String domicilio = request.getParameter("domicilioEmpleado");
				String email = request.getParameter("emailEmpleado");
				String telefono = request.getParameter("telefonoEmpleado");
				EstadoCivil estadoCivil = (request.getParameter("estadoCivilEmpleado") == null ? null
						: EstadoCivil.fromId(Integer.valueOf(request.getParameter("estadoCivilEmpleado"))));
				Genero genero = (request.getParameter("generoEmpleado") == null ? null
						: Genero.fromId(Integer.valueOf(request.getParameter("generoEmpleado"))));
				LocalDate fechaNacimiento = (request.getParameter("fechaNacimientoEmpleado") == null ? null
						: LocalDate.parse(request.getParameter("fechaNacimientoEmpleado"),
								DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				Puesto puesto = (request.getParameter("puestoEmpleado") == null ? null
						: Puesto.fromId(Integer.valueOf(request.getParameter("puestoEmpleado"))));
				Integer horas = (request.getParameter("horasEmpleado") == null ? null
						: Integer.valueOf(request.getParameter("horasEmpleado")));
				LocalDate fechaIngreso = (request.getParameter("fechaIngresoEmpleado") == null ? null
						: LocalDate.parse(request.getParameter("fechaIngresoEmpleado"),
								DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				LocalDate fechaEgreso = null;
				if (legajo != null)
					fechaEgreso = (request.getParameter("fechaEgresoEmpleado").isEmpty() ? null
							: LocalDate.parse(request.getParameter("fechaEgresoEmpleado"),
									DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				Float sueldoBase = (request.getParameter("sueldoEmpleado") == null ? null
						: Float.valueOf(request.getParameter("sueldoEmpleado")));
				String cbu = request.getParameter("cbuEmpleado");
				String password = request.getParameter("passwordEmpleado");
				nuevo.setLegajo(legajo);
				nuevo.setNombre(nombre);
				nuevo.setApellido(apellido);
				nuevo.setDni(dni);
				nuevo.setNacionalidad(nacionalidad);
				nuevo.setDomicilio(domicilio);
				nuevo.setEmail(email);
				nuevo.setTelefono(telefono);
				nuevo.setEstadoCivil(estadoCivil);
				nuevo.setGenero(genero);
				nuevo.setFechaNacimiento(fechaNacimiento);
				nuevo.setPuesto(puesto);
				nuevo.setHorasAsignadas(horas);
				nuevo.setFechaIngreso(fechaIngreso);
				nuevo.setFechaEgreso(fechaEgreso);
				nuevo.setSueldoBase(sueldoBase);
				nuevo.setCbu(cbu);
				if (!password.isEmpty())
					nuevo.setPassword(password);
				nuevo.setEstadoEmpleado(estado);
				try {
					if (nuevo.getLegajo() == null)
						bd.altaEmpleado(logged, nuevo);
					else
						bd.modificacionEmpleado(logged, nuevo);
					request.setAttribute("success", "Se guard\u00F3 el usuario.");
					jspPage = "Private?action=listarEmpleados&todos=si";
				} catch (ExcepcionProceso e) {
					request.setAttribute("error", e.getMessage());
					request.setAttribute("empleado", nuevo);
					jspPage = "empleados/crearEmpleado.jsp";
				}

			} else if (action.equals("crearProducto")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				String nombre = request.getParameter("nombreProducto");
				Long codigo = (request.getParameter("codigoProducto") == null ? null
						: Long.valueOf(request.getParameter("codigoProducto")));
				String descripcion = request.getParameter("descripcionProducto");
				Float precio = (request.getParameter("precioProducto") == null ? null
						: Float.valueOf(request.getParameter("precioProducto")));
				String presentacion = request.getParameter("presentacionProducto");
				Integer stockDis = (request.getParameter("stockDisponible") == null ? null
						: Integer.valueOf(request.getParameter("stockDisponible")));
				Integer stockMin = (request.getParameter("stockMinimo") == null ? null
						: Integer.valueOf(request.getParameter("stockMinimo")));
				Integer stockTot = (request.getParameter("stockTotal") == null ? null
						: Integer.valueOf(request.getParameter("stockTotal")));
				ProductoDTO nuevo = new ProductoDTO();
				nuevo.setCodigo(codigo);
				nuevo.setNombre(nombre);
				nuevo.setDescripcion(descripcion);
				nuevo.setPrecio(precio);
				nuevo.setPresentacion(presentacion);
				StockDTO stock = new StockDTO();
				stock.setCantidadDisponible(stockDis);
				stock.setCantidadMinimo(stockMin);
				stock.setCantidadTotal(stockTot);
				nuevo.setStock(stock);
				try {
					bd.altaProducto(logged, nuevo);
					request.setAttribute("success", "Se guard\u00F3 el producto.");
					jspPage = "Private?action=listarProductos";
				} catch (ExcepcionProceso e) {
					request.setAttribute("error", e.getMessage());
					request.setAttribute("producto", nuevo);
					jspPage = "productos/crearProducto.jsp";
				}

			} else if (action.equals("verEmpleado")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				Integer legajo = (request.getParameter("legajo") == null ? null
						: Integer.valueOf(request.getParameter("legajo")));
				EmpleadoDTO empleado = new EmpleadoDTO();
				empleado.setLegajo(legajo);
				try {
					empleado = bd.mostrarFichaEmpleado(logged, empleado);
					request.setAttribute("fichaEmpleado", empleado);
				} catch (ExcepcionProceso e) {
					request.setAttribute("error", e.getMessage());
				}
				if (request.getParameter("modificar") == null)
					jspPage = "empleados/verEmpleado.jsp";
				else
					jspPage = "empleados/crearEmpleado.jsp";

			} else if (action.equals("verProducto")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				Long codigo = (request.getParameter("codigo") == null ? null
						: Long.valueOf(request.getParameter("codigo")));
				ProductoDTO producto = new ProductoDTO();
				producto.setCodigo(codigo);
				try {
					producto = bd.mostrarProducto(logged, producto);
					request.setAttribute("producto", producto);
				} catch (ExcepcionProceso e) {
					request.setAttribute("error", e.getMessage());
				}
				if (request.getParameter("modificar") == null)
					jspPage = "productos/verProducto.jsp";
				else
					jspPage = "productos/crearProducto.jsp";
			} else if (action.equals("verFactura")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				Integer nroVenta = (request.getParameter("factura") == null ? null
						: Integer.valueOf(request.getParameter("factura")));
				VentaDTO venta = new VentaDTO();
				venta.setId(nroVenta);
				try {
					venta = bd.mostrarFactura(logged, venta);
					request.setAttribute("factura", venta);
				} catch (ExcepcionProceso e) {
					request.setAttribute("error", e.getMessage());
				}
				jspPage = "facturacion/verFactura.jsp";
			} else if (action.equals("listarEmpleados")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				String todos = request.getParameter("todos");
				Integer legajo = null;
				String dni = null;
				EstadoEmpleado estado = EstadoEmpleado.ACTIVO;
				Puesto puesto = null;
				if (todos == null) {
					legajo = (request.getParameter("buscarEmpleadoLegajo") == null ? null
							: Integer.valueOf(request.getParameter("buscarEmpleadoLegajo")));
					dni = request.getParameter("buscarEmpleadoDni");
					estado = EstadoEmpleado
							.fromId(request.getParameter("estadoEmpleado") == null ? EstadoEmpleado.ACTIVO.getId()
									: Integer.valueOf(request.getParameter("estadoEmpleado")));
					puesto = (request.getParameter("puestoEmpleado") == null ? null
							: Puesto.fromId(Integer.valueOf(request.getParameter("puestoEmpleado"))));
				}
				ArrayList<EmpleadoDTO> empleados = new ArrayList<EmpleadoDTO>();
				if (legajo != null) {
					empleados = bd.listarEmpleadoPorLegajo(logged, legajo);
				} else if (dni != null) {
					empleados = bd.listarEmpleadoPorDNI(logged, dni);
				} else {					
					empleados = bd.listarEmpleados(logged, puesto, estado);
				}
				request.setAttribute("empleados", empleados);
				jspPage = "empleados/index.jsp";
				
			}else if (action.equals("listarParams")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				ArrayList<ParamGralesDTO> params = new ArrayList<ParamGralesDTO>();
			
				try {
					params = bd.listarParamGrales(logged);
				} catch (ExcepcionProceso e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("params", params);
				jspPage = "config.jsp";
				
				
			}else if (action.equals("editParams")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
					
				String[] cadenaParams = request.getParameterValues("params");
				ParamGralesDTO pg = new ParamGralesDTO();
				
				if(cadenaParams[0]!=null) {
					pg.setId(Integer.parseInt(cadenaParams[0]));
				}
				if(cadenaParams[1]!=null) {
					pg.setClave(cadenaParams[1]);
				}
				if(cadenaParams[2]!=null) {
				pg.setValor(cadenaParams[2]);
				}
				try {
					bd.guardarParamGrales(logged, pg);
					request.setAttribute("success", "Se guard\u00F3 el par\u00E1metro.");
				} catch (ExcepcionProceso e) {
					request.setAttribute("error", e.getMessage());
				}
				jspPage = "Private?action=listarParams";
			}
					
			else if (action.equals("listarProductos")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				Long codigo = (request.getParameter("buscarProductoCodigo") == null ? null
						: Long.valueOf(request.getParameter("buscarProductoCodigo")));
				String nombre = request.getParameter("buscarProductoNombre");
				ProductoDTO p = null;
				if (codigo != null || nombre != null) {
					p = new ProductoDTO();
					p.setCodigo(codigo);
					p.setNombre(nombre);
				}
				ArrayList<ProductoDTO> productos = bd.listarProductos(logged, p);
				request.setAttribute("productos", productos);
				jspPage = "productos/index.jsp";

			}
			/*
			 * nada: todos buscarProductoNombre buscarProductoCodigo
			 * 
			 * 
			 */
			else if (action.equals("listarVentas")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");

				Integer numero = (request.getParameter("buscarFacturaNumero") == null ? null
						: Integer.valueOf(request.getParameter("buscarFacturaNumero")));
				Integer operacion = (request.getParameter("buscarFacturaOperacion") == null ? null
						: Integer.valueOf(request.getParameter("buscarFacturaOperacion")));
				EstadoVenta estado = (request.getParameter("estadoFactura") == null ? null
						: EstadoVenta.fromId(Integer.valueOf(request.getParameter("estadoFactura"))));
				MedioDePago mdp = (request.getParameter("medioPagoFactura") == null ? null
						: MedioDePago.fromId(Integer.valueOf(request.getParameter("medioPagoFactura"))));

				String fechaFactura = request.getParameter("fechaFactura");
				LocalDate fecha = LocalDate.now();
				if (fechaFactura != null && fechaFactura.isEmpty() && (estado != null || mdp != null))
					fecha = null;
				else if (fechaFactura != null && !fechaFactura.isEmpty())
					fecha = LocalDate.parse(request.getParameter("fechaFactura"),
							DateTimeFormatter.ofPattern("yyyy-MM-dd"));

				ArrayList<VentaDTO> ventas = null;
				if (numero != null) {
					ventas = bd.listarFacturaPorNroFactura(logged, numero);
				} else if (operacion != null) {
					ventas = bd.listarFacturaPorNroOperacion(logged, operacion);
				} else {
					ventas = bd.listarFacturas(logged, mdp, fecha, estado);
				}
				request.setAttribute("facturas", ventas);
				jspPage = "facturacion/index.jsp";
			}
			/*
			 * nada: las de hoy buscarFacturaNumero buscarFacturaOperacion fechaFactura
			 * estadoFactura medioPagoFactura
			 */
			else if (action.equals("facturar")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				TipoFactura tf = TipoFactura.fromId(request.getParameter("tipoFactura") == null ? null
						: Integer.valueOf(request.getParameter("tipoFactura")));
				String cuit = (tf == TipoFactura.C) ? "Consumidor Final" : request.getParameter("cuitFactura");
				String[] itemsStr = request.getParameterValues("items");
				ArrayList<ItemVentaDTO> items = new ArrayList<ItemVentaDTO>();
				for (String itemStr : itemsStr) {
					List<String> listStrItems = Arrays.asList(itemStr.split(","));
					ItemVentaDTO iv = new ItemVentaDTO();
					ProductoDTO p = new ProductoDTO();
					p.setCodigo(Long.valueOf(listStrItems.get(0)));
					iv.setCantidad(Integer.valueOf(listStrItems.get(1)));
					iv.setProducto(p);
					items.add(iv);
				}
				MedioDePago mdp = MedioDePago.fromId(request.getParameter("medioPago") == null ? null
						: Integer.valueOf(request.getParameter("medioPago")));

				Float montoPago = (request.getParameter("montoPago").isEmpty() ? null
						: Float.valueOf(request.getParameter("montoPago")));

				String numeroTarjetaDebito = request.getParameter("debitoTarjeta");
				Integer codigoSeguridadDebito = (request.getParameter("debitoCodigoSeguridad").isEmpty() ? null
						: Integer.valueOf(request.getParameter("debitoCodigoSeguridad")));
				String titularDebito = request.getParameter("debitoTitular");
				String dniDebito = request.getParameter("debitoDni");
				String vencimientoDebito = request.getParameter("debitoVencimiento");
				TipoCuenta tc = TipoCuenta.fromId(request.getParameter("debitoTipoCuenta").isEmpty() ? null
						: Integer.valueOf(request.getParameter("debitoTipoCuenta")));
				Integer pin = (request.getParameter("debitoPin").isEmpty() ? null
						: Integer.valueOf(request.getParameter("debitoPin")));

				String numeroTarjetaCredito = request.getParameter("creditoTarjeta");
				Integer codigoSeguridadCredito = (request.getParameter("creditoCodigoSeguridad").isEmpty() ? null
						: Integer.valueOf(request.getParameter("creditoCodigoSeguridad")));
				String titularCredito = request.getParameter("creditoTitular");
				String dniCredito = request.getParameter("creditoDni");
				String vencimientoCredito = request.getParameter("creditoVencimiento");
				Integer cuotas = (request.getParameter("creditoCuotas").isEmpty() ? null
						: Integer.valueOf(request.getParameter("creditoCuotas")));

				VentaDTO v = new VentaDTO();
				v.setEmpleado(logged);
				v.setTipoFact(tf);
				v.setCuit(cuit);
				v.setMedioDePago(mdp);
				v.setMontoRecibido(montoPago);
				v.setTipoCuenta(tc);
				v.setPin(pin);
				v.setCantCuotas(cuotas);
				v.setItems(items);
				if (mdp == MedioDePago.TARJETA_CREDITO) {
					v.setNumeroTarjeta(numeroTarjetaCredito);
					v.setCodigoSeguridad(codigoSeguridadCredito);
					v.setNombre(titularCredito);
					v.setDni(dniCredito);
					v.setFechaVto(vencimientoCredito);
				} else if (mdp == MedioDePago.TARJETA_DEBITO) {
					v.setNumeroTarjeta(numeroTarjetaDebito);
					v.setCodigoSeguridad(codigoSeguridadDebito);
					v.setNombre(titularDebito);
					v.setDni(dniDebito);
					v.setFechaVto(vencimientoDebito);
				}

				try {
					v = bd.generarVenta(logged, v);
					String resp = "";
					if(v.getMedioDePago() == MedioDePago.EFECTIVO) {
						resp = "Su vuelto es $" + v.getVuelto().toString();
					}else if(v.getMedioDePago() == MedioDePago.TARJETA_CREDITO) {
						resp = "El nro. de operación es " + v.getNroOperacion();
					}else {
						resp = "Su pago ha sido debitado";
					}
					request.setAttribute("venta", v);
					request.setAttribute("success", "\u00A1Venta aprobada! "+resp+".");
					request.setAttribute("script", "true");
				} catch (ExcepcionProceso e) {
					request.setAttribute("venta", v);
					request.setAttribute("error", e.getMessage());
					request.setAttribute("script", "false");
				}
				jspPage = "facturacion/vender.jsp";
				/*
				 * tipoFactura (enum) cuitFactura (String) items (array de tuplas de codigo y
				 * cantidad) medioPago - Efectivo montoPago - Debito debitoTarjeta
				 * debitoCodigoSeguridad debitoTitular debitoDni debitoVencimiento
				 * debitoTipoCuenta debitoPin - Credito creditoTarjeta creditoCodigoSeguridad
				 * creditoTitular creditoDni creditoVencimiento creditoCuotas
				 */
			} else if (action.equals("imputarCobros")) {
				// EJEMPLO:
				// Le pega a: Web/Private?action=imputarCobros
				// Envia: periodoMes=1&periodoAnio=2019
			}
		} catch (UsuarioSinPermisos usp) {
			jspPage = "index.jsp";
			request.setAttribute("error", usp.getMessage());
		} catch (UsuarioNoLogueado unl) {
			jspPage = "index.jsp";
			request.setAttribute("error", unl.getMessage());
		} catch (ComunicacionException ce) {
			jspPage = "index.jsp";
			request.setAttribute("error", ce.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher(jspPage);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}