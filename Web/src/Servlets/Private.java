package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delegado.BusinessDelegate;
import dto.EmpleadoDTO;
import dto.ProductoDTO;
import dto.StockDTO;
import dto.VentaDTO;
import enumeraciones.EstadoCivil;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Genero;
import enumeraciones.Puesto;
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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = "index.jsp";

		try {
			String action = request.getParameter("action");
			BusinessDelegate bd = BusinessDelegate.getInstance();
			//delegado.DummyDelegate bd = delegado.DummyDelegate.getInstancia();
			if ((action == null) || (action.length() < 1)) action = "no_action";
			
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
				}
				else {
					jspPage = "index.jsp";
					request.setAttribute("error", "Todos los campos deben estar completos.");					
				}

			}
			else if (action.equals("logout")) {
				HttpSession session = request.getSession();
				session.invalidate();
				jspPage = "index.jsp";
				request.setAttribute("error", "Usuario desconectado correctamente.");	
			}
			else if (action.equals("crearEmpleado")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				EmpleadoDTO nuevo = new EmpleadoDTO();
				Integer legajo = (request.getParameter("legajo") == null ? null : Integer.valueOf(request.getParameter("legajo")));
				EstadoEmpleado estado = (request.getParameter("estadoEmpleado") == null ? null : EstadoEmpleado.fromId(Integer.valueOf(request.getParameter("estadoEmpleado"))));
				String nombre = request.getParameter("nombreEmpleado");
				String apellido = request.getParameter("apellidoEmpleado");
				String dni = request.getParameter("dniEmpleado");
				String nacionalidad = request.getParameter("nacionalidadEmpleado");
				String domicilio = request.getParameter("domicilioEmpleado");
				String email = request.getParameter("emailEmpleado");
				String telefono = request.getParameter("telefonoEmpleado");
				EstadoCivil estadoCivil = (request.getParameter("estadoCivilEmpleado") == null ? null : EstadoCivil.fromId(Integer.valueOf(request.getParameter("estadoCivilEmpleado"))));
				Genero genero = (request.getParameter("generoEmpleado") == null ? null : Genero.fromId(Integer.valueOf(request.getParameter("generoEmpleado"))));
				LocalDate fechaNacimiento = (request.getParameter("fechaNacimientoEmpleado") == null ? null : LocalDate.parse(request.getParameter("fechaNacimientoEmpleado"),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				Puesto puesto = (request.getParameter("puestoEmpleado") == null ? null : Puesto.fromId(Integer.valueOf(request.getParameter("puestoEmpleado"))));
				Integer horas = (request.getParameter("horasEmpleado") == null ? null : Integer.valueOf(request.getParameter("horasEmpleado")));
				LocalDate fechaIngreso = (request.getParameter("fechaIngresoEmpleado") == null ? null : LocalDate.parse(request.getParameter("fechaIngresoEmpleado"),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				Float sueldoBase = (request.getParameter("sueldoEmpleado") == null ? null : Float.valueOf(request.getParameter("sueldoEmpleado")));
				String cbu = request.getParameter("cbuEmpleado");
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
				nuevo.setSueldoBase(sueldoBase);
				nuevo.setCbu(cbu);
				try {
					if (nuevo.getLegajo() == null) bd.altaEmpleado(logged, nuevo);
					else bd.modificacionEmpleado(logged, nuevo);
					request.setAttribute("success", "Se creo el usuario");
				}
				catch (ExcepcionProceso e) {
					request.setAttribute("error", e.getMessage());
					request.setAttribute("empleado", nuevo);
				}
				jspPage = "empleados/crearEmpleado.jsp";					
			}
			else if (action.equals("crearProducto")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				String nombre = request.getParameter("nombreProducto");
				Integer codigo = (request.getParameter("codigoProducto") == null ? null : Integer.valueOf(request.getParameter("codigoProducto")));
				String descripcion = request.getParameter("descripcionProducto");
				Float precio = (request.getParameter("precioProducto") == null ? null : Float.valueOf(request.getParameter("precioProducto")));
				String presentacion = request.getParameter("presentacionProducto");
				Integer stockDis = (request.getParameter("stockDisponible") == null ? null : Integer.valueOf(request.getParameter("stockDisponible")));
				Integer stockMin = (request.getParameter("stockMinimo") == null ? null : Integer.valueOf(request.getParameter("stockMinimo")));
				Integer stockTot = (request.getParameter("stockTotal") == null ? null : Integer.valueOf(request.getParameter("stockTotal")));
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
				bd.cargarProducto(logged, nuevo);
				jspPage = "productos/crearProducto.jsp";
			}
			else if (action.equals("verEmpleado")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				Integer legajo = (request.getParameter("legajo") == null ? null : Integer.valueOf(request.getParameter("legajo")));
				EmpleadoDTO empleado = new EmpleadoDTO();
				empleado.setLegajo(legajo);
				try {
					empleado = bd.mostrarFichaEmpleado(logged, empleado);
					request.setAttribute("fichaEmpleado", empleado);
				}
				catch (ExcepcionProceso e) {
					request.setAttribute("error", e.getMessage());
				}
				jspPage = "empleados/verEmpleado.jsp";
			}
			else if (action.equals("verProducto")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				Integer codigo = (request.getParameter("codigo") == null ? null : Integer.valueOf(request.getParameter("codigo")));
				ProductoDTO producto = new ProductoDTO();
				producto.setCodigo(codigo);
				try {
					producto = bd.mostrarProducto(logged, producto);
					request.setAttribute("producto", producto);					
				}
				catch (ExcepcionProceso e) {
					request.setAttribute("error", e.getMessage());
				}
				jspPage = "productos/verProducto.jsp";
			}
			else if (action.equals("verVenta")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				Integer nroVenta = (request.getParameter("factura") == null ? null : Integer.valueOf(request.getParameter("factura")));
				VentaDTO venta = new VentaDTO();
				venta.setId(nroVenta);
				try {
					venta = bd.mostrarFactura(logged, venta);
					request.setAttribute("factura", venta);					
				}
				catch (ExcepcionProceso e) {
					request.setAttribute("error", e.getMessage());
				}
				jspPage = "facturacion/verFactura.jsp";
			}
			else if (action.equals("listarEmpleados")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				Integer legajo = (request.getParameter("buscarEmpleadoLegajo") == null ? null : Integer.valueOf(request.getParameter("buscarEmpleadoLegajo")));
				String dni = request.getParameter("buscarEmpleadoDni");
				EstadoEmpleado estado = EstadoEmpleado.fromId(request.getParameter("estadoEmpleado") == null ? null : Integer.valueOf(request.getParameter("estadoEmpleado")));
				Puesto puesto = Puesto.fromId(request.getParameter("puestoEmpleado") == null ? null : Integer.valueOf(request.getParameter("puestoEmpleado")));
				try {
					
				}
				catch (ExcepcionProceso e) {
				if (legajo != null) {
					bd.listarEmpleadoPorLegajo(logged, legajo);
				}
				else if (dni != null) {
					bd.listarEmpleadoPorDNI(logged, dni);
				}
				else {
					bd.listarEmpleados(logged, puesto, estado);
				}
			}
			else if (action.equals("listarProductos")) {
				HttpSession session = request.getSession();
				EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
				Integer codigo = (request.getParameter("buscarEmpleadoLegajo") == null ? null : Integer.valueOf(request.getParameter("buscarEmpleadoLegajo")));
				String nombre = request.getParameter("buscarEmpleadoDni");
				
				
				/*
				nada: todos
				buscarProductoNombre
				buscarProductoCodigo
				*/
			}
			else if (action.equals("listarVentas")) {
				/*
				nada: las de hoy
				buscarFacturaNumero
				buscarFacturaOperacion
				fechaFactura
				estadoFactura
				medioPagoFactura
				*/
			}
			else if (action.equals("vender")) {
				//nada: todos los productos
			}
			else if (action.equals("facturar")) {
				
			}
		}
		catch (UsuarioSinPermisos usp) {
			jspPage = "index.jsp";
			request.setAttribute("error", usp.getMessage());	
		}
		catch (UsuarioNoLogueado unl) {
			jspPage = "index.jsp";
			request.setAttribute("error", unl.getMessage());	
		}
		catch (ComunicacionException ce) {
			jspPage = "index.jsp";
			request.setAttribute("error", ce.getMessage());		
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(jspPage);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}