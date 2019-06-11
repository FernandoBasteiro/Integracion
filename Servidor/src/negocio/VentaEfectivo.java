package negocio;

import java.time.LocalDateTime;
import java.util.List;
import dto.VentaDTO;
import enumeraciones.EstadoVenta;

public class VentaEfectivo extends Venta {
	private Float montoRecibido;
	private Float vuelto;
	
	public VentaEfectivo(Integer id, LocalDateTime fechaVenta, List<ItemVenta> items, Empleado empleado,
			EstadoVenta estado, Float total, Float montoRecibido, Float vuelto) {
		super(id, fechaVenta, items, empleado, estado, total);
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
		return new VentaDTO ();
	}
}
