package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import dto.EmpleadoDTO;
import dto.VentaDTO;
import dto.ProductoDTO;
import enumeraciones.EstadoEmpleado;
import enumeraciones.EstadoFactura;
import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;
import enumeraciones.Puesto;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;


public interface InterfazRemota extends Remote {
	public EmpleadoDTO iniciarSesion(EmpleadoDTO e) throws RemoteException, UsuarioNoLogueado;
	public void altaEmpleado(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso;
	
	public void modificacionEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso;
	public void bajaEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso;
	public EmpleadoDTO mostrarFichaEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException, UsuarioSinPermisos, ExcepcionProceso, UsuarioNoLogueado;
	public ArrayList<EmpleadoDTO> listarEmpleadoPorDNI (EmpleadoDTO gerente, String dni) throws RemoteException, UsuarioSinPermisos, UsuarioNoLogueado;
	public ArrayList<EmpleadoDTO> listarEmpleadoPorLegajo (EmpleadoDTO gerente, Integer leg) throws RemoteException, UsuarioSinPermisos, UsuarioNoLogueado;
	public ArrayList<EmpleadoDTO> listarEmpleados (EmpleadoDTO gerente, Puesto p, EstadoEmpleado e) throws RemoteException, UsuarioSinPermisos, UsuarioNoLogueado;
	public void eliminarEmpleado (EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso;
	public void generarNovedad(EmpleadoDTO gerente, EmpleadoDTO empleado) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos, ExcepcionProceso;
	public EmpleadoDTO listarNovedades(EmpleadoDTO empleado) throws RemoteException, ExcepcionProceso; 
	
	public ArrayList<ProductoDTO> listarProductos(EmpleadoDTO cajero, ProductoDTO p) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos;
	
	public void marcarFacturaCobrada(EmpleadoDTO gerente, VentaDTO v) throws RemoteException, UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos;
	public ArrayList<VentaDTO> listarFacturaPorNroFactura(EmpleadoDTO gerente, Integer nroFact) throws RemoteException, UsuarioSinPermisos, UsuarioNoLogueado;
	public ArrayList<VentaDTO> listarFacturaPorNroOperacion(EmpleadoDTO gerente, Integer nroOper) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos;
	public ArrayList<VentaDTO> listarFacturas (EmpleadoDTO gerente, MedioDePago m, LocalDate fch, EstadoVenta e) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos;
	
	public VentaDTO mostrarFactura (EmpleadoDTO gerente, VentaDTO v) throws RemoteException, UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos;
	  
	public VentaDTO generarVenta(EmpleadoDTO cajero, VentaDTO v) throws RemoteException, UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos;
	public void anularFactura(EmpleadoDTO g, VentaDTO v) throws RemoteException, UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos;
	public void marcarFacturasCobradas(EmpleadoDTO g, String periodo) throws RemoteException, UsuarioNoLogueado, UsuarioSinPermisos;
	
	public void altaProducto(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException, UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos;
	public void modificacionProducto (EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException, UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos;
	public void bajaProducto(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException, UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos;
	public void actualizarStock(EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException, UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos;
	public ProductoDTO mostrarProducto (EmpleadoDTO supervisor, ProductoDTO p) throws RemoteException, UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos;
	
}
