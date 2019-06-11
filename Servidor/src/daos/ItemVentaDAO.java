package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controladores.HibernateUtil;
import entities.ItemVentaEntity;
import entities.ProductoEntity;
import entities.StockEntity;
import negocio.ItemVenta;

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
	
	private ItemVenta toNegocio(ItemVentaEntity ee) {
		ItemVenta e = new ItemVenta();
		
		e.setCantidad(ee.getCantidad());
		e.setPrecio(ee.getPrecio());
		e.setProducto(ProductoDAO.getinstance().toNegocio(ee.getProducto()));	
		return e;
	}

}
