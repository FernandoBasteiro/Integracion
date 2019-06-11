package remoto;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import controladores.ControladorEmpleados;
import dto.EmpleadoDTO;
import dto.ProductoDTO;
import dto.VentaDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.EstadoFactura;
import enumeraciones.MedioDePago;
import enumeraciones.Puesto;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;
import interfaces.InterfazRemota;

public class ObjetoRemoto implements InterfazRemota {

	@Override
	public EmpleadoDTO iniciarSesion(EmpleadoDTO e) throws RemoteException {
		try {
			ControladorEmpleados.getInstance().iniciarSesion(e);
		} catch (UsuarioNoLogueado e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public void altaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ControladorEmpleados.getInstance().altaEmpleado(gerente, empleado);
		} catch (UsuarioNoLogueado | UsuarioSinPermisos | ExcepcionProceso e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void modificacionEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bajaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmpleadoDTO mostrarFichaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmpleadoDTO> listarEmpleadoPorDNI(EmpleadoDTO gerente, String dni) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmpleadoDTO> listarEmpleadoPorLegajo(EmpleadoDTO gerente, Integer leg) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmpleadoDTO> listarEmpleados(EmpleadoDTO gerente, Puesto p, EstadoEmpleado e)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
/**
	@Override
	public void marcarFacturaCobrada(EmpleadoDTO gerente, FacturaDTO f) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<FacturaDTO> listarFacturaPorNroFactura(EmpleadoDTO gerente, Integer nroFact)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FacturaDTO> listarFacturaPorNroOperacion(EmpleadoDTO gerente, Integer nroOper)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FacturaDTO> listarFacturas(EmpleadoDTO gerente, MedioDePago m, LocalDate fch, EstadoFactura e)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacturaDTO mostrarFactura(EmpleadoDTO gerente, FacturaDTO f) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generarVenta(EmpleadoDTO cajero, FacturaDTO f) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

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
	public ArrayList<ProductoDTO> listarProductos(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoDTO mostrarProducto(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void marcarFacturaCobrada(EmpleadoDTO gerente, VentaDTO v) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VentaDTO mostrarFactura(EmpleadoDTO gerente, VentaDTO v) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generarVenta(EmpleadoDTO cajero, VentaDTO v) throws RemoteException {
		// TODO Auto-generated method stub
		
	}*/

}
