package Servlets;


import java.rmi.RemoteException;
import java.util.ArrayList;

import delegado.BusinessDelegate;
import dto.EmpleadoDTO;
import dto.ProductoDTO;
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
	
	private testTomi( ) throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso, ComunicacionException, RemoteException {
		this.probarBase();
	}
	
	public static testTomi getInstancia() throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso, ComunicacionException, RemoteException {
		if (instancia == null) {
			instancia = new testTomi();
		}
		return instancia;
	}
	public static void main(String[] args) throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso, ComunicacionException, RemoteException
	{
		testTomi.getInstancia();
	}
	public void probarBase() throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso, ComunicacionException, RemoteException {

		
		//ARMO EL EMPLEADO NUEVO PARA AGREGAR
		EmpleadoDTO empleado = new EmpleadoDTO();
		empleado.setApellido("AgregadoEnTestTomi");
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
		//BusinessDelegate.getInstance().altaEmpleado(gerente, empleado);
		
		
		//Elimino EMPLEADO 
		/**
		EmpleadoDTO empleado2 = new EmpleadoDTO();
		empleado2.setLegajo(4);
		empleado2.setDni("12");
		BusinessDelegate.getInstance().bajaEmpleado(gerente, empleado2);
		
		
		for( EmpleadoDTO e : BusinessDelegate.getInstance().listarEmpleados(gerente, Puesto.GERENTE, EstadoEmpleado.ACTIVO))
		{
			System.out.println(e.getApellido());
		}
		*/
		
		//MOSTRAR FICHA Y MODIFICAR
		/**
		EmpleadoDTO empleado2 = new EmpleadoDTO();
		empleado2.setLegajo(2);
		empleado2=BusinessDelegate.getInstance().mostrarFichaEmpleado(gerente, empleado2);
		empleado2.setDni("12");
		empleado2.setNombre("Paula");
		BusinessDelegate.getInstance().modificacionEmpleado(gerente, empleado2);
		*/
		
		ArrayList<EmpleadoDTO> empleados = new ArrayList<EmpleadoDTO>();
		
		empleados=BusinessDelegate.getInstance().listarEmpleadoPorDNI(gerente, "5666");
		
		System.out.println(empleados.get(0).getApellido());
				
		
		//ControladorEmpleados.getInstance().listarEmpleadoPorLegajo(gerente, leg)
		
		//ControladorEmpleados.getInstance().altaEmpleado(gerente, empleado);
		
		
/**
 * 
 * 
 * 
 * 
 *    PRODUCTOS 
 */
		ProductoDTO prod = new ProductoDTO();
		prod.setNombre("Tallar");
		//prod.setCodigo(1234567);
		ArrayList<ProductoDTO> productos = new ArrayList<ProductoDTO>();
		productos=BusinessDelegate.getInstance().listarProductos(gerente, prod);
		
		for(ProductoDTO p : productos) {
			System.out.println(p.getNombre());

		}
		
		System.out.println("Bien");

	}
}