package daos;

import controladores.HibernateUtil;
import entities.EmpleadoEntity;
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

	public void guardarEmpleado(Empleado empleado) {
		Transaction t = null;
		s = this.getSession();

		try {
			t = s.beginTransaction();
			s.saveOrUpdate(empleado);
			s.flush();
			t.commit();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al Guardar Empleado");
		}
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
		EmpleadoEntity pe = (EmpleadoEntity) session.createQuery("from EmpleadoEntity where dni = ?")
					.setParameter(0, dni)
					.uniqueResult();
			return EmpleadoDAO.getinstance().toNegocio(pe);
		
	}

	private Empleado toNegocio(EmpleadoEntity ee) {
		Empleado e = new Empleado();
		

		
		
		
		
		return null;
	}
	
	
	
	
	
	public Partida toNegocio(PartidaEntity pe) {
		Partida p = new Partida(pe.getId());
		p.setEsAbierta(pe.getEsAbierta());
		p.setEstado(pe.getEstado());
		p.setGanador(pe.getGanador());
		p.setFechaCreacion(pe.getFechaCreacion());
		p.setFechaActualizacion(pe.getFechaActualizacion());
		ArrayList<Juego> juegos = new ArrayList<Juego>();
		for (JuegoEntity je : pe.getJuegos()) {
			juegos.add(JuegoDAO.getInstancia().toNegocio(je));
		}
		p.setJuegos(juegos);
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		for (JugadorEntity je : pe.getJugadores()) {
			jugadores.add(JugadorDAO.getInstancia().toNegocio(je));
		}
		p.setJugadores(jugadores);
		ArrayList<Jugador> jugadoresListos = new ArrayList<Jugador>();
		for (JugadorEntity je : pe.getJugadoresListos()) {
			jugadoresListos.add(JugadorDAO.getInstancia().toNegocio(je));
		}
		p.setJugadoresListos(jugadoresListos);
		return p;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
