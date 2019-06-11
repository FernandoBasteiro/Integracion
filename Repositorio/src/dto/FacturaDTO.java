package dto;

import java.time.LocalDate;

import enumeraciones.EstadoFactura;
import enumeraciones.TipoFactura;

public class FacturaDTO {
	private Integer numero;
	private LocalDate fechaFacturacion;
	private TipoFactura tipo;
	private VentaDTO venta;
	private String cuit;
	private EstadoFactura estado;
	private LocalDate fechaCobro;
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
	public VentaDTO getVenta() {
		return venta;
	}
	public void setVenta(VentaDTO venta) {
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
	public FacturaDTO(Integer numero, LocalDate fechaFacturacion, TipoFactura tipo, VentaDTO venta, String cuit,
			EstadoFactura estado, LocalDate fechaCobro) {
		this.numero = numero;
		this.fechaFacturacion = fechaFacturacion;
		this.tipo = tipo;
		this.venta = venta;
		this.cuit = cuit;
		this.estado = estado;
		this.fechaCobro = fechaCobro;
	}
	public FacturaDTO() {
	}
	
	
}
