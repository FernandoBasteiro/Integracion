package Servlets;


import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import delegado.BusinessDelegate;
import dto.EmpleadoDTO;
import dto.ItemVentaDTO;
import dto.ProductoDTO;
import dto.VentaDTO;
import enumeraciones.EstadoCivil;
import enumeraciones.EstadoEmpleado;
import enumeraciones.EstadoVenta;
import enumeraciones.Genero;
import enumeraciones.MedioDePago;
import enumeraciones.Puesto;
import enumeraciones.TipoCuenta;
import enumeraciones.TipoFactura;
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
		/**ProductoDTO prod = new ProductoDTO();
		prod.setNombre("Tallar");
		//prod.setCodigo(1234567);
		ArrayList<ProductoDTO> productos = new ArrayList<ProductoDTO>();
		productos=BusinessDelegate.getInstance().listarProductos(gerente, prod);
		
		for(ProductoDTO p : productos) {
			System.out.println(p.getNombre());

		}
		*/
		
		
		/**
		 * 
		 * 
		 * 
		 * 
		 *    VENTAS 
		 
		 EFECTIVO --------------------------------------------------------------EFECTIVO */
		VentaDTO v = new VentaDTO();
		
		ProductoDTO p = new ProductoDTO();
		p.setCodigo(1234567);
		
		ItemVentaDTO iv	= new ItemVentaDTO();
		iv.setCantidad(2);
		iv.setProducto(p);
		
		ArrayList<ItemVentaDTO> ivs = new ArrayList<ItemVentaDTO>();
		ivs.add(iv);
		
		//v.setAprobada(aprobada);
		//v.setCantCuotas(cantCuotas);
		//v.setCodigoSeguridad(codigoSeguridad);
		v.setCuit("2");
		//v.setDni(dni);
		v.setEmpleado(gerente);
		//v.setEstado(null);
		//v.setFechaCobro(fechaCobro);
		v.setFechaVenta(LocalDate.now());
		//v.setFechaVto();
		//v.setId(id);
		v.setItems(ivs);
		v.setMedioDePago(MedioDePago.EFECTIVO);
		v.setMontoRecibido((float)10);
		//v.setNombre(nombre);
		//v.setNroOperacion(nroOperacion);
		//v.setNumeroTarjeta(numeroTarjeta);
		//v.setPin(pin);
		//v.setTipoCuenta(tipoCuenta);
		v.setTipoFact(TipoFactura.A);
		
		VentaDTO v2 = new VentaDTO();
		
		v2=BusinessDelegate.getInstance().generarVenta(gerente, v); 
		
		System.out.println("VENTA NUEVA .- EFECTIVO: ");
		System.out.println("Vuelto: "+v2.getVuelto());
		System.out.println("Total: "+v2.getTotal());
		
		
		
		/**----------------------------------------------------------------------FIN EFECTIVO
		
		-----------------------------------------------------------------TARJETA DEBITO 
		 
		 

		VentaDTO v = new VentaDTO();
		
		ProductoDTO p = new ProductoDTO();
		p.setCodigo(1234567);
		
		ItemVentaDTO iv	= new ItemVentaDTO();
		iv.setCantidad(10);
		iv.setProducto(p);
		
		ArrayList<ItemVentaDTO> ivs = new ArrayList<ItemVentaDTO>();
		ivs.add(iv);
		
		//v.setCantCuotas(cantCuotas);
		v.setCodigoSeguridad(1234);
		v.setCuit("2");
		v.setDni("3434");
		v.setEmpleado(gerente);
		//v.setEstado(null);
		//v.setFechaCobro(fechaCobro);
		v.setFechaVenta(LocalDate.now());
		//v.setFechaVto();
		//v.setId(id);
		v.setItems(ivs);
		v.setMedioDePago(MedioDePago.TARJETA_DEBITO);
		//v.setMontoRecibido((float)500);
		v.setNombre("Cliente");
		v.setNumeroTarjeta("12345678910111112");
		v.setPin(1234);
		v.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
		v.setTipoFact(TipoFactura.A);
		//v.setVuelto((float)300);
		
		VentaDTO v2 = new VentaDTO();
		v2=BusinessDelegate.getInstance().generarVenta(gerente, v); 
		
		System.out.println("VENTA NUEVA .- DEBITO: ");
		System.out.println("Numero Operacion:"+v2.getNroOperacion());
		System.out.println("Aprobada: "+v2.getAprobada());
		System.out.println("Producto: "+v2.getItems().get(0).getProducto().getNombre());
		System.out.println("Cantidad: "+v2.getItems().get(0).getCantidad());
		System.out.println("Total: "+v2.getTotal());

		
		
		
		
		
		/**----------------FIN DEBITO
		
		 -----------------------------------------------------------------TARJETA CREDITO*/
		 /**
		 

		VentaDTO v = new VentaDTO();
		
		ProductoDTO p = new ProductoDTO();
		p.setCodigo(1234567);
		
		ItemVentaDTO iv	= new ItemVentaDTO();
		iv.setCantidad(3);
		iv.setProducto(p);
		
		ArrayList<ItemVentaDTO> ivs = new ArrayList<ItemVentaDTO>();
		ivs.add(iv);
		
		//v.setAprobada(true);
		v.setCantCuotas(3);
		v.setCodigoSeguridad(1234);
		v.setCuit("2");
		v.setDni("3434");
		v.setEmpleado(gerente);
		//v.setEstado(null);
		//v.setFechaCobro(fechaCobro);
		v.setFechaVenta(LocalDate.now());
		v.setFechaVto("1234");
		//v.setId(id);
		v.setItems(ivs);
		v.setMedioDePago(MedioDePago.TARJETA_CREDITO);
		//v.setMontoRecibido((float)500);
		v.setNombre("Cliente");
		//v.setNroOperacion(123456789);
		v.setNumeroTarjeta("12345678910111111");
		//v.setPin(1234);
		//v.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
		v.setTipoFact(TipoFactura.A);
		//v.setTotal((float)400);
		//v.setVuelto((float)300);
		
		
		VentaDTO v2 = new VentaDTO();
		v2=BusinessDelegate.getInstance().generarVenta(gerente, v); 
		
		System.out.println("VENTA NUEVA .- CREDITO: ");
		System.out.println("Numero Operacion:"+v2.getNroOperacion());
		System.out.println("Aprobada: "+v2.getAprobada());
		System.out.println("Producto: "+v2.getItems().get(0).getProducto().getNombre());
		System.out.println("Cantidad: "+v2.getItems().get(0).getCantidad());
		System.out.println("Total: "+v2.getTotal());
		
		
		
		----------------FIN CREDITO*/
		
		System.out.println("Bien");

	}
}