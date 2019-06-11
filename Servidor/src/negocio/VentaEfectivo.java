package negocio;

import org.joda.time.LocalDate;
import java.util.List;
import java.util.Vector;

import enumeraciones.MedioDePago;
import enumeraciones.TipoCuenta;
import dto.EmpleadoDTO;
import dto.ItemVentaDTO;
import dto.VentaDTO;
import enumeraciones.EstadoVenta;
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
		return new VentaDTO (this.id, convertJodaToJava(this.fechaVenta), this.gesItemsDTO (), this.empleado.getDTO(),
				this.estado, this.total, MedioDePago.EFECTIVO, 
				this.montoRecibido, this.vuelto, //Datos EFVTO
				null, null, null, null, //Datos Tarjetas TC+TD	
				null, null, null, //Datos Tarjetas TC+TD		
				null, //Datos TC			
				null, null, //Datos TD
				this.tipoFact, this.cuit, convertJodaToJava(this.fechaCobro)); //Datos Factura
	}
}
