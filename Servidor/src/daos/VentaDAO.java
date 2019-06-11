package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controladores.HibernateUtil;
import entities.VentaEntity;
import negocio.Venta;

public class VentaDAO {
	
	
	
	
	protected static SessionFactory sf = null;
	protected static VentaDAO instancia;
	protected Session s = null;

	public static VentaDAO getinstance() {
		if (instancia == null) {
			sf = HibernateUtil.getSessionFactory();
			instancia = new VentaDAO();
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

	public void add(Venta venta) {
		Transaction t = null;
		s = this.getSession();

		try {
			t = s.beginTransaction();
			s.saveOrUpdate(VentaDAO.getinstance().toEntity(venta));
			s.flush();
			t.commit();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al guardar la venta");
		}
	}

	private VentaEntity toEntity(Venta venta) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public Venta toNegocio(VentaEntity venta) {
		// TODO Auto-generated method stub
		return null;
	}


}
