package negocio;

import enumeraciones.TipoCuenta;

public class VentaTarjetaCredito extends Venta {
	private String numeroTarjeta;
	private Integer codigoSeguridad;
	private String nombre;
	private Integer dni;
	private String fechaVto;
	private Integer nroOperacion;
	private Boolean aprobada;
	private Integer cantCuotas;
}
