package delegado;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import dto.EmpleadoDTO;
import dto.FacturaDTO;
import dto.ProductoDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.MedioDePago;
import enumeraciones.Puesto;
import excepciones.ComunicacionException;
import interfaces.InterfazRemota;

public class BusinessDelegate {
	private InterfazRemota ir;
	private static BusinessDelegate instance;
	
	public static BusinessDelegate getInstance() throws ComunicacionException{
		if(instance==null){
			instance = new BusinessDelegate(); 
		}
		return instance;
	}
	
	private BusinessDelegate() throws ComunicacionException{
		try {
			ir = (InterfazRemota) Naming.lookup("//127.0.0.1/Super");
		} catch (MalformedURLException e) {
			throw new ComunicacionException("La direccion especificada no es correcta");
		} catch (RemoteException e) {

		} catch (NotBoundException e) {
			throw new ComunicacionException("El servidor no esta disponible");		
		}
	}
	
	public EmpleadoDTO iniciarSesion(EmpleadoDTO e) throws ComunicacionException {
		try {
			return ir.iniciarSesion(e);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}

	}
	public void altaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws ComunicacionException {
		try {
			ir.altaEmpleado(gerente, empleado);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
		
	}
	public void modificacionEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws ComunicacionException {
		try {
			ir.modificacionEmpleado(gerente, empleado);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}

	}
	public void bajaEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws ComunicacionException {
		try {
			ir.bajaEmpleado(gerente, empleado);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}

	}
	public EmpleadoDTO mostrarFichaEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws ComunicacionException {
		try {
			return ir.mostrarFichaEmpleado(gerente, empleado);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}

	}
	public ArrayList<EmpleadoDTO> listarEmpleado (EmpleadoDTO supervisor) throws ComunicacionException {
		try {
			return ir.listarEmpleado(supervisor);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}

	}
	public ArrayList<EmpleadoDTO> listarEmpleado (EmpleadoDTO supervisor, EstadoEmpleado ee) throws ComunicacionException {
		try {
			return ir.listarEmpleado(supervisor, ee);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}

	}
	public ArrayList<EmpleadoDTO> listarEmpleado (EmpleadoDTO supervisor, Puesto p) throws ComunicacionException {
		try {
			return ir.listarEmpleado(supervisor, p);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public void eliminarEmpleado (EmpleadoDTO gerente, Puesto p) throws ComunicacionException {
		try {
			ir.listarEmpleado(gerente, p);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}

	public void marcarFacturaCobrada(EmpleadoDTO gerente, FacturaDTO f) throws ComunicacionException {
		try {
			ir.marcarFacturaCobrada(gerente, f);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public ArrayList<FacturaDTO> listarFacturas(EmpleadoDTO gerente, LocalDate fecha) throws ComunicacionException {
		try {
			return ir.listarFacturas(gerente, fecha);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public ArrayList<FacturaDTO> listarFacturasPendientes (EmpleadoDTO gerente) throws ComunicacionException {
		try {
			return ir.listarFacturasPendientes(gerente);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	
	public ArrayList<FacturaDTO> listarFacturasPendientes (EmpleadoDTO gerente, MedioDePago medioDePago) throws ComunicacionException {
		try {
			return ir.listarFacturasPendientes(gerente, medioDePago);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	public ArrayList<FacturaDTO> listarFacturasPendientes (EmpleadoDTO gerente, Integer nroOperacion) throws ComunicacionException {
		try {
			return ir.listarFacturasPendientes(gerente, nroOperacion);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public FacturaDTO mostrarFactura (EmpleadoDTO gerente, FacturaDTO f) throws ComunicacionException {
		try {
			return ir.mostrarFactura(gerente, f);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public void generarVenta(EmpleadoDTO cajero, FacturaDTO f) throws ComunicacionException {
		try {
			ir.generarVenta(cajero, f);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public void altaProducto(EmpleadoDTO supervisor, ProductoDTO p) throws ComunicacionException {
		try {
			ir.altaProducto(supervisor, p);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	public void modificacionProducto (EmpleadoDTO supervisor, ProductoDTO p) throws ComunicacionException {
		try {
			ir.modificacionProducto(supervisor, p);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	public void bajaProducto(EmpleadoDTO supervisor, ProductoDTO p) throws ComunicacionException {
		try {
			ir.bajaProducto(supervisor, p);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	public void actualizarStock(EmpleadoDTO supervisor, ProductoDTO p) throws ComunicacionException {
		try {
			ir.actualizarStock(supervisor, p);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	public ArrayList<ProductoDTO> listarProductos(EmpleadoDTO supervisor) throws ComunicacionException {
		try {
			return ir.listarProductos(supervisor);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public ProductoDTO mostrarProducto (EmpleadoDTO supervisor, ProductoDTO p) throws ComunicacionException {
		try {
			return ir.mostrarProducto(supervisor, p);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
}
