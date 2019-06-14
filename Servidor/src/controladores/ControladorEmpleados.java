package controladores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import org.joda.time.LocalDate;

import daos.EmpleadoDAO;
import dto.EmpleadoDTO;
import dto.NovedadDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Puesto;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;
import negocio.Empleado;
import negocio.Novedad;

public class ControladorEmpleados {

	private static ControladorEmpleados instance;

	public ControladorEmpleados() {
		super();
	}

	public static ControladorEmpleados getInstance() {
		if (instance == null) {
			instance = new ControladorEmpleados();
		}
		return instance;
	}

	public EmpleadoDTO iniciarSesion(EmpleadoDTO e) throws UsuarioNoLogueado {
		Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(e.getLegajo());
		if (emp == null || !emp.getPassword().equals(e.getPassword()))
			throw new UsuarioNoLogueado("Legajo o password inv�lido.");
		else {
			emp.setSession(e.getSession());
			emp.guardar();
			return emp.getDTO();
		}
	}

	public static boolean estaLogueado(EmpleadoDTO e) throws UsuarioNoLogueado {
		if (e != null) {
			Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(e.getLegajo());
			if (emp != null && emp.getSession().equals(e.getSession())) {
				return true;
			}
		}
		throw new UsuarioNoLogueado("El usuario no esta logueado");
	}

