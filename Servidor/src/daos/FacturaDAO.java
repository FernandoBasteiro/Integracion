package daos;

import controladores.HibernateUtil;
import entities.EmpleadoEntity;
import entities.FacturaEntity;
import entities.ItemVentaEntity;
import enumeraciones.EstadoFactura;
import enumeraciones.Puesto;
import negocio.Empleado;
import negocio.Factura;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.joda.time.LocalDate;



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
		FacturaEntity fe = new FacturaEntity();
		fe = FacturaDAO.getinstance().toEntity(factura);

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

	private FacturaEntity toEntity(Factura factura) {
		// TODO Auto-generated method stub
		return null;
	}

	public Factura getFacturaByNumero(int numero){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		FacturaEntity fe = (FacturaEntity) session.createQuery("from FacturaEntity where numero = ?")
					.setParameter(0, numero)
					.uniqueResult();
			return FacturaDAO.getinstance().toNegocio(fe);
		
	}
	
	
	private Factura toNegocio(FacturaEntity fe) {
		Factura f = new Factura();
		f.setCuit(fe.getCuit());
		f.setEstado(fe.getEstado());
		f.setFechaCobro(fe.getFechaCobro());
		f.setFechaFacturacion(fe.getFechaFacturacion());
		f.setNumero(fe.getNumero());
		f.setTipo(fe.getTipo());
		f.setVenta(VentaDAO.getinstance().toNegocio(fe.getVenta()));	
		return f;		
	}
	
	public ArrayList<Factura> getFacturasByFecha(LocalDate fecha) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<FacturaEntity> lista_entities = (ArrayList<FacturaEntity>) session.createQuery("from FacturaEntity where fechaFacturacion = ?)")
				.setParameter(0, fecha)
				.list();
		ArrayList<Factura> lista = new ArrayList<Factura>();
		for (FacturaEntity facturaEntity : lista_entities) lista.add(FacturaDAO.getinstance().toNegocio(facturaEntity));
		return lista;
	}
	
	public ArrayList<Factura> getFacturasByEstado(EstadoFactura estado) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<FacturaEntity> lista_entities = (ArrayList<FacturaEntity>) session.createQuery("from FacturaEntity where estado = ?)")
				.setParameter(0, estado)
				.list();
		ArrayList<Factura> lista = new ArrayList<Factura>();
		for (FacturaEntity facturaEntity : lista_entities) lista.add(FacturaDAO.getinstance().toNegocio(facturaEntity));
		return lista;
	}
	
	public ArrayList<Factura> getFacturasByMedioDePago(EstadoFactura estado) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<FacturaEntity> lista_entities = (ArrayList<FacturaEntity>) session.createQuery("from FacturaEntity where venta. = ?)")
				.setParameter(0, estado)
				.list();
		ArrayList<Factura> lista = new ArrayList<Factura>();
		for (FacturaEntity facturaEntity : lista_entities) lista.add(FacturaDAO.getinstance().toNegocio(facturaEntity));
		return lista;
	}
}
