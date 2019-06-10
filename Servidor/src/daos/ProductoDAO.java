package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import controladores.HibernateUtil;
import negocio.Producto;


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
			s.saveOrUpdate(producto);
			s.flush();
			t.commit();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al guardar el producto");
		}
	}

}
