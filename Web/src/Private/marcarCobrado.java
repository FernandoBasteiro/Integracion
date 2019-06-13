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

@WebServlet("/Private/marcarCobrado")
public class marcarCobrado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		JsonObjectBuilder json = Json.createObjectBuilder();
		try {
			BusinessDelegate bd = BusinessDelegate.getInstance();
			HttpSession session = request.getSession();
			EmpleadoDTO logged = (EmpleadoDTO) session.getAttribute("loggedUsr");
			String facturaStr = request.getParameter("factura");
			Integer factura;
			if (facturaStr != null) factura = Integer.valueOf(facturaStr);
			VentaDTO f = new VentaDTO();
			bd.marcarFacturaCobrada(logged, f);
			json.add("success", "Se actualizaron los cobros.");			
		}
		catch (ComunicacionException ce) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			json.add("error", ce.getMessage());		
		} */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
