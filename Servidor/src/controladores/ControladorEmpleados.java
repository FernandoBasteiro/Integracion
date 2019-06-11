package controladores;

import java.util.ArrayList;

import daos.EmpleadoDAO;
import dto.EmpleadoDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Puesto;
import excepciones.UsuarioNoLogueado;
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
	
	public  void altaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws UsuarioNoLogueado {
		if (estaLogueado(gerente)) {
			if (gerente.getPuesto().getId() >= Puesto.GERENTE.getId()) {
				Empleado nuevo = new Empleado(empleado.getNombre(), empleado.getApellido(), empleado.getLegajo(), empleado.getDni(), empleado.getDomicilio(), empleado.getTelefono(), empleado.getEmail(), empleado.getEstadoCivil(), empleado.getGenero(), empleado.getFechaNacimiento(), empleado.getFechaIngreso(), empleado.getFechaEgreso(), empleado.getEstadoEmpleado(), empleado.getNacionalidad(), empleado.getPassword(), empleado.getPassword(), empleado.getSueldoBase(), empleado.getHorasAsignadas(), empleado.getPuesto(), empleado.getCbu(), empleado.getSession());
			}
		}
			
	}
	
	public  void modificacionEmpleado(EmpleadoDTO gerente, EmpleadoDTO eempleado) {
		
	}
	
	public  void bajaEmpleado(EmpleadoDTO gerente, EmpleadoDTO eempleado) {
		
	}
	
	public EmpleadoDTO mostrarFichaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) {
		return empleado;
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
	
	public void eliminarEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) {
		
	}
	
} 
 

