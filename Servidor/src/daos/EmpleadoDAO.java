package daos;

import controladores.HibernateUtil;
import entities.EmpleadoEntity;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Puesto;
import negocio.Empleado;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
			System.out.println("Error al Guardar Empleado");
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
		e.setFechaEgreso(ee.getFechaEgreso());
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
		return e;		
	}

	public Empleado getEmpleadoByLegajo(int legajo){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		EmpleadoEntity pe = (EmpleadoEntity) session.createQuery("from EmpleadoEntity where legajo = ?")
					.setParameter(0, legajo)
					.uniqueResult();
			return EmpleadoDAO.getinstance().toNegocio(pe);
		
	}
	
	public Empleado getEmpleadoByDni(String dni){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		EmpleadoEntity ee = (EmpleadoEntity) session.createQuery("from EmpleadoEntity where dni = ?")
					.setParameter(0, dni)
					.uniqueResult();
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
		e.setPassword(ee.getPassword());
		e.setPuesto(ee.getPuesto());
		e.setSueldoBase(ee.getSueldoBase());
		e.setTelefono(ee.getTelefono());
		return e;		
	}	
	
	public ArrayList<Empleado> getEmpleadosByPuesto(Puesto puesto) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<EmpleadoEntity> lista_entities = (ArrayList<EmpleadoEntity>) session.createQuery("from EmpleadoEntity where puesto = ?)")
				.setParameter(0, puesto.getNombre())
				.list();
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
		for (EmpleadoEntity empleadoEntity : lista_entities) lista.add(EmpleadoDAO.getinstance().toNegocio(empleadoEntity));
		return lista;
	}
	
	public ArrayList<Empleado> getEmpleadosByPuesto(EstadoEmpleado estado) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<EmpleadoEntity> lista_entities = (ArrayList<EmpleadoEntity>) session.createQuery("from EmpleadoEntity where estado = ?)")
				.setParameter(0, estado.getNombre())
				.list();
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
		for (EmpleadoEntity empleadoEntity : lista_entities) lista.add(EmpleadoDAO.getinstance().toNegocio(empleadoEntity));
		return lista;
	}
	
}
