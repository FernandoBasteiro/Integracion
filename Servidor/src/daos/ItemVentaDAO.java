package daos;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controladores.HibernateUtil;
import entities.ItemVentaEntity;
import entities.ProductoEntity;
import entities.StockEntity;
import negocio.ItemVenta;
import negocio.Producto;

public class ItemVentaDAO {
	
	
	protected static SessionFactory sf = null;
	protected static ItemVentaDAO instancia;
	protected Session s = null;

	public static ItemVentaDAO getinstance() {
		if (instancia == null) {
			sf = HibernateUtil.getSessionFactory();
			instancia = new ItemVentaDAO();
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

	public void add(ItemVenta itemVenta) {
		Transaction t = null;
		s = this.getSession();

		try {
			t = s.beginTransaction();
			s.saveOrUpdate(ItemVentaDAO.getinstance().toEntity(itemVenta));
			s.flush();
			t.commit();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al guardar el item venta");
		}
	}

	private ItemVentaEntity toEntity(ItemVenta ee) {
		ItemVentaEntity e = new ItemVentaEntity();
		
		e.setCantidad(ee.getCantidad());
		e.setPrecio(ee.getPrecio());
		e.setProducto(ProductoDAO.getinstance().toEntity(ee.getProducto()));
		return e;
	}
	
	public ItemVenta toNegocio(ItemVentaEntity ee) {
		ItemVenta e = new ItemVenta();
		
		e.setCantidad(ee.getCantidad());
		e.setPrecio(ee.getPrecio());
		e.setProducto(ProductoDAO.getinstance().toNegocio(ee.getProducto()));	
		return e;
	}
	
	public ArrayList<ItemsVenta> getItems() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<ItemsVenta> lista_entities = (ArrayList<ItemsVenta>) session.createQuery("from ItemsVentaEntity where nombre like %?%)")
				.setParameter(0, nombre)
				.list();
		ArrayList<Producto> lista = new ArrayList<Producto>();
		for (ProductoEntity productoEntity : lista_entities) lista.add(ProductoDAO.getinstance().toNegocio(productoEntity));
		return lista;
	}
}
