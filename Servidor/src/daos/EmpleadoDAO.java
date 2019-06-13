package daos;

import controladores.HibernateUtil;
import entities.EmpleadoEntity;
import entities.VentaEntity;
import enumeraciones.EstadoEmpleado;
import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;
import enumeraciones.Puesto;
import negocio.Empleado;
import negocio.Venta;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.joda.time.LocalDate;

public class EmpleadoDAO {
	
	
	protected static SessionFactory sf = null;
	protected static EmpleadoDAO instancia;
	protected Session s = null;

	public static EmpleadoDAO getinstance() {
		if (instancia == null) {
			sf = HibernateUtil.getSessionFactory();
			instancia = new EmpleadoDAO();
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

	public void add(Empleado empleado) {
		Transaction t = null;
		s = this.getSession();
		try {
			t = s.beginTransaction();
			s.saveOrUpdate(EmpleadoDAO.getinstance().toEntity(empleado));
			s.flush();
			t.commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public EmpleadoEntity toEntity(Empleado ee) {
		EmpleadoEntity e = new EmpleadoEntity();
		e.setApellido(ee.getApellido());
		e.setCbu(ee.getCbu());
		e.setDni(ee.getDni());
		e.setDomicilio(ee.getDomicilio());
		e.setMail(ee.getEmail());
		e.setEstadoCivil(ee.getEstadoCivil());
		e.setEstado(ee.getEstadoEmpleado());
		if(ee.getFechaEgreso()!=null)
			{e.setFechaEgreso(ee.getFechaEgreso());}
		e.setFechaIngreso(ee.getFechaIngreso());
		e.setFechaNacimiento(ee.getFechaNacimiento());
		e.setGenero(ee.getGenero());
		e.setHorasAsignadas(ee.getHorasAsignadas());
		e.setLegajoEmpleado(ee.getLegajo());
		e.setNacionalidad(ee.getNacionalidad());
		e.setNombre(ee.getNombre());
		e.setPassword(ee.getPassword());
		e.setPuesto(ee.getPuesto());
		e.setSueldoBase(ee.getSueldoBase());
		e.setTelefono(ee.getTelefono());
		e.setSession(ee.getSession());
		return e;		
	}

	public Empleado getEmpleadoByLegajo(int legajo){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		EmpleadoEntity pe = (EmpleadoEntity) session.createQuery("from EmpleadoEntity where legajoEmpleado = ?")
					.setParameter(0, legajo)
					.uniqueResult();
			if(pe==null)
				return null;
			else
				return EmpleadoDAO.getinstance().toNegocio(pe);

		
	}
	
	public Empleado getEmpleadoByDni(String dni){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		EmpleadoEntity ee = (EmpleadoEntity) session.createQuery("from EmpleadoEntity where dni = ?")
					.setParameter(0, dni)
					.uniqueResult();
		if(ee==null)
			return null;
		else
			return EmpleadoDAO.getinstance().toNegocio(ee);
		
	}

	public Empleado toNegocio(EmpleadoEntity ee) {
		Empleado e = new Empleado();
		e.setApellido(ee.getApellido());
		e.setCbu(ee.getCbu());
		e.setDni(ee.getDni());
		e.setDomicilio(ee.getDomicilio());
		e.setEmail(ee.getMail());
		e.setEstadoCivil(ee.getEstadoCivil());
		e.setEstadoEmpleado(ee.getEstado());
		e.setFechaEgreso(ee.getFechaEgreso());
		e.setFechaIngreso(ee.getFechaIngreso());
		e.setFechaNacimiento(ee.getFechaNacimiento());
		e.setGenero(ee.getGenero());
		e.setHorasAsignadas(ee.getHorasAsignadas());
		e.setLegajo(ee.getLegajoEmpleado());
		e.setNacionalidad(ee.getNacionalidad());
		e.setNombre(ee.getNombre());
		
		if (ee.getPassword()!=null) {
			e.setPassword(ee.getPassword());
		}
		e.setPuesto(ee.getPuesto());
		e.setSueldoBase(ee.getSueldoBase());
		e.setTelefono(ee.getTelefono());
		e.setSession(ee.getSession());
		return e;		
	}	
	
	
	public ArrayList<Empleado> getEmpleadosByPuestoAndEstado( Puesto p, EstadoEmpleado e) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		// Armar query dinamico
		
		String query =  "from EmpleadoEntity";
		int p1 = -1, p2 = -1;
		String q1 = "", q2 = "";
		//Info de puesto
		if (p != null) {
			p1 = 0;
			q1 = " where puesto = ?";	
		}
		//info de estado empleado
		if (e != null) {
			if (p1 >= 0 ) {
				p2= 1;
				q2 = " and estado = ? ";
			}else {
				p2 = 0;
				q2 = " where estado = ? ";
			}
		}
		
		query = query + q1 + q2;
		Query q = session.createQuery(query);
		//carga parametro de puesto
		if (p1 >= 0) {
			q.setParameter(p1,p);
		}
		//carga parametro de estado empleado
		if (p2 >= 0) {
			q.setParameter(p2,e); 
		}
			
		@SuppressWarnings("unchecked")
		ArrayList<EmpleadoEntity> lista_entities = (ArrayList<EmpleadoEntity>) q.list();		
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
		for (EmpleadoEntity empleadoEntity : lista_entities) lista.add(EmpleadoDAO.getinstance().toNegocio(empleadoEntity));
		return lista;
	}

}
