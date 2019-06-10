package negocio;

import java.time.LocalDate;

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
}
