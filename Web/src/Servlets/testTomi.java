package Servlets;


import delegado.BusinessDelegate;
import dto.EmpleadoDTO;
import enumeraciones.EstadoCivil;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Genero;
import enumeraciones.Puesto;
import excepciones.ComunicacionException;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;


public class testTomi {
	private static testTomi instancia;
	
	private testTomi( ) throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso, ComunicacionException {
		this.probarBase();
	}
	
	public static testTomi getInstancia() throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso, ComunicacionException {
		if (instancia == null) {
			instancia = new testTomi();
		}
		return instancia;
	}
	public static void main(String[] args) throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso, ComunicacionException
	{
		testTomi.getInstancia();
	}
	public void probarBase() throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso, ComunicacionException {

		
		//ARMO EL EMPLEADO NUEVO PARA AGREGAR
		EmpleadoDTO empleado = new EmpleadoDTO();
		empleado.setApellido("AgregadoPorGerente2");
		empleado.setCbu("1234");
		empleado.setDni("12333");
		empleado.setDomicilio("BASSS");
		empleado.setEstadoEmpleado(EstadoEmpleado.ACTIVO);
		empleado.setEstadoCivil(EstadoCivil.CASADO);
		empleado.setFechaIngreso(java.time.LocalDate.now());
		empleado.setFechaEgreso(java.time.LocalDate.now());
		empleado.setFechaNacimiento(java.time.LocalDate.now());
		empleado.setGenero(Genero.FEMENINO);
		empleado.setHorasAsignadas(10);
		empleado.setEmail("mail@falso");
		empleado.setNacionalidad("Argentino");
		empleado.setPassword("1234");
		empleado.setPuesto(Puesto.CAJERO);
		empleado.setSueldoBase((float)100.3);
		empleado.setTelefono("1234");
		
		//ARMO EL GERENTE PARA INICIAR SESION
		EmpleadoDTO gerente = new EmpleadoDTO();
		gerente.setLegajo(1);
		gerente.setPassword("1234");
		gerente.setSession("3");
		
		//INICIO SESION
		gerente=BusinessDelegate.getInstance().iniciarSesion(gerente);
	
		//AGREGO EMPLEADO
	//	BusinessDelegate.getInstance().altaEmpleado(gerente, empleado);
		
		
		
		
		
		
		
		//ControladorEmpleados.getInstance().listarEmpleadoPorLegajo(gerente, leg)
		
		//ControladorEmpleados.getInstance().altaEmpleado(gerente, empleado);
		
		

		
		
		
		
		
		
		System.out.println("Bien");

	}
}