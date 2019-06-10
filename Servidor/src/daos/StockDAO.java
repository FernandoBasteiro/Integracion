package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controladores.HibernateUtil;
import negocio.Producto;
import negocio.Stock;

public class StockDAO {
	
	
	protected static SessionFactory sf = null;
	protected static StockDAO instancia;
	protected Session s = null;

	public static StockDAO getinstance() {
		if (instancia == null) {
			sf = HibernateUtil.getSessionFactory();
			instancia = new StockDAO();
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

	public void add(Stock stock) {
		Transaction t = null;
		s = this.getSession();

		try {
			t = s.beginTransaction();
			s.saveOrUpdate(stock);
			s.flush();
			t.commit();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al guardar el stock");
		}
	}

}
