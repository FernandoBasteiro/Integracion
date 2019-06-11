package interfaces;

import java.rmi.Remote;
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


public interface InterfazRemota extends Remote {
	public EmpleadoDTO iniciarSesion(EmpleadoDTO e) throws RemoteException;
	public void altaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException;
	public void modificacionEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException;
	public void bajaEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException;
	public EmpleadoDTO mostrarFichaEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException;
	public ArrayList<EmpleadoDTO> listarEmpleadoPorDNI (EmpleadoDTO gerente, String dni) throws RemoteException;
	public ArrayList<EmpleadoDTO> listarEmpleadoPorLegajo (EmpleadoDTO gerente, Integer leg) throws RemoteException;
	public ArrayList<EmpleadoDTO> listarEmpleados (EmpleadoDTO gerente, Puesto p, EstadoEmpleado e) throws RemoteException;
	public void eliminarEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException;
	
	public void marcarFacturaCobrada(EmpleadoDTO gerente, FacturaDTO f) throws RemoteException;
	public ArrayList<FacturaDTO> listarFacturaPorNroFactura(EmpleadoDTO gerente, Integer nroFact) throws RemoteException;
	public ArrayList<FacturaDTO> listarFacturaPorNroOperacion(EmpleadoDTO gerente, Integer nroOper) throws RemoteException;
	public ArrayList<FacturaDTO> listarFacturas (EmpleadoDTO gerente, MedioDePago m, LocalDate fch, EstadoFactura e) throws RemoteException;
	public FacturaDTO mostrarFactura (EmpleadoDTO gerente, FacturaDTO f) throws RemoteException;
	
	public void generarVenta(EmpleadoDTO cajero, FacturaDTO f) throws RemoteException;
	
	public void altaProducto(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException;
	public void modificacionProducto (EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException;
	public void bajaProducto(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException;
	public void actualizarStock(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException;
	public ArrayList<ProductoDTO> listarProductos(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException;
	public ProductoDTO mostrarProducto (EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException;
}
