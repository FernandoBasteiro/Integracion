package remoto;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import dto.EmpleadoDTO;
import dto.FacturaDTO;
import dto.ProductoDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.EstadoFactura;
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

}
