package controladores;

import org.joda.time.LocalDate;

import daos.EmpleadoDAO;
import daos.ProductoDAO;
import enumeraciones.EstadoCivil;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Genero;
import enumeraciones.Puesto;
import negocio.Empleado;
import negocio.Producto;
import negocio.Stock;

public class DB_Inicial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//empleados();
		//productos();


	}
	/*
	public static void productos() {
		
		Stock stock = new Stock();
		Producto producto = new Producto();
		
		//
		stock.setCantidadDisponible(100);
		stock.setCantidadMinimo(10);
		stock.setCantidadTotal(100);
		producto.setCodigo(1000);
		producto.setDescripcion("Caseras");
		producto.setNombre("Hamburguesas Patty");
		producto.setPrecio((float)45.3);
		producto.setPresentacion("Envase");
		producto.setStock(stock);
		ProductoDAO.getinstance().add(producto);
		
		//
		stock.setCantidadDisponible(200);
		stock.setCantidadMinimo(20);
		stock.setCantidadTotal(200);
		producto.setCodigo(1001);
		producto.setDescripcion("Girasol Puro");
		producto.setNombre("Aceite Cocinero");
		producto.setPrecio((float)58.1);
		producto.setPresentacion("Botella 1L");
		producto.setStock(stock);
		ProductoDAO.getinstance().add(producto);
		
		//
		stock.setCantidadDisponible(200);
		stock.setCantidadMinimo(10);
		stock.setCantidadTotal(200);
		producto.setCodigo(1002);
		producto.setDescripcion("Doble Carolina");
		producto.setNombre("Arroz Gallo");
		producto.setPrecio((float)25.1);
		producto.setPresentacion("Paquete 1Kg");
		producto.setStock(stock);
		ProductoDAO.getinstance().add(producto);
				
		//
		stock.setCantidadDisponible(100);
		stock.setCantidadMinimo(10);
		stock.setCantidadTotal(100);
		producto.setCodigo(1003);
		producto.setDescripcion("Sin Sal");
		producto.setNombre("Mayonesa Hellmanns");
		producto.setPrecio((float)34.1);
		producto.setPresentacion("Frasco 0,5Kg");
		producto.setStock(stock);
		ProductoDAO.getinstance().add(producto);
		
		//
		stock.setCantidadDisponible(100);
		stock.setCantidadMinimo(10);
		stock.setCantidadTotal(100);
		producto.setCodigo(1004);
		producto.setDescripcion("Sin Sal");
		producto.setNombre("Mostaza Hellmanns");
		producto.setPrecio((float)44.1);
		producto.setPresentacion("Frasco 0,5Kg");
		producto.setStock(stock);
		ProductoDAO.getinstance().add(producto);
		
		
		
	}
	public static void empleados() {
		
		Empleado empleado = new Empleado();
		
		//
		empleado.setApellido("Sarasa");
		empleado.setNombre("Paula");
		empleado.setCbu("1234");
		empleado.setDni("30787333");
		empleado.setDomicilio("Pinamar");
		empleado.setEstadoEmpleado(EstadoEmpleado.ACTIVO);
		empleado.setEstadoCivil(EstadoCivil.CASADO);
		empleado.setFechaIngreso(LocalDate.now());
		empleado.setFechaEgreso(LocalDate.now());
		empleado.setFechaNacimiento(LocalDate.now());
		empleado.setGenero(Genero.FEMENINO);
		empleado.setHorasAsignadas(10);
		empleado.setEmail("p.sarasa@uade");
		empleado.setNacionalidad("Argentina");
		empleado.setPassword("1234");
		empleado.setPuesto(Puesto.GERENTE);
		empleado.setSueldoBase((float)1000.3);
		empleado.setTelefono("1567897665");
		EmpleadoDAO.getinstance().add(empleado);
		
		//
		empleado.setApellido("Marcelo");
		empleado.setNombre("Gallardo");
		empleado.setCbu("12345678");
		empleado.setDni("31787333");
		empleado.setDomicilio("Buenos Aires");
		empleado.setEstadoEmpleado(EstadoEmpleado.ACTIVO);
		empleado.setEstadoCivil(EstadoCivil.CASADO);
		empleado.setFechaIngreso(LocalDate.now());
		empleado.setFechaEgreso(LocalDate.now());
		empleado.setFechaNacimiento(LocalDate.now());
		empleado.setGenero(Genero.MASCULINO);
		empleado.setHorasAsignadas(10);
		empleado.setEmail("m.gallardo@uade");
		empleado.setNacionalidad("Argentino");
		empleado.setPassword("1234");
		empleado.setPuesto(Puesto.CAJERO);
		empleado.setSueldoBase((float)1000.3);
		empleado.setTelefono("1567897665");
		EmpleadoDAO.getinstance().add(empleado);
		
		//
		empleado.setApellido("Enzo");
		empleado.setNombre("Francescoli");
		empleado.setCbu("12345678910");
		empleado.setDni("32787333");
		empleado.setDomicilio("Buenos Aires");
		empleado.setEstadoEmpleado(EstadoEmpleado.ACTIVO);
		empleado.setEstadoCivil(EstadoCivil.SOLTERO);
		empleado.setFechaIngreso(LocalDate.now());
		empleado.setFechaEgreso(LocalDate.now());
		empleado.setFechaNacimiento(LocalDate.now());
		empleado.setGenero(Genero.MASCULINO);
		empleado.setHorasAsignadas(10);
		empleado.setEmail("e.francescoli@uade");
		empleado.setNacionalidad("Argentino");
		empleado.setPassword("1234");
		empleado.setPuesto(Puesto.SUPERVISOR);
		empleado.setSueldoBase((float)1000.3);
		empleado.setTelefono("1567897665");
		EmpleadoDAO.getinstance().add(empleado);
		
		//
		empleado.setApellido("Juan");
		empleado.setNombre("Segurola");
		empleado.setCbu("12345678910");
		empleado.setDni("32787333");
		empleado.setDomicilio("Vicente Lopez");
		empleado.setEstadoEmpleado(EstadoEmpleado.ACTIVO);
		empleado.setEstadoCivil(EstadoCivil.SOLTERO);
		empleado.setFechaIngreso(LocalDate.now());
		empleado.setFechaEgreso(LocalDate.now());
		empleado.setFechaNacimiento(LocalDate.now());
		empleado.setGenero(Genero.MASCULINO);
		empleado.setHorasAsignadas(10);
		empleado.setEmail("j.segurola@uade");
		empleado.setNacionalidad("Argentino");
		empleado.setPassword("1234");
		empleado.setPuesto(Puesto.SEGURIDAD);
		empleado.setSueldoBase((float)1000.3);
		empleado.setTelefono("1567897665");
		EmpleadoDAO.getinstance().add(empleado);
		
		
		//
		empleado.setApellido("Ana");
		empleado.setNombre("Repositovich");
		empleado.setCbu("12345678910");
		empleado.setDni("32787333");
		empleado.setDomicilio("Capital Federal");
		empleado.setEstadoEmpleado(EstadoEmpleado.ACTIVO);
		empleado.setEstadoCivil(EstadoCivil.SOLTERO);
		empleado.setFechaIngreso(LocalDate.now());
		empleado.setFechaEgreso(LocalDate.now());
		empleado.setFechaNacimiento(LocalDate.now());
		empleado.setGenero(Genero.FEMENINO);
		empleado.setHorasAsignadas(10);
		empleado.setEmail("a.repo@uade");
		empleado.setNacionalidad("Argentina");
		empleado.setPassword("1234");
		empleado.setPuesto(Puesto.REPOSITOR);
		empleado.setSueldoBase((float)1000.3);
		empleado.setTelefono("1567897665");
		EmpleadoDAO.getinstance().add(empleado);
	
		
	}
	*/
}










