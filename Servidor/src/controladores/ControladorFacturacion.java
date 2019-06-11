package controladores;

import java.time.LocalDate;
import java.util.ArrayList;

import dto.EmpleadoDTO;
import dto.FacturaDTO;
import enumeraciones.EstadoFactura;
import enumeraciones.MedioDePago;
import enumeraciones.TipoFactura;
import negocio.Venta;

public class ControladorFacturacion {

	
	public void marcarFacturaCobrada(EmpleadoDTO gerente, FacturaDTO f) {
		
	}
	
	public ArrayList<FacturaDTO> listarFacturasPorNroFactura(EmpleadoDTO gerente, Integer nroFact) {
		return null;
	}
	
	public ArrayList<FacturaDTO> listarFacturasPorNroOperacion(EmpleadoDTO gerente, Integer nroOper) {
		return null;
	}

	public ArrayList<FacturaDTO> listarFacturas(EmpleadoDTO gerente, MedioDePago m, LocalDate fch, EstadoFactura e) {
		return null;
	}
		
	public void generarFactura(Venta v, TipoFactura tipo, String cuit) {
		
	}
	
	public FacturaDTO mostrarFactura(EmpleadoDTO gerente, FacturaDTO f) {
		
		return f;
	}
	
	
}


