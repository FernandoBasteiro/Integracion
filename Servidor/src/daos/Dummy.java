package daos;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;

import controladores.HibernateUtil;
import enumeraciones.EstadoCivil;
import enumeraciones.EstadoEmpleado;
import enumeraciones.EstadoFactura;
import enumeraciones.EstadoVenta;
import enumeraciones.Genero;
import enumeraciones.Puesto;
import enumeraciones.TipoFactura;
import negocio.Empleado;
import negocio.ItemVenta;
import negocio.Producto;
import negocio.Stock;
import negocio.Venta;

public class Dummy {
	private static Dummy instancia;
	
	private Dummy( ) {
		this.probarBase();
	}
	
	public static Dummy getInstancia() {
		if (instancia == null) {
			instancia = new Dummy();
		}
		return instancia;
	}
	public static void main(String[] args)
	{
		Dummy.getInstancia();
	}
	public void probarBase() {
		Stock stock = new Stock();
		Producto producto = new Producto();
		Empleado empleado = new Empleado();
		ItemVenta itemVenta = new ItemVenta();
		ArrayList<ItemVenta> items = new ArrayList<ItemVenta>();
		Venta venta = new Venta();
		stock.setCantidadDisponible(10);
		stock.setCantidadMinimo(6);
		stock.setCantidadTotal(12);
		producto.setCodigo(1234);
		producto.setDescripcion("Veneno");
		producto.setNombre("CocaCola 2L");
		producto.setPrecio((float)45.3);
		producto.setPresentacion("2L");
		producto.setStock(stock);
		empleado.setApellido("Sarasa");
		empleado.setCbu("1234");
		empleado.setDni("33333");
		empleado.setDomicilio("Mardel");
		empleado.setEstadoEmpleado(EstadoEmpleado.LICENCIA_PAGA);
		empleado.setEstadoCivil(EstadoCivil.CASADO);
		empleado.setFechaIngreso(LocalDate.now());
		empleado.setFechaEgreso(LocalDate.now());
		empleado.setFechaNacimiento(LocalDate.now());
		empleado.setGenero(Genero.FEMENINO);
		empleado.setHorasAsignadas(10);
		empleado.setEmail("mail@falso");
		empleado.setNacionalidad("Argentino");
		empleado.setPassword("1234");
		empleado.setPuesto(Puesto.GERENTE);
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
		
	
		System.out.println("Bien");

	}
}
