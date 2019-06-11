package controladores;

import java.util.ArrayList;

import org.joda.time.LocalDate;

import daos.EmpleadoDAO;
import dto.EmpleadoDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Puesto;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;
import negocio.Empleado;

public class ControladorEmpleados {
	
	public EmpleadoDTO iniciarSesion(EmpleadoDTO e) throws UsuarioNoLogueado {
		Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(e.getLegajo());
		if (emp == null || ! emp.getPassword().equals(e.getPassword())) throw new UsuarioNoLogueado("Legajo o password inválido.");
		else {
			emp.setSession(e.getSession());
			emp.guardar();
			return emp.getDTO();
		}
	}
	
	public boolean estaLogueado (EmpleadoDTO e) throws UsuarioNoLogueado {
		Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(e.getLegajo());
		if (emp == null || ! emp.getSession().equals(e.getSession())) throw new UsuarioNoLogueado("El usuario no esta logueado");
		else return true;
	}
	
	public  void altaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByDni(empleado.getDni());
				if (emp == null) {
					Empleado nuevo = new Empleado(empleado.getNombre(), empleado.getApellido(), empleado.getDni(), empleado.getDomicilio(), empleado.getTelefono(), empleado.getEmail(), empleado.getEstadoCivil(), empleado.getGenero(), empleado.getFechaNacimiento(), empleado.getFechaIngreso(), empleado.getFechaEgreso(), empleado.getEstadoEmpleado(), empleado.getNacionalidad(), empleado.getPassword(), empleado.getSueldoBase(), empleado.getHorasAsignadas(), empleado.getPuesto(), empleado.getCbu(), empleado.getSession());
					nuevo.guardar();
				}
				else throw new ExcepcionProceso("Ya existe un empleado con ese número de DNI.");
			}
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción.");
		}	
	}
	
	public  void modificacionEmpleado(EmpleadoDTO gerente, EmpleadoDTO e) throws  UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(e.getLegajo());
				if (emp != null) {
					if (e.getEstadoEmpleado().equals(EstadoEmpleado.DESVINCULADO)) {
						emp.setFechaEgreso(LocalDate.now());
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
					emp.setPassword(e.getPassword());
					emp.setSueldoBase(e.getSueldoBase());
					emp.setHorasAsignadas(e.getHorasAsignadas());
					emp.setPuesto(e.getPuesto());
					emp.setCbu(e.getCbu());
					
					emp.guardar();		
				}
				else throw new ExcepcionProceso("No existe un empleado con ese número de legajo.");
				
			}
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción.");
		}
	}
	
	public EmpleadoDTO mostrarFichaEmpleado(EmpleadoDTO gerente, EmpleadoDTO e) throws  UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {

		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(e.getLegajo());
				if (emp != null) {
					return emp.getDTO();
				}
				else throw new ExcepcionProceso("No existe un empleado con ese número de legajo.");								
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		}
		else throw new UsuarioNoLogueado("Legajo o password inválido.");
	}
	
	public ArrayList<EmpleadoDTO> listarEmpleadoPorDNI(EmpleadoDTO gerente, String dni) {
		return null;
	}
	
	public ArrayList<EmpleadoDTO> listarEmpleadoPorLegajo(EmpleadoDTO gerente, Integer leg) {
		return null;
	}
	
	public ArrayList<EmpleadoDTO> listarEmpleados(EmpleadoDTO gerente, Puesto p, EstadoEmpleado e) {
		return null;
	}
	
	public void eliminarEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws  UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado emp = EmpleadoDAO.getinstance().getEmpleadoByLegajo(empleado.getLegajo());
				if (emp != null) {
					emp.guardar();
				}
				else throw new ExcepcionProceso("No existe un empleado con ese número de legajo.");
			}
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		}
		else throw new UsuarioNoLogueado("Legajo o password inválido.");
	}
	
	
	
} 
 

