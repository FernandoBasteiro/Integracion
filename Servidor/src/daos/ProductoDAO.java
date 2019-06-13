package daos;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import controladores.HibernateUtil;
import entities.EmpleadoEntity;
import entities.ProductoEntity;
import entities.StockEntity;
import enumeraciones.Puesto;
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
		e.setPresentacion(ee.getPresentacion());
		e.setPrecio(ee.getPrecio());
		e.setStock(StockDAO.getinstance().toEntity(ee.getStock()));				
		return e;	
	}
	
	public Producto toNegocio(ProductoEntity ee) {
		Producto e = new Producto();
		e.setCodigo(ee.getCodigo());
		e.setNombre(ee.getNombre());
		e.setDescripcion(ee.getDescripcion());
		e.setPresentacion(ee.getPresentacion());
		e.setPrecio(ee.getPrecio());
		e.setStock(StockDAO.getinstance().toNegocio(ee.getStock()));				
		return e;		
	}	
	
	
	public ArrayList<Producto> getProductoByNombre(String nombre) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<ProductoEntity> lista_entities = (ArrayList<ProductoEntity>) session.createQuery("from ProductoEntity where nombre like  '%'||?||'%'")
				.setParameter(0, nombre)
				.list();
		ArrayList<Producto> lista = new ArrayList<Producto>();
		for (ProductoEntity productoEntity : lista_entities) lista.add(ProductoDAO.getinstance().toNegocio(productoEntity));
		return lista;
	}
	
	public ArrayList<Producto> getProductoByCodigo(Long codigo){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<ProductoEntity> lista_entities = (ArrayList<ProductoEntity>) session.createQuery("from ProductoEntity where codigo = ?")
					.setParameter(0, codigo)
					.list();
		ArrayList<Producto> lista = new ArrayList<Producto>();
		for (ProductoEntity productoEntity : lista_entities) {
			lista.add(ProductoDAO.getinstance().toNegocio(productoEntity));}
		return lista;
		
	}
	
	public ArrayList<Producto> getProductos() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<ProductoEntity> lista_entities = (ArrayList<ProductoEntity>) session.createQuery("from ProductoEntity ")
				.list();
		ArrayList<Producto> lista = new ArrayList<Producto>();
		for (ProductoEntity productoEntity : lista_entities) {
			lista.add(ProductoDAO.getinstance().toNegocio(productoEntity));
		}
		return lista;
	}
	
}
