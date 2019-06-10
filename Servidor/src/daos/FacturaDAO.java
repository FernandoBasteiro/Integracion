package daos;

import controladores.HibernateUtil;
import negocio.Factura;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class FacturaDAO {

	protected static SessionFactory sf = null;
	protected static FacturaDAO instancia;
	protected Session s = null;

	public static FacturaDAO getinstance() {
		if (instancia == null) {
			sf = HibernateUtil.getSessionFactory();
			instancia = new FacturaDAO();
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

	public void add(Factura factura) {
		Transaction t = null;
		s = this.getSession();

		try {
			t = s.beginTransaction();
			s.saveOrUpdate(factura);
			s.flush();
			t.commit();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al generar la Factura");
		}
	}

}
