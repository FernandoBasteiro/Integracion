package Private;


import java.io.IOException;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delegado.BusinessDelegate;
import dto.EmpleadoDTO;
import dto.ProductoDTO;
import excepciones.ComunicacionException;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;

/**
 * Servlet implementation class listarProductos
 */
@WebServlet("/Private/listarProductos")
public class listarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObjectBuilder json = Json.createObjectBuilder();
		try {
			BusinessDelegate bd = BusinessDelegate.getInstance();
			HttpSession session = request.getSession();
			EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
			ArrayList<ProductoDTO> productos;
			productos = bd.listarProductos(logged, null);
			JsonArrayBuilder productosJson = Json.createArrayBuilder();
			JsonObjectBuilder productoJson;
			for (ProductoDTO p : productos) {
				productoJson = Json.createObjectBuilder()
						.add("codigo", p.getCodigo().toString())
						.add("nombre", p.getNombre())
						.add("descripcion", p.getDescripcion())
						.add("presentacion", p.getPresentacion())
						.add("precio", p.getPrecio());
				productosJson.add(productoJson);
			}
			json.add("productos", productosJson);
		}
		catch (UsuarioNoLogueado unl) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			json.add("error", unl.getMessage());
		}
		catch (UsuarioSinPermisos usp) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			json.add("error", usp.getMessage());
		}
		catch (ComunicacionException ce) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			json.add("error", ce.getMessage());
		}
		catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			json.add("error", "Error desconocido :(");
		}
		response.setContentType("application/json");
		response.getWriter().write(json.build().toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
