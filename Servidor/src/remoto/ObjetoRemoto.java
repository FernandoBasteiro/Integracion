package remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.joda.time.LocalDate;

import controladores.ControladorEmpleados;
import controladores.ControladorProductos;
import controladores.ControladorVentas;
import dto.EmpleadoDTO;
import dto.ProductoDTO;
import dto.VentaDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;
import enumeraciones.Puesto;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;
import interfaces.InterfazRemota;

public class ObjetoRemoto extends UnicastRemoteObject implements InterfazRemota {
	
	private static final long serialVersionUID = -1715957252381957302L;

	public ObjetoRemoto() throws RemoteException {}

	@Override
	public EmpleadoDTO iniciarSesion(EmpleadoDTO e) throws RemoteException, UsuarioNoLogueado {
		return ControladorEmpleados.getInstance().iniciarSesion(e);
	}

	@Override
	public void altaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		ControladorEmpleados.getInstance().altaEmpleado(gerente, empleado);
	}
	
	@Override
	public void modificacionEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		ControladorEmpleados.getInstance().modificacionEmpleado(gerente, empleado);
	}

	@Override
	public void bajaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso {
		ControladorEmpleados.getInstance().eliminarEmpleado(gerente, empleado);
	}

	@Override
	public EmpleadoDTO mostrarFichaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException, UsuarioSinPermisos, ExcepcionProceso, UsuarioNoLogueado {
		return ControladorEmpleados.getInstance().mostrarFichaEmpleado(gerente, empleado);
	}

	@Override
	public ArrayList<EmpleadoDTO> listarEmpleadoPorDNI(EmpleadoDTO gerente, String dni) throws RemoteException, UsuarioSinPermisos, UsuarioNoLogueado {
		return ControladorEmpleados.getInstance().listarEmpleadoPorDNI(gerente, dni);
	}
	
	@Override
	public ArrayList<EmpleadoDTO> listarEmpleadoPorLegajo(EmpleadoDTO gerente, Integer leg) throws RemoteException, UsuarioSinPermisos, UsuarioNoLogueado {
		return ControladorEmpleados.getInstance().listarEmpleadoPorLegajo(gerente, leg);
	}

	@Override
	public ArrayList<EmpleadoDTO> listarEmpleados(EmpleadoDTO gerente, Puesto p, EstadoEmpleado e)
			throws RemoteException, UsuarioSinPermisos, UsuarioNoLogueado {
		return ControladorEmpleados.getInstance().listarEmpleados(gerente, p, e);
	}

	@Override
	public void eliminarEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<ProductoDTO> listarProductos(EmpleadoDTO cajero, ProductoDTO p) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos {
		return ControladorProductos.getInstancia().listarProductos(cajero, p);
	}

	public void marcarFacturaCobrada(EmpleadoDTO gerente, VentaDTO f) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<VentaDTO> listarFacturaPorNroFactura(EmpleadoDTO gerente, Integer nroFact)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<VentaDTO> listarFacturaPorNroOperacion(EmpleadoDTO gerente, Integer nroOper)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public ArrayList<VentaDTO> listarFacturas(EmpleadoDTO gerente, MedioDePago m, LocalDate fch, EstadoVenta e)
			throws RemoteException {
		return null;
	}

	@Override
	public VentaDTO mostrarFactura(EmpleadoDTO gerente, VentaDTO f) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VentaDTO generarVenta(EmpleadoDTO c, VentaDTO v) throws RemoteException, UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
	 return	ControladorVentas.getInstance().generarVenta(c, v);
		
	}
/**
	@Override
	public void altaProducto(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificacionProducto(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bajaProducto(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarStock(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	
	@Override
	public ProductoDTO mostrarProducto(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
