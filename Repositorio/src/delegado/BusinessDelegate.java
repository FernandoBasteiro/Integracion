package delegado;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import dto.EmpleadoDTO;
import dto.ProductoDTO;
import dto.VentaDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.EstadoFactura;
import enumeraciones.MedioDePago;
import enumeraciones.Puesto;
import excepciones.ComunicacionException;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;
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
			ir = (InterfazRemota) Naming.lookup("//127.0.0.1/SuperSarasaServer");
		} catch (MalformedURLException e) {
			throw new ComunicacionException("La direccion especificada no es correcta");
		} catch (RemoteException e) {

		} catch (NotBoundException e) {
			throw new ComunicacionException("El servidor no esta disponible");		
		}
	}
	
	public EmpleadoDTO iniciarSesion(EmpleadoDTO e) throws ComunicacionException, UsuarioNoLogueado {
		try {
			return ir.iniciarSesion(e);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}

	}
	public void altaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws ComunicacionException, UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
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
	
	public ArrayList<EmpleadoDTO> listarEmpleadoPorDNI (EmpleadoDTO gerente, String dni) throws ComunicacionException {
		try {
			return ir.listarEmpleadoPorDNI(gerente, dni);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	public ArrayList<EmpleadoDTO> listarEmpleadoPorLegajo (EmpleadoDTO gerente, Integer leg) throws ComunicacionException {
		try {
			return ir.listarEmpleadoPorLegajo(gerente, leg);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	public ArrayList<EmpleadoDTO> listarEmpleados (EmpleadoDTO gerente, Puesto p, EstadoEmpleado e) throws ComunicacionException, ExcepcionProceso, UsuarioSinPermisos, UsuarioNoLogueado {
		try {
			return ir.listarEmpleados(gerente, p, e);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}

	public void eliminarEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws ComunicacionException {
		try {
			ir.eliminarEmpleado(gerente, empleado);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	/*
	public void marcarFacturaCobrada(EmpleadoDTO gerente, VentaDTO f) throws ComunicacionException {
		try {
			ir.marcarFacturaCobrada(gerente, f);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public ArrayList<VentaDTO> listarFacturaPorNroFactura(EmpleadoDTO gerente, Integer nroFact) throws ComunicacionException {
		try {
			return ir.listarFacturaPorNroFactura(gerente, nroFact);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	public ArrayList<VentaDTO> listarFacturaPorNroOperacion(EmpleadoDTO gerente, Integer nroOper) throws ComunicacionException{
		try {
			return ir.listarFacturaPorNroOperacion(gerente, nroOper);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	public ArrayList<VentaDTO> listarFacturas (EmpleadoDTO gerente, MedioDePago m, LocalDate fch, EstadoFactura e) throws ComunicacionException{
		try {
			return ir.listarFacturas(gerente, m, fch, e);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public VentaDTO mostrarFactura (EmpleadoDTO gerente, VentaDTO f) throws ComunicacionException {
		try {
			return ir.mostrarFactura(gerente, f);
		} catch (RemoteException re) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public void generarVenta(EmpleadoDTO cajero, VentaDTO f) throws ComunicacionException {
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
	public ArrayList<ProductoDTO> listarProductos(EmpleadoDTO supervisor, ProductoDTO p) throws ComunicacionException {
		try {
			return ir.listarProductos(supervisor, p);
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
	*/
}
