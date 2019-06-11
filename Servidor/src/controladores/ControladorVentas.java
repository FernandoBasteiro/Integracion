package controladores;

import java.time.LocalDate;
import java.util.ArrayList;

import dto.EmpleadoDTO;
import dto.VentaDTO;
import enumeraciones.EstadoFactura;
import enumeraciones.MedioDePago;
import enumeraciones.TipoFactura;
import negocio.Venta;

public class ControladorVentas {
	
	public void generarVenta(EmpleadoDTO cajero, VentaDTO v) {
		
	}
	
public void marcarFacturaCobrada(EmpleadoDTO gerente, VentaDTO v) {
		
	}
	
	public ArrayList<VentaDTO> listarFacturasPorNroFactura(EmpleadoDTO gerente, Integer nroFact) {
		return null;
	}
	
	public ArrayList<VentaDTO> listarFacturasPorNroOperacion(EmpleadoDTO gerente, Integer nroOper) {
		return null;
	}

	public ArrayList<VentaDTO> listarFacturas(EmpleadoDTO gerente, MedioDePago m, LocalDate fch, EstadoFactura e) {
		return null;
	}
		
	public void generarFactura(Venta v, TipoFactura tipo, String cuit) {
		
	}
	
	public VentaDTO mostrarFactura(EmpleadoDTO gerente, VentaDTO v) {
		
		return v;
	}
}
