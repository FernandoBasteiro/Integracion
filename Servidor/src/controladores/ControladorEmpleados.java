package controladores;

import java.util.ArrayList;

import dto.EmpleadoDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Puesto;

public class ControladorEmpleados {
	
	
	
	public EmpleadoDTO iniciarSesion(EmpleadoDTO e) {
		return e;
	}
	
	public  void altaEmpleado(EmpleadoDTO gerente, EmpleadoDTO e) {
			
	}
	
	public  void modificacionEmpleado(EmpleadoDTO gerente, EmpleadoDTO e) {
		
	}
	
	public  void bajaEmpleado(EmpleadoDTO gerente, EmpleadoDTO e) {
		
	}
	
	public EmpleadoDTO mostrarFichaEmpleado(EmpleadoDTO gerente, EmpleadoDTO e) {
		return e;
	}
	
	public ArrayList<EmpleadoDTO> listarEmpleados(EmpleadoDTO supervisor) {
		return null;
	}
	
	public ArrayList<EmpleadoDTO> listarEmpleados(EmpleadoDTO supervisor, EstadoEmpleado ee) {
		return null;
	}
	
	public ArrayList<EmpleadoDTO> listarEmpleados(EmpleadoDTO supervisor, Puesto p) {
		return null;
	}
	
	
} 
 

