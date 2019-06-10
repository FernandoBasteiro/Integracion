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
}
