package Private;

import java.io.IOException;

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
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;

@WebServlet("/Private/eliminarEmpleado")
public class eliminarEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObjectBuilder json = Json.createObjectBuilder();
		try {
			BusinessDelegate bd = BusinessDelegate.getInstance();
			HttpSession session = request.getSession();
			EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
			String legajoStr = request.getParameter("legajo");
			Integer legajo = null;
			if (legajoStr != null) legajo = Integer.valueOf(legajoStr);
			EmpleadoDTO empleado = new EmpleadoDTO();
			empleado.setLegajo(legajo);
			bd.eliminarEmpleado(logged, empleado);
			json.add("warning", "El empleado fue dado de baja.\nComuníquese con la Entidad Bancaria BX10 para obtener información sobre cómo tramitar la baja de la cuenta y con Liquid Salary para cancelar su liquidación.");
		}
		catch (ComunicacionException | UsuarioNoLogueado | ExcepcionProceso | UsuarioSinPermisos e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			json.add("error", e.getMessage());
		}
		response.setContentType("application/json");
		response.getWriter().write(json.build().toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
