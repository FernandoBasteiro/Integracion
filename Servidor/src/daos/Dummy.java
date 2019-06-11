package daos;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;

import controladores.HibernateUtil;
import entities.EmpleadoEntity;
import entities.FacturaEntity;
import entities.ItemVentaEntity;
import entities.ProductoEntity;
import entities.StockEntity;
import entities.VentaEntity;
import enumeraciones.EstadoCivil;
import enumeraciones.EstadoEmpleado;
import enumeraciones.EstadoFactura;
import enumeraciones.EstadoVenta;
import enumeraciones.Genero;
import enumeraciones.Puesto;
import enumeraciones.TipoFactura;

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
	
	public void probarBase() {
		StockEntity stock = new StockEntity();
		ProductoEntity producto = new ProductoEntity();
		EmpleadoEntity empleado = new EmpleadoEntity();
		FacturaEntity factura = new FacturaEntity();
		ItemVentaEntity itemVenta = new ItemVentaEntity();
		ArrayList<ItemVentaEntity> items = new ArrayList<ItemVentaEntity>();
		VentaEntity venta = new VentaEntity();
		stock.setCantidadDisponible(10);
		stock.setCantidadMinima(6);
		stock.setCantidadTotal(12);
		producto.setCodigo(1234);
		producto.setDescripcion("Veneno");
		producto.setNombre("CocaCola 2L");
		producto.setPrecio((float)40.3);
		producto.setPresentacion("2L");
		producto.setStock(stock);
		empleado.setApellido("Sarasa");
		empleado.setCbu("1234");
		empleado.setDni("12345");
		empleado.setDomicilio("Mardel");
		empleado.setEstado(EstadoEmpleado.LICENCIA_PAGA);
		empleado.setEstadoCivil(EstadoCivil.CASADO);
		empleado.setFechaIngreso(LocalDate.now());
		empleado.setFechaNacimiento(LocalDate.now());
		empleado.setGenero(Genero.FEMENINO);
		empleado.setHorasAsignadas(10);
		empleado.setMail("mail@falso");
		empleado.setNacionalidad("Argentino");
		empleado.setPassword("1234");
		empleado.setPuesto(Puesto.GERENTE);
		empleado.setSueldoBase((float)13.3);
		empleado.setTelefono("1234");
		itemVenta.setCantidad(13);
		itemVenta.setPrecio((float)41);
		itemVenta.setProducto(producto);
		items.add(itemVenta);
		venta.setAprobada(true);
		venta.setCantCuotas(null);
		venta.setEmpleado(empleado);
		venta.setEstado(EstadoVenta.FACTURADA);
		venta.setFechaVenta(Calendar.getInstance());
		venta.setItems(items);
		venta.setNroOperacion(1234);
		venta.setTotal((float)13.3);
		venta.setUltimos4DigitosTarjeta(1234);
		factura.setCuit(null);
		factura.setEstado(EstadoFactura.PENDIENTE);
		factura.setFechaCobro(Calendar.getInstance());
		factura.setFechaFacturacion(Calendar.getInstance());
		factura.setTipo(TipoFactura.C);
		factura.setVenta(venta);
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(stock);
		session.getTransaction().commit();
		session.beginTransaction();
		session.save(producto);
		session.getTransaction().commit();
		session.beginTransaction();
		session.save(empleado);
		session.getTransaction().commit();
		session.beginTransaction();
		session.save(venta);
		session.getTransaction().commit();
		session.beginTransaction();
		session.save(itemVenta);
		session.getTransaction().commit();
		session.beginTransaction();
		Integer numero = (Integer)session.save(factura);
		session.getTransaction().commit();
		session.close();
		System.out.println(numero);

	}
}
