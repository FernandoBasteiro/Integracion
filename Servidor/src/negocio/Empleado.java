package negocio;

import java.time.LocalDate;

import enumeraciones.EstadoCivil;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Genero;
import enumeraciones.Puesto;
 
public class Empleado {
	private String nombre;
	private String apellido;
	private Integer legajo;
	private String dni;
	private String domicilio;
	private String telefono;
	private String email;
	private EstadoCivil estadoCivil;
	private Genero genero;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIngreso;
	private LocalDate fechaEgreso;
	private EstadoEmpleado estadoEmpleado;
	private String nacionalidad;
	private String password;
	private Float sueldoBase;
	private Integer horasAsignadas;
	private Puesto puesto;
	private Integer cbu;
	private String session;
	
	

}
