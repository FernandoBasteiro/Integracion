package Private;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delegado.BusinessDelegate;
import dto.EmpleadoDTO;
import dto.VentaDTO;
import excepciones.ComunicacionException;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;

@WebServlet("/Private/marcarCobrado")
public class marcarCobrado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObjectBuilder json = Json.createObjectBuilder();
		try {
			BusinessDelegate bd = BusinessDelegate.getInstance();
			HttpSession session = request.getSession();
			EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
			String facturaStr = request.getParameter("factura");
			Integer factura = null;
			if (facturaStr != null) factura = Integer.valueOf(facturaStr);
			VentaDTO f = new VentaDTO();
			f.setId(factura);
			bd.marcarFacturaCobrada(logged, f);
			json.add("success", "La factura fue marcada como cobrada.");			
		}
		catch (ComunicacionException | UsuarioNoLogueado | ExcepcionProceso | UsuarioSinPermisos ce) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			json.add("error", ce.getMessage());		
		}
		response.setContentType("application/json");
		response.getWriter().write(json.build().toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
