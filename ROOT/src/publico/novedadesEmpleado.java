package publico;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegado.BusinessDelegate;
import dto.EmpleadoDTO;
import dto.NovedadDTO;
import excepciones.ComunicacionException;
import excepciones.ExcepcionProceso;

/**
 * Servlet implementation class novedadesEmpleado
 */
@WebServlet("/Public/novedadesEmpleado")
public class novedadesEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObjectBuilder json = Json.createObjectBuilder();
		try {
			BusinessDelegate bd = BusinessDelegate.getInstance();
			String cuilStr = request.getParameter("cuil");
			String periodoStr = request.getParameter("periodo");
			if (cuilStr != null && periodoStr != null && ! cuilStr.isEmpty() && ! periodoStr.isEmpty()) {
				try {
					String anioStr = periodoStr.substring(0, 4);
					String mesStr = periodoStr.substring(4, 6);
					Integer mes = Integer.parseInt(mesStr);
					Integer anio = Integer.valueOf(anioStr);
					EmpleadoDTO empleado = new EmpleadoDTO();
					empleado.setDni(cuilStr);
					try {
						empleado  = bd.listarNovedades(empleado);
						json.add("cuil", empleado.getDni());
						json.add("nombre", empleado.getNombre());
						json.add("apellido", empleado.getApellido());
						JsonArrayBuilder novedades = Json.createArrayBuilder();
						for (NovedadDTO n : empleado.getNovedades()) {
							Integer anioNovedad = n.getAnio();
							Integer mesNovedad = n.getMes();
							//String anioNovedadStr = String.format("%04d", anioNovedad);
							//String mesNovedadStr = String.format("%02d", mesNovedad);
							if (anioNovedad.equals(anio) && mesNovedad.equals(mes)) {
							//if (anioStr.equals(anioNovedadStr) && mesStr.equals(mesNovedadStr)) {
								JsonObjectBuilder novedad = Json.createObjectBuilder()
										.add("TipoLicencia", n.getEsPaga())
										.add("CantidadDias", n.getCantDias())
										.add("Periodo", String.format("%04d", anio)+String.format("%02d", mes));
								novedades.add(novedad);
							}
						}
						json.add("novedades", novedades);
					} catch (ExcepcionProceso e) {
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					}
				}
				catch (IndexOutOfBoundsException | NumberFormatException e) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
			}
			else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		} catch (ComunicacionException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		response.setContentType("application/json");
		response.getWriter().write(json.build().toString());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
