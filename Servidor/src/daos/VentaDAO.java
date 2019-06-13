package daos;

import java.util.ArrayList;
import java.util.Locale;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import controladores.HibernateUtil;
import entities.ItemVentaEntity;
import entities.VentaEntity;
import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;
import negocio.ItemVenta;
import negocio.Venta;
import negocio.VentaEfectivo;
import negocio.VentaTarjetaCredito;
import negocio.VentaTarjetaDebito;

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

	public void add(VentaEfectivo venta) {
		Transaction t = null;
		s = this.getSession();
		try {
			t = s.beginTransaction();
			s.saveOrUpdate(VentaDAO.getinstance().toEntity(venta));
			t.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void add(VentaTarjetaCredito venta) {
		Transaction t = null;
		s = this.getSession();
		try {
			t = s.beginTransaction();
			s.saveOrUpdate(VentaDAO.getinstance().toEntity(venta));
			t.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void add(VentaTarjetaDebito venta) {
		Transaction t = null;
		s = this.getSession();
		try {
			t = s.beginTransaction();
			s.saveOrUpdate(VentaDAO.getinstance().toEntity(venta));
			t.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	private VentaEntity toEntity(VentaEfectivo ee) {
		ArrayList<ItemVentaEntity> items = new ArrayList<ItemVentaEntity>();
		VentaEntity e = new VentaEntity();
		
		for (ItemVenta iv : ee.getItems()) {
			items.add(ItemVentaDAO.getinstance().toEntity(iv));
		}
		
		e.setItemVentas(items);
		e.setCuit(ee.getCuit());
		e.setEmpleado(EmpleadoDAO.getinstance().toEntity(ee.getEmpleado()));	
		e.setEstado(ee.getEstado());
		e.setFechaCobro(ee.getFechaCobro());
		e.setFechaVenta(ee.getFechaVenta());
		e.setId(ee.getId());
		e.setTipo(ee.getTipoFact());
		e.setTotal(ee.getTotal());
		e.setMedioDePago(MedioDePago.EFECTIVO);
			 			
		return e;
		
	}
	
	private VentaEntity toEntity(VentaTarjetaDebito ee) {
		ArrayList<ItemVentaEntity> items = new ArrayList<ItemVentaEntity>();
		VentaEntity e = new VentaEntity();
		
		for (ItemVenta iv : ee.getItems()) {
			items.add(ItemVentaDAO.getinstance().toEntity(iv));
		}
		
		e.setItemVentas(items);
		e.setCuit(ee.getCuit());
		e.setEmpleado(EmpleadoDAO.getinstance().toEntity(ee.getEmpleado()));	
		e.setEstado(ee.getEstado());
		e.setFechaCobro(ee.getFechaCobro());
		e.setFechaVenta(ee.getFechaVenta());
		e.setId(ee.getId());
		e.setTipo(ee.getTipoFact());
		e.setTotal(ee.getTotal());
		e.setMedioDePago(MedioDePago.TARJETA_DEBITO);
		e.setNroOperacion(ee.getNroOperacion());
		e.setUltimos4DigitosTarjeta(ee.getNumeroTarjeta().substring(ee.getNumeroTarjeta().length()-4,ee.getNumeroTarjeta().length()));
		 		 			
		return e;
		
	}

	private VentaEntity toEntity(VentaTarjetaCredito ee) {
		ArrayList<ItemVentaEntity> items = new ArrayList<ItemVentaEntity>();
		VentaEntity e = new VentaEntity();
		
		for (ItemVenta iv : ee.getItems()) {
			items.add(ItemVentaDAO.getinstance().toEntity(iv));
		}
		
		e.setItemVentas(items);
		e.setCuit(ee.getCuit());
		e.setEmpleado(EmpleadoDAO.getinstance().toEntity(ee.getEmpleado()));	
		e.setEstado(ee.getEstado());
		e.setFechaCobro(ee.getFechaCobro());
		e.setFechaVenta(ee.getFechaVenta());
		e.setId(ee.getId());
		e.setTipo(ee.getTipoFact());
		e.setTotal(ee.getTotal());
		e.setMedioDePago(MedioDePago.TARJETA_CREDITO);
		e.setNroOperacion(ee.getNroOperacion());
		e.setUltimos4DigitosTarjeta(ee.getNumeroTarjeta().substring(ee.getNumeroTarjeta().length()-4,ee.getNumeroTarjeta().length()));
		e.setCantCuotas(ee.getCantCuotas());
		 			
		return e;
		
	}
	public Venta toNegocio(VentaEntity ee) {
		
		ArrayList<ItemVenta> items = new ArrayList<ItemVenta>();
		
		for (ItemVentaEntity iv : ee.getItemVentas()) {
			items.add(ItemVentaDAO.getinstance().toNegocio(iv));
		}
		Venta v = null;
		switch(ee.getMedioDePago()) {
		case EFECTIVO:
			v = new VentaEfectivo(ee.getId(),  ee.getFechaVenta(), items,  EmpleadoDAO.getinstance().toNegocio(ee.getEmpleado()),
					ee.getEstado(), ee.getTotal(), null, null, ee.getTipo(), 
					ee.getCuit(), ee.getFechaCobro());
			break;
		case TARJETA_DEBITO:
			v = new VentaTarjetaDebito(ee.getId(),ee.getFechaVenta(), items,  EmpleadoDAO.getinstance().toNegocio(ee.getEmpleado()),
					ee.getEstado(), ee.getTotal(), ee.getUltimos4DigitosTarjeta(), null, null, null, null, ee.getNroOperacion(), 
					ee.isAprobada(), null, null, ee.getTipo(), ee.getCuit(), ee.getFechaCobro());			
			break;
		case TARJETA_CREDITO:
			v = new VentaTarjetaCredito(ee.getId(),ee.getFechaVenta(), items,  EmpleadoDAO.getinstance().toNegocio(ee.getEmpleado()),
					ee.getEstado(), ee.getTotal(), ee.getUltimos4DigitosTarjeta(), null, null, null, null, ee.getNroOperacion(), 
					ee.isAprobada(), ee.getCantCuotas(), ee.getTipo(), ee.getCuit(), ee.getFechaCobro());
			break;
		}
		return v;
		
	}

	public ArrayList<Venta> getVentaByNumeroDeOperacion(Integer numero) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<VentaEntity> lista_entities = (ArrayList<VentaEntity>) session.createQuery("from VentaEntity where nroOperacion=?)")
				.setParameter(0, numero)
				.list();
		ArrayList<Venta> lista = new ArrayList<Venta>();
		for (VentaEntity ventaEntity : lista_entities) lista.add(VentaDAO.getinstance().toNegocio(ventaEntity));
		return lista;
	}
	
	public ArrayList<Venta> getVentaByIdVenta(Integer id) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<VentaEntity> lista_entities = (ArrayList<VentaEntity>) session.createQuery("from VentaEntity where id=?")
				.setParameter(0, id)
				.list();
		ArrayList<Venta> lista = new ArrayList<Venta>();
		for (VentaEntity ventaEntity : lista_entities) lista.add(VentaDAO.getinstance().toNegocio(ventaEntity));
		return lista;
	}
	
	public ArrayList<Venta> getVentasByEstadoFechaMedioDePago(LocalDate f, EstadoVenta e, MedioDePago m) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		// Armar query dinamico
		
		String query =  "from VentaEntity";
		int p1 = -1, p2 = -1, p3 = -1;
		String q1 = "", q2 = "", q3 = "";
		//Info de fecha
		if (f != null) {
			p1 = 0;
			q1 = " where fechaVenta = ? ";	
		}
		//info de estado venta
		if (e != null) {
			if (p1 >= 0 ) {
				p2= 1;
				q2 = " and estadoVenta = ? ";
			}else {
				p2 = 0;
				q2 = " where estadoVenta = ? ";
			}
		}
		//info de medio de pago
		if (m != null) {
			if (p1 >= 0 && p2 >= 0) {
				p3 = 2;
				q3 = " and medioDePago = ? " ;
			}else {
					if (p1 >= 0 || p2 >= 0) {
						p3 = 1;
						q3 = " and medioDePago = ? " ;
					} else {
						p3 = 0;
						q3 = " where medioDePago = ? " ;
					}
			}
		}
		query = query + q1 + q2 + q3;
		Query q = session.createQuery(query);
		//carga parametro de fecha de venta
		if (p1 >= 0) {
			
			q.setParameter(p1,f.toDateTime(LocalTime.MIDNIGHT).toCalendar(Locale.getDefault()));
		}
		//carga parametro de estado venta
		if (p2 >= 0) {
			q.setParameter(p2,e);
		}
		//carga parametro de medio de pago
		if (p3 >= 0) {
			q.setParameter(p3,m);
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<VentaEntity> lista_entities = (ArrayList<VentaEntity>) q.list();		
		ArrayList<Venta> lista = new ArrayList<Venta>();
		for (VentaEntity ventaEntity : lista_entities) lista.add(VentaDAO.getinstance().toNegocio(ventaEntity));
		return lista;
	}
}