	public void altaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado)
			throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByDni(empleado.getDni());
				if (emp == null) {
					Empleado nuevo = new Empleado(empleado.getNombre(), empleado.getApellido(), empleado.getDni(),
					empleado.getDomicilio(), empleado.getTelefono(), empleado.getEmail(),
					empleado.getEstadoCivil(), empleado.getGenero(),
					ConversorFechas.convertJavaToJoda(empleado.getFechaNacimiento()),
					ConversorFechas.convertJavaToJoda(empleado.getFechaIngreso()),
					ConversorFechas.convertJavaToJoda(empleado.getFechaEgreso()), empleado.getEstadoEmpleado(),
					empleado.getNacionalidad(), empleado.getPassword(), empleado.getSueldoBase(),
					empleado.getHorasAsignadas(), empleado.getPuesto(), null,
					empleado.getSession());
					
					try {
						this.crearCuentaBanco(this.crearJsonAltaEmpleado(nuevo));
						String cbu = this.averiguarCBUEmpleado(emp.getDni());
					} catch (IOException e) {
						throw new ExcepcionProceso("No se pudo crear la cuenta bancaria.");
					}
					
					//***********************************************
					//TODO Infomar CBU a liquidacion sueldos
					//***********************************************
					
					nuevo.guardar();
				} else
					throw new ExcepcionProceso("Ya existe un empleado con ese n�mero de DNI.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n.");
		}
	}

	public void modificacionEmpleado(EmpleadoDTO gerente, EmpleadoDTO e)
			throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(e.getLegajo());
				
				if (emp != null) {	
					
					// Si tenia egreso, estaba desvinculado y nuevo es desvinculado >> no cambio egreso
					// Si tenia egreso, estaba desvinculado y nuevo no es desvinculado >> cambio egreso
					// Si no tenia egreso y nuevo es desvinculado >> cambio egreso
					// Si no tenia egreso y nuevo no es desvinculado >> no cambio egreso
					
					if ((emp.getFechaEgreso()!=null && emp.getEstadoEmpleado() == EstadoEmpleado.DESVINCULADO && e.getEstadoEmpleado() != EstadoEmpleado.DESVINCULADO) || 
							(emp.getFechaEgreso() == null && e.getEstadoEmpleado() == EstadoEmpleado.DESVINCULADO)) {
						emp.setFechaEgreso(ConversorFechas.convertJavaToJoda(e.getFechaEgreso()));
						//*************************************************************
						//TODO informar a liquidacion de sueldos para liquidacion final
						//************************************************************
					}
					
					if (e.getPassword()!=null) {
						emp.setPassword(e.getPassword());
					}
					emp.setNombre(e.getNombre());
					emp.setApellido(e.getApellido());
					emp.setLegajo(e.getLegajo());
					emp.setDni(e.getDni());
					emp.setDomicilio(e.getDomicilio());
					emp.setTelefono(e.getTelefono());
					emp.setEmail(e.getEmail());
					emp.setEstadoCivil(e.getEstadoCivil());
					emp.setGenero(e.getGenero());
					emp.setFechaNacimiento(ConversorFechas.convertJavaToJoda(e.getFechaNacimiento()));
					emp.setFechaIngreso(ConversorFechas.convertJavaToJoda(e.getFechaIngreso()));
					emp.setEstadoEmpleado(e.getEstadoEmpleado());
					emp.setNacionalidad(e.getNacionalidad());
					emp.setSueldoBase(e.getSueldoBase());
					emp.setHorasAsignadas(e.getHorasAsignadas());
					emp.setPuesto(e.getPuesto());
					

					emp.guardar();
				} else
					throw new ExcepcionProceso("No existe un empleado con ese n�mero de legajo.");

			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n.");
		}
	}

	public EmpleadoDTO mostrarFichaEmpleado(EmpleadoDTO gerente, EmpleadoDTO e)
			throws UsuarioSinPermisos, ExcepcionProceso, UsuarioNoLogueado {

		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(e.getLegajo());
				if (emp != null) {
					return emp.getDTO();
				} else
					throw new ExcepcionProceso("No existe un empleado con ese n�mero de legajo.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

	public ArrayList<EmpleadoDTO> listarEmpleadoPorDNI(EmpleadoDTO gerente, String dni)
			throws UsuarioSinPermisos, UsuarioNoLogueado {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByDni(dni);
				ArrayList<EmpleadoDTO> list = new ArrayList<EmpleadoDTO>();
				if (emp != null) {
					list.add(emp.getDTO());
				}
				return list;
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

	public ArrayList<EmpleadoDTO> listarEmpleadoPorLegajo(EmpleadoDTO gerente, Integer leg)
			throws UsuarioSinPermisos, UsuarioNoLogueado {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(leg);
				ArrayList<EmpleadoDTO> list = new ArrayList<EmpleadoDTO>();
				if (emp != null) {
					list.add(emp.getDTO());
				}
				return list;
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

	public ArrayList<EmpleadoDTO> listarEmpleados(EmpleadoDTO gerente, Puesto p, EstadoEmpleado est)
			throws UsuarioSinPermisos, UsuarioNoLogueado {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {

				ArrayList<Empleado> list = null;
				ArrayList<EmpleadoDTO> listDTO = new ArrayList<EmpleadoDTO>();

				list = EmpleadoDAO.getinstance().getEmpleadosByPuestoAndEstado(p, est);
				for (Empleado e : list)
					listDTO.add(e.getDTO());
				return listDTO;
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

	public void eliminarEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado)
			throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(empleado.getLegajo());
				if (emp != null) {
					emp.setEstadoEmpleado(EstadoEmpleado.ANULADO);
					emp.setFechaEgreso(LocalDate.now());
					emp.guardar();
					//**************************************
					//TODO llamar a LiqSueldo con baja
					//***************************************
					
					//**************************************
					//TODO llamar a Banco con baja?????
					//***************************************
					
				} else
					throw new ExcepcionProceso("No existe un empleado con ese n�mero de legajo.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}
	
	public void generarNovedad(EmpleadoDTO gerente, EmpleadoDTO empleado)
			throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(empleado.getLegajo());
				if (emp != null) {
					ArrayList<Novedad> novedades = emp.getNovedades();
					for (NovedadDTO nd : empleado.getNovedades()) {
						Novedad n = new Novedad(LocalDate.now(), nd.getEsPaga(), nd.getCantDias(), nd.getMes(), nd.getAnio());
						novedades.add(n);
					}
					emp.setNovedades(novedades);
					emp.guardar();
				} else
					throw new ExcepcionProceso("No existe un empleado con ese n�mero de legajo.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}
	
	public EmpleadoDTO listarNovedades(EmpleadoDTO empleado) throws ExcepcionProceso {
		Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByDni(empleado.getDni());
		if (emp != null) return emp.getDTO();
		else throw new ExcepcionProceso("No existe el empleado");
	}
	
	private String crearJsonAltaEmpleado(Empleado empleado) {
		JsonObjectBuilder json = Json.createObjectBuilder();
		Long idUsuario = Long.parseLong(empleado.getDni());
		String pwd = ControladorVentas.getInstance().getParamGral("banco_default_password");
		String nombre = empleado.getNombre();
		String apellido = empleado.getApellido();
		JsonObjectBuilder idRol = Json.createObjectBuilder().add("id", Integer.valueOf(ControladorVentas.getInstance().getParamGral("banco_idRol")));
		JsonObjectBuilder idProducto = Json.createObjectBuilder().add("id", Integer.valueOf(ControladorVentas.getInstance().getParamGral("banco_idProducto")));
		json.add("idUsuario", idUsuario);
		json.add("contrasena", pwd);
		json.add("nombre", nombre);
		json.add("apellido", apellido);
		json.add("idRol", idRol);
		json.add("idProducto", idProducto);
		return json.build().toString();
	}
	
	private int crearCuentaBanco(String json) throws IOException {
		URL url = new URL("https://bank-back.herokuapp.com/api/v1/usuario");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("PUT");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		 try (OutputStream os = con.getOutputStream()) {
			byte[] input = json.getBytes("utf-8");
			os.write(input, 0, input.length);
		}
		 
		return con.getResponseCode();
	}
	
	private String averiguarCBUEmpleado(String cuit) throws IOException, ExcepcionProceso {
		URL url = new URL("https://bank-back.herokuapp.com/api/v1/cuentas/" + cuit);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setDoOutput(true);
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			//return response.toString();
			
			JsonReader reader = Json.createReader(new StringReader(response.toString()));
			JsonArray cuentasArr = reader.readArray();
	        reader.close();
	        List<JsonObject> cuentas = cuentasArr.getValuesAs(JsonObject.class);
			for (JsonObject cuenta : cuentas) {
				if (cuenta.getString("tipoCuenta").equals(ControladorVentas.getInstance().getParamGral("banco_tipoCuenta_deposito"))) return cuenta.getString("cbu");
			}
			throw new ExcepcionProceso("Caja de ahorro no encontrada.");
		}

	}
}
