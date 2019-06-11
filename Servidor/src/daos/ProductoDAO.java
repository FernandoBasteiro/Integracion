package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import controladores.HibernateUtil;
import entities.EmpleadoEntity;
import entities.ProductoEntity;
import entities.StockEntity;
import negocio.Empleado;
import negocio.Producto;
import negocio.Stock;


public class ProductoDAO {
	

	protected static SessionFactory sf = null;
	protected static ProductoDAO instancia;
	protected Session s = null;

	public static ProductoDAO getinstance() {
		if (instancia == null) {
			sf = HibernateUtil.getSessionFactory();
			instancia = new ProductoDAO();
		}
		return instancia;
	}

	public Session getSession() {
		if (s == null || !s.isOpen())
			s = sf.openSession();

		return s;
	}

	public void closeSession() {
		if (s.isOpen())
			s.close();
	}

	public void add(Producto producto) {
		Transaction t = null;
		s = this.getSession();


		try {
			t = s.beginTransaction();
			s.saveOrUpdate(ProductoDAO.getinstance().toEntity(producto));
			s.flush();
			t.commit();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al guardar el producto");
		}
	}

	public ProductoEntity toEntity(Producto ee) {
		ProductoEntity e = new ProductoEntity();
		e.setCodigo(ee.getCodigo());
		e.setNombre(ee.getNombre());
		e.setDescripcion(ee.getDescripcion());
		e.setPresentacion(ee.getDescripcion());
		e.setPrecio(ee.getPrecio());
		e.setStock(StockDAO.getinstance().toEntity(ee.getStock()));				
		return e;	
	}
	
	public Producto toNegocio(ProductoEntity ee) {
		Producto e = new Producto();
		e.setCodigo(ee.getCodigo());
		e.setNombre(ee.getNombre());
		e.setDescripcion(ee.getDescripcion());
		e.setPresentacion(ee.getDescripcion());
		e.setPrecio(ee.getPrecio());
		e.setStock(StockDAO.getinstance().toNegocio(ee.getStock()));				
		return e;		
	}	
	
	
	
	
	
	
}
