package controladores;

import java.util.ArrayList;
import java.util.Vector;
import org.joda.time.LocalDate;
import daos.EmpleadoDAO;
import dto.EmpleadoDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Puesto;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;
import negocio.Empleado;
import negocio.ItemVenta;

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
			throw new UsuarioNoLogueado("Legajo o password inválido.");
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
							empleado.getHorasAsignadas(), empleado.getPuesto(), empleado.getCbu(),
							empleado.getSession());
					nuevo.guardar();
				} else
					throw new ExcepcionProceso("Ya existe un empleado con ese número de DNI.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción.");
		}
	}

	public void modificacionEmpleado(EmpleadoDTO gerente, EmpleadoDTO e)
			throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(e.getLegajo());
				if (emp != null) {
					if (e.getFechaEgreso()!=null) {
						emp.setFechaEgreso(ConversorFechas.convertJavaToJoda(e.getFechaEgreso()));
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
					emp.setCbu(e.getCbu());

					emp.guardar();
				} else
					throw new ExcepcionProceso("No existe un empleado con ese número de legajo.");

			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción.");
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
					throw new ExcepcionProceso("No existe un empleado con ese número de legajo.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
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
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
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
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
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
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

	public void eliminarEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado)
			throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(empleado.getLegajo());
				if (emp != null) {
					emp.setEstadoEmpleado(empleado.getEstadoEmpleado());
					emp.setFechaEgreso(ConversorFechas.convertJavaToJoda(empleado.getFechaEgreso()));
					emp.guardar();
				} else
					throw new ExcepcionProceso("No existe un empleado con ese número de legajo.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		} else
			throw new UsuarioNoLogueado("Usuario no logueado.");
	}

}
