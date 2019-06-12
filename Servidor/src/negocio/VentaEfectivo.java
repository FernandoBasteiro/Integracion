package negocio;

import java.util.List;

import org.joda.time.LocalDate;

import controladores.ConversorFechas;
import dto.VentaDTO;
import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;
import enumeraciones.TipoFactura;

public class VentaEfectivo extends Venta {
	private Float montoRecibido;
	private Float vuelto;
	
	public VentaEfectivo(Integer id, LocalDate fechaVenta, List<ItemVenta> items, Empleado empleado,
			EstadoVenta estado, Float total, Float montoRecibido, Float vuelto, 
			TipoFactura tipoFact, String cuit, LocalDate fechaCobro) {
		super(id, fechaVenta, items, empleado, estado, total, tipoFact, cuit, fechaCobro);
		this.montoRecibido = montoRecibido;
		this.vuelto = vuelto;
	}
	
	public VentaEfectivo(LocalDate fechaVenta, List<ItemVenta> items, Empleado empleado,
			EstadoVenta estado, Float total, Float montoRecibido, Float vuelto, 
			TipoFactura tipoFact, String cuit, LocalDate fechaCobro) {
		super(fechaVenta, items, empleado, estado, total, tipoFact, cuit, fechaCobro);
		this.montoRecibido = montoRecibido;
		this.vuelto = vuelto;
	}
	
	
	
	public Float getMontoRecibido() {
		return montoRecibido;
	}
	
	public void setMontoRecibido(Float montoRecibido) {
		this.montoRecibido = montoRecibido;
	}
	
	public Float getVuelto() {
		return vuelto;
	}
	
	public void setVuelto(Float vuelto) {
		this.vuelto = vuelto;
	}
	
	public VentaDTO getDTO () {
		//TODO ENVIAR PARAMETROS 
		return new VentaDTO (this.id, ConversorFechas.convertJodaToJava(this.fechaVenta), this.gesItemsDTO (), this.empleado.getDTO(),
				this.estado, this.total, MedioDePago.EFECTIVO, 
				this.montoRecibido, this.vuelto, //Datos EFVTO
				null, null, null, null, //Datos Tarjetas TC+TD	
				null, null, null, //Datos Tarjetas TC+TD		
				null, //Datos TC			
				null, null, //Datos TD
				this.tipoFact, this.cuit, ConversorFechas.convertJodaToJava(this.fechaCobro)); //Datos Factura
	}

	@Override
	public void grabar() {
		VentaDAO.getinstance().add(this);
	}
	public float calcularVuelto () {
		return this.montoRecibido - this.total;
	}
}
