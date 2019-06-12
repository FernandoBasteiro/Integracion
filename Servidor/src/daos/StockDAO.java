package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controladores.HibernateUtil;
import entities.ProductoEntity;
import entities.StockEntity;
import entities.VentaEntity;
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
			s.saveOrUpdate(StockDAO.getinstance().toEntity(stock));
			s.flush();
			t.commit();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al guardar el stock");
		}
	}

	public StockEntity toEntity(Stock ee) {
		StockEntity e = new StockEntity();
		e.setCantidadMinima(ee.getCantidadMinimo());
		e.setCantidadTotal(ee.getCantidadTotal());
		e.setCantidadDisponible(ee.getCantidadDisponible());
		return e;
	}

	public Stock toNegocio(StockEntity ee) {
		Stock e = new Stock();
		e.setCantidadMinimo(ee.getCantidadMinima());
		e.setCantidadTotal(ee.getCantidadTotal());
		e.setCantidadDisponible(ee.getCantidadDisponible());
		return e;
	}
	
}
