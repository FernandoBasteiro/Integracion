package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controladores.HibernateUtil;
import entities.EmpleadoEntity;
import entities.NovedadEntity;
import entities.ParamGralesEntity;
import negocio.Empleado;
import negocio.Novedad;
import negocio.ParamGrales;


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
	
	
	
	
	public ArrayList<ParamGrales> getParamGrales() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
			
		String query =  "from ParamGralesEntity";
		
		Query q = session.createQuery(query);	
		@SuppressWarnings("unchecked")
		ArrayList<ParamGralesEntity> lista_entities = (ArrayList<ParamGralesEntity>) q.list();		
		ArrayList<ParamGrales> lista = new ArrayList<ParamGrales>();
		for (ParamGralesEntity paramGralesEntity : lista_entities) lista.add(ParamGralesDAO.getinstance().toNegocio(paramGralesEntity));
		return lista;
		
	}
	
	public void add(ParamGrales pg) {
		Transaction t = null;
		s = this.getSession();
		try {
			t = s.beginTransaction();
			s.merge(ParamGralesDAO.getinstance().toEntity(pg));
			//s.flush();
			t.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public ParamGralesEntity toEntity(ParamGrales pg) {
		ParamGralesEntity pgE = new ParamGralesEntity();
		pgE.setId(pg.getId());
		pgE.setClave(pg.getClave());
		pgE.setValor(pg.getValor());
		return pgE;		
	}
	
	public ParamGrales toNegocio(ParamGralesEntity pgE) {
		ParamGrales pg = new ParamGrales();
		pg.setId(pgE.getId());
		pg.setClave(pgE.getClave());
		pg.setValor(pgE.getValor());
		return pg;		
	}
	
	
	
	
	
	
	
}
