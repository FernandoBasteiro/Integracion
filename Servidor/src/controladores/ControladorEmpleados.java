package controladores;

import java.util.ArrayList;

import dto.EmpleadoDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Puesto;

public class ControladorEmpleados {
	
	
	
	public EmpleadoDTO iniciarSesion(EmpleadoDTO e) {
		return e;
	}
	
	public  void altaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) {
			
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
 

