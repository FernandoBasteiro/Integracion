package daos;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;

import controladores.ControladorEmpleados;
import controladores.ConversorFechas;
import controladores.HibernateUtil;
import dto.EmpleadoDTO;
import enumeraciones.EstadoCivil;
import enumeraciones.EstadoEmpleado;
import enumeraciones.EstadoFactura;
import enumeraciones.EstadoVenta;
import enumeraciones.Genero;
import enumeraciones.Puesto;
import enumeraciones.TipoFactura;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;
import negocio.Empleado;
import negocio.ItemVenta;
import negocio.Producto;
import negocio.Stock;
import negocio.Venta;

public class Dummy {
	private static Dummy instancia;
	
	private Dummy( ) throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		this.probarBase();
	}
	
	public static Dummy getInstancia() throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		if (instancia == null) {
			instancia = new Dummy();
		}
		return instancia;
	}
	public static void main(String[] args) throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso
	{
		Dummy.getInstancia();
	}
	public void probarBase() throws UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		Stock stock = new Stock();
		Producto producto = new Producto();
		ItemVenta itemVenta = new ItemVenta();
		ArrayList<ItemVenta> items = new ArrayList<ItemVenta>();
		Venta venta = new Venta();
		/**stock.setCantidadDisponible(10);
		stock.setCantidadMinimo(6);
		stock.setCantidadTotal(12);
		producto.setCodigo(1234567);
		producto.setDescripcion("Veneno");
		producto.setNombre("Oreos");
		producto.setPrecio((float)45.3);
		producto.setPresentacion("2L");
		producto.setStock(stock);
		empleado.setApellido("Montero");
		empleado.setCbu("1234");
		empleado.setDni("33333444444");
		empleado.setDomicilio("Mardel");
		empleado.setEstadoEmpleado(EstadoEmpleado.ACTIVO);
		empleado.setEstadoCivil(EstadoCivil.CASADO);
		empleado.setFechaIngreso(LocalDate.now());
		empleado.setFechaEgreso(LocalDate.now());
		empleado.setFechaNacimiento(LocalDate.now());
		empleado.setGenero(Genero.FEMENINO);
		empleado.setHorasAsignadas(10);
		empleado.setEmail("mail@falso");
		empleado.setNacionalidad("Argentino");
		empleado.setPassword("1234");
		empleado.setPuesto(Puesto.CAJERO);
		empleado.setSueldoBase((float)100.3);
		empleado.setTelefono("1234");
		itemVenta.setCantidad(10);
		itemVenta.setPrecio((float)41);
		itemVenta.setProducto(producto);
		items.add(itemVenta);
		venta.setEmpleado(empleado);
		venta.setEstado(EstadoVenta.FACTURADA);
		venta.setItems(items);
		venta.setTotal((float)13.3);
		
		EmpleadoDAO.getinstance().add(empleado);
		ProductoDAO.getinstance().add(producto);
		empleado.setLegajo(1);
		VentaDAO.getinstance().add(venta);
		
		 int i=0;
		for( Empleado e : EmpleadoDAO.getinstance().getEmpleadosByEstado(EstadoEmpleado.DESVINCULADO)) {
			
			System.out.println("Empleado "+i+": "+e.getApellido()+" - Estado: "+e.getEstadoEmpleado().toString());
			i++;
		}*/
		
		
		EmpleadoDTO empleado = new EmpleadoDTO();

		empleado.setApellido("AgregadoPorGerente");
		empleado.setCbu("1234");
		empleado.setDni("000");
		empleado.setDomicilio("BA");
		empleado.setEstadoEmpleado(EstadoEmpleado.ACTIVO);
		empleado.setEstadoCivil(EstadoCivil.CASADO);
		empleado.setFechaIngreso(ConversorFechas.convertJodaToJava(LocalDate.now()));
		empleado.setFechaEgreso(ConversorFechas.convertJodaToJava(LocalDate.now()));
		empleado.setFechaNacimiento(ConversorFechas.convertJodaToJava(LocalDate.now()));
		empleado.setGenero(Genero.FEMENINO);
		empleado.setHorasAsignadas(10);
		empleado.setEmail("mail@falso");
		empleado.setNacionalidad("Argentino");
		empleado.setPassword("1234");
		empleado.setPuesto(Puesto.GERENTE);
		empleado.setSueldoBase((float)100.3);
		empleado.setTelefono("1234");
		
		
		EmpleadoDTO gerente = new EmpleadoDTO();
		gerente.setLegajo(1);
		gerente.setPassword("1234");
		gerente.setSession("3");
		gerente=ControladorEmpleados.getInstance().iniciarSesion(gerente);
		System.out.println(gerente.getApellido());
		//ControladorEmpleados.getInstance().altaEmpleado(gerente, empleado);
		
		
		
		
		
		
		//ControladorEmpleados.getInstance().listarEmpleadoPorLegajo(gerente, leg)
		
		//ControladorEmpleados.getInstance().altaEmpleado(gerente, empleado);
		
		

		
		
		
		
		
		
		System.out.println("Bien");

	}
}
