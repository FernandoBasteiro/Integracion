package daos;

import controladores.HibernateUtil;
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
	
	
}
