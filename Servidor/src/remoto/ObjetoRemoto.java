package remoto;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import dto.EmpleadoDTO;
import dto.FacturaDTO;
import dto.ProductoDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.MedioDePago;
import enumeraciones.Puesto;
import interfaces.InterfazRemota;

public class ObjetoRemoto implements InterfazRemota {

	@Override
	public EmpleadoDTO iniciarSesion(EmpleadoDTO e) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void altaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException {
		// TODO Auto-generated method stub
		
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
	public ArrayList<EmpleadoDTO> listarEmpleado(EmpleadoDTO supervisor) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmpleadoDTO> listarEmpleado(EmpleadoDTO supervisor, EstadoEmpleado ee) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmpleadoDTO> listarEmpleado(EmpleadoDTO supervisor, Puesto p) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void marcarFacturaCobrada(EmpleadoDTO gerente, FacturaDTO f) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<FacturaDTO> listarFacturas(EmpleadoDTO gerente, LocalDate fecha) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FacturaDTO> listarFacturasPendientes(EmpleadoDTO gerente) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FacturaDTO> listarFacturasPendientes(EmpleadoDTO gerente, MedioDePago medioDePago)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FacturaDTO> listarFacturasPendientes(EmpleadoDTO gerente, Integer nroOperacion)
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
	public ArrayList<ProductoDTO> listarProductos(EmpleadoDTO supervisor) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoDTO mostrarProducto(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
