package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import controladores.HibernateUtil;
import entities.ParamGralesEntity;


public class ParamGralesDAO {

	protected static SessionFactory sf = null;
	protected static ParamGralesDAO instancia;
	protected Session s = null;

	public static ParamGralesDAO getinstance() {
		if (instancia == null) {
			sf = HibernateUtil.getSessionFactory();
			instancia = new ParamGralesDAO();
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
	
	public String getValor(String clave) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ParamGralesEntity pg = (ParamGralesEntity) session.createQuery("FROM ParamGralesEntity WHERE clave = ?").setParameter(0, clave).uniqueResult();
		if(pg==null) return null;
		else return pg.getValor();
	}
}
