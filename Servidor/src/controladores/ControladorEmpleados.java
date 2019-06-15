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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
						String cbu = this.averiguarCBUEmpleado(nuevo.getDni());
						nuevo.setCbu(cbu);
					} catch (Exception e) {
						throw new ExcepcionProceso("No se pudo crear la cuenta bancaria.");
					}
					
					try {
						if (this.crearCuentaLiquidaciones(this.crearJsonAltaLiquidacion(nuevo)) != 200) {
							throw new ExcepcionProceso("No se pudo crear la cuenta liquidac\u00F3n.");
						}
					} catch (Exception e) {
						throw new ExcepcionProceso("No se pudo crear la cuenta liquidaci\u00F3n.");
					}
					nuevo.guardar();
				} else
					throw new ExcepcionProceso("Ya existe un empleado con ese n\u00FAmero de DNI.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci\u00F3n.");
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

						try {
							if (this.bajaCuentaLiquidaciones(emp) != 200) {
								throw new ExcepcionProceso("Hubo un error al dar de baja del sistema de liquidaciones. Contacte a Liquid Salary para m\u00E1s informaci\u00F3n.");
							}							
						} catch (Exception ex) {
							throw new ExcepcionProceso("Hubo un error al dar de baja del sistema de liquidaciones. Contacte a Liquid Salary para m\u00E1s informaci\u00F3n.");
						}
					}
					else {
						if (! emp.getSueldoBase().equals(e.getSueldoBase())) {
							emp.setSueldoBase(e.getSueldoBase());
							try {
								if (this.modificarSueldoLiquidaciones(emp) != 200) {
									throw new ExcepcionProceso("Hubo un error intentando actualizar el sueldo en el sistema de liquidaciones. Contacte a Liquid Salary para m\u00E1s informaci\u00F3n.");
								}
							} catch (Exception ex) {
								throw new ExcepcionProceso("Hubo un error intentando actualizar el sueldo en el sistema de liquidaciones. Contacte a Liquid Salary para m\u00E1s informaci\u00F3n.");
							}
						}
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
	
	private int crearCuentaBanco(String json) throws Exception {
		OkHttpClient client = new OkHttpClient();
		byte[] input = json.getBytes("utf-8");
		RequestBody body = RequestBody.create(input);
		Request request = new Request.Builder()
		  .url("https://bank-back.herokuapp.com/api/v1/usuario")
		  .put(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("Postman-Token", "c17b7809-88f5-4b43-a848-93ac24536b41")
		  .build();

		Response response = client.newCall(request).execute();
		return response.code();
	}
	
	private String averiguarCBUEmpleado(String cuit) throws Exception {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("https://bank-back.herokuapp.com/api/v1/cuentas/" + cuit).get().build();
		Response response = client.newCall(request).execute();
		JsonReader reader = Json.createReader(new StringReader(response.body().string()));
		JsonArray cuentasArr = reader.readArray();
        reader.close();
        List<JsonObject> cuentas = cuentasArr.getValuesAs(JsonObject.class);
		for (JsonObject cuenta : cuentas) {
			if (cuenta.getString("tipoCuenta").equals(ControladorVentas.getInstance().getParamGral("banco_tipoCuenta_deposito"))) 
				return cuenta.getString("cbu");
		}
		return null;
	}
	
	private Integer crearCuentaLiquidaciones(String json) throws Exception {
		OkHttpClient client = new OkHttpClient();
		byte[] input = json.getBytes("utf-8");
		RequestBody body = RequestBody.create(input);
		Request request = new Request.Builder()
		  .url("http://appdistflix.herokuapp.com/api/empleado/insert")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .build();

		Response response = client.newCall(request).execute();
		return response.code();
	}
	
	private Integer modificarSueldoLiquidaciones(Empleado emp) throws Exception {
		OkHttpClient client = new OkHttpClient();
		String json = "{ \"sueldo\" : " + emp.getSueldoBase() + "}";
		byte[] input = json.getBytes("utf-8");
		RequestBody body = RequestBody.create(input);
		Request request = new Request.Builder()
		  .url("http://appdistflix.herokuapp.com/api/empleado/update/?cuil=" + emp.getDni())
		  .put(body)
		  .addHeader("Content-Type", "application/json")
		  .build();

		Response response = client.newCall(request).execute();
		return response.code();
	}

	private Integer bajaCuentaLiquidaciones(Empleado empleado) throws Exception {
		OkHttpClient client = new OkHttpClient();
		String json = "{ \"cuil\" : \"" + empleado.getDni() + "\"}";
		byte[] input = json.getBytes("utf-8");
		RequestBody body = RequestBody.create(input);
		Request request = new Request.Builder()
		  .url("http://appdistflix.herokuapp.com/api/empleado/delete")
		  .delete(body)
		  .addHeader("Content-Type", "application/json")
		  .build();

		Response response = client.newCall(request).execute();
		return response.code();
	}

	
	private String crearJsonAltaLiquidacion(Empleado empleado) {
		JsonObjectBuilder json = Json.createObjectBuilder();
		String cuit = ControladorVentas.getInstance().getParamGral("cuit");
		String cuil = empleado.getDni();
		String fechaIngreso = empleado.getFechaIngreso().toString(DateTimeFormat.forPattern("yyyy-MM-dd"));
		String cbu = empleado.getCbu();
		Boolean convenio = Boolean.valueOf(ControladorVentas.getInstance().getParamGral("liquidacion_hayConvenio")); //TODO
		String rubro = ControladorVentas.getInstance().getParamGral("liquidacion_rubro");
		String categoria = ControladorVentas.getInstance().getParamGral("liquidacion_categoria");
		String tipoLiquidacion = ControladorVentas.getInstance().getParamGral("liquidacion_tipoLiquidacion");
		Integer vacacionesDisp = Integer.valueOf(ControladorVentas.getInstance().getParamGral("liquidacion_vacacionesDisp"));
		Integer diasEstudioDisp = Integer.valueOf(ControladorVentas.getInstance().getParamGral("liquidacion_diasEstudioDisp"));
		Float sueldo = empleado.getSueldoBase();
		json.add("cuit", cuit);
		json.add("cuil", cuil);
		json.add("fechaIngreso", fechaIngreso);
		json.add("cbu", cbu);
		json.add("convenio", convenio);
		json.add("rubro", rubro);
		json.add("categoria", categoria);
		json.add("tipoLiquidacion", tipoLiquidacion);
		json.add("vacacionesDisp", vacacionesDisp);
		json.add("diasEstudioDisp", diasEstudioDisp);
		json.add("sueldo", sueldo);
		return json.build().toString();
	}
}
