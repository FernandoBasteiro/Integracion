package controladores;

import java.time.LocalDate;
import java.util.ArrayList;

import dto.FacturaDTO;
import enumeraciones.MedioDePago;

public class ControladorFacturacion {

	
	public void marcarFacturaCobrada(FacturaDTO f) {
		
	}
	
	public ArrayList<FacturaDTO> listarFacturas(LocalDate fecha) {
		return null;
	}
	
	public ArrayList<FacturaDTO> listarFacturasPendientes() {
		return null;
	}
	
	public ArrayList<FacturaDTO> listarFacturasPendientes(MedioDePago medioDePago) {
		return null;
	}
	
	public ArrayList<FacturaDTO> listarFacturasPendientes(Integer nroOperacion) {
		return null;
	}
	
	public void generarFactura(FacturaDTO f) {
		
	}
	
	public FacturaDTO mostrarFactura(FacturaDTO f) {
		
		return f;
	}
	
	
}


