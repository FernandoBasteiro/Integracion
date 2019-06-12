

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
@WebServlet("/listarProductos")
public class listarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BusinessDelegate bd = BusinessDelegate.getInstance();
			
			HttpSession session = request.getSession();
			EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
			ArrayList<ProductoDTO> productos;
			productos = bd.listarProductos(logged, null);
			JsonObjectBuilder json = Json.createObjectBuilder();
			JsonArrayBuilder productosJson = Json.createArrayBuilder();
			JsonObjectBuilder productoJson;
			for (ProductoDTO p : productos) {
				productoJson = Json.createObjectBuilder()
						.add("codigo", p.getCodigo())
						.add("nombre", p.getNombre())
						.add("descripcion", p.getDescripcion())
						.add("presentacio", p.getPresentacion())
						.add("precio", p.getPrecio());
				productosJson.add(productoJson);
			}
		}
		catch (Exception e) {
			JsonObjectBuilder json = Json.createObjectBuilder().add("error", e.getMessage());	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
