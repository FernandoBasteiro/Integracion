package daos;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controladores.HibernateUtil;
import entities.ItemVentaEntity;
import entities.ProductoEntity;
import entities.VentaEntity;
import enumeraciones.MedioDePago;
import negocio.ItemVenta;
import negocio.Producto;
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

	public void add(Venta venta) {
		Transaction t = null;
		s = this.getSession();

		try {
			t = s.beginTransaction();
			s.saveOrUpdate(VentaDAO.getinstance().toEntity(venta));
			t.commit();
			//s.flush();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private VentaEntity toEntity(Venta ee) {
		ArrayList<ItemVentaEntity> items = new ArrayList<ItemVentaEntity>();
		VentaEntity e = new VentaEntity();
		
		for (ItemVenta iv : ee.getItems()) {
			items.add(ItemVentaDAO.getinstance().toEntity(iv));
		}
		
		e.setCuit(ee.getCuit());
		e.setEmpleado(EmpleadoDAO.getinstance().toEntity(ee.getEmpleado()));	
		e.setEstado(ee.getEstado());
		e.setFechaCobro(ee.getFechaCobro());
		e.setFechaVenta(ee.getFechaVenta());
		e.setId(ee.getId());
		e.setTipo(ee.getTipoFact());
		e.setTotal(ee.getTotal());
		if(ee instanceof VentaEfectivo) {
			e.setMedioDePago(MedioDePago.EFECTIVO);
		}
		if(ee instanceof VentaTarjetaCredito) {
			e.setMedioDePago(MedioDePago.TARJETA_CREDITO);
		}
		if(ee instanceof VentaTarjetaDebito) {
			e.setMedioDePago(MedioDePago.TARJETA_DEBITO);
		}	 			
		return e;
		
	}

	public Venta toNegocio(VentaEntity ee) {
		
		ArrayList<ItemVenta> items = new ArrayList<ItemVenta>();
		
		
		for (ItemVentaEntity iv : ee.getItemVentas()) {
			items.add(ItemVentaDAO.getinstance().toNegocio(iv));
		}
		Venta e = new Venta();
		e.setItems(items);
		e.setCuit(ee.getCuit());
		e.setEmpleado(EmpleadoDAO.getinstance().toNegocio(ee.getEmpleado()));	
		e.setEstado(ee.getEstado());
		e.setFechaCobro(ee.getFechaCobro());
		e.setFechaVenta(ee.getFechaVenta());
		e.setId(ee.getId());
		e.setTipoFact(ee.getTipo());
		e.setTotal(ee.getTotal());
			
		return e;
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
		ArrayList<VentaEntity> lista_entities = (ArrayList<VentaEntity>) session.createQuery("from VentaEntity where id=?)")
				.setParameter(0, id)
				.list();
		ArrayList<Venta> lista = new ArrayList<Venta>();
		for (VentaEntity ventaEntity : lista_entities) lista.add(VentaDAO.getinstance().toNegocio(ventaEntity));
		return lista;
	}
}
