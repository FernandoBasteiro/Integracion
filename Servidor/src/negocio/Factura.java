package negocio;

import java.time.LocalDate;
import dto.FacturaDTO;
import enumeraciones.EstadoFactura;
import enumeraciones.TipoFactura;

public class Factura {
	private Integer numero;
	private LocalDate fechaFacturacion;
	private TipoFactura tipo;
	private Venta venta;
	private String cuit;
	private EstadoFactura estado;
	private LocalDate fechaCobro;
	
	public Factura(Integer numero, LocalDate fechaFacturacion, TipoFactura tipo, Venta venta, String cuit,
			EstadoFactura estado, LocalDate fechaCobro) {
		super();
		this.numero = numero;
		this.fechaFacturacion = fechaFacturacion;
		this.tipo = tipo;
		this.venta = venta;
		this.cuit = cuit;
		this.estado = estado;
		this.fechaCobro = fechaCobro;
	}
	public Factura(LocalDate fechaFacturacion, TipoFactura tipo, Venta venta, String cuit, EstadoFactura estado,
			LocalDate fechaCobro) {
		super();
		this.fechaFacturacion = fechaFacturacion;
		this.tipo = tipo;
		this.venta = venta;
		this.cuit = cuit;
		this.estado = estado;
		this.fechaCobro = fechaCobro;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public LocalDate getFechaFacturacion() {
		return fechaFacturacion;
	}
	public void setFechaFacturacion(LocalDate fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	public TipoFactura getTipo() {
		return tipo;
	}
	public void setTipo(TipoFactura tipo) {
		this.tipo = tipo;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public EstadoFactura getEstado() {
		return estado;
	}
	public void setEstado(EstadoFactura estado) {
		this.estado = estado;
	}
	public LocalDate getFechaCobro() {
		return fechaCobro;
	}
	public void setFechaCobro(LocalDate fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
	public Factura() {
		super();
	}
	
	public void marcarFacturaCobrada() {
		this.setFechaCobro(LocalDate.now());
		this.setEstado(EstadoFactura.COBRADA);
	}
	
	public void anularFactura() {
		this.venta.cancelarVenta();
		this.setEstado(EstadoFactura.ANULADA);
	}
	
	public FacturaDTO getDTO () {
		//TODO ENVIAR PARAMETROS 
		return new FacturaDTO ();
	}
}
