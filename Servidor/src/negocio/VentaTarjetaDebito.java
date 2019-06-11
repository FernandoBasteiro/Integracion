package negocio;

import java.time.LocalDateTime;
import java.util.List;

import dto.VentaDTO;
import enumeraciones.EstadoVenta;
import enumeraciones.TipoCuenta;

public class VentaTarjetaDebito extends Venta {
	private String numeroTarjeta;
	private Integer codigoSeguridad;
	private String nombre;
	private Integer dni;
	private String fechaVto;
	private Integer nroOperacion;
	private Boolean aprobada;
	private Integer pin;
	private TipoCuenta tipoCuenta;
	public VentaTarjetaDebito(Integer id, LocalDateTime fechaVenta, List<ItemVenta> items, Empleado empleado,
			EstadoVenta estado, Float total, String numeroTarjeta, Integer codigoSeguridad, String nombre, Integer dni,
			String fechaVto, Integer nroOperacion, Boolean aprobada, Integer pin, TipoCuenta tipoCuenta) {
		super(id, fechaVenta, items, empleado, estado, total);
		this.numeroTarjeta = numeroTarjeta;
		this.codigoSeguridad = codigoSeguridad;
		this.nombre = nombre;
		this.dni = dni;
		this.fechaVto = fechaVto;
		this.nroOperacion = nroOperacion;
		this.aprobada = aprobada;
		this.pin = pin;
		this.tipoCuenta = tipoCuenta;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public Integer getCodigoSeguridad() {
		return codigoSeguridad;
	}
	public void setCodigoSeguridad(Integer codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public String getFechaVto() {
		return fechaVto;
	}
	public void setFechaVto(String fechaVto) {
		this.fechaVto = fechaVto;
	}
	public Integer getNroOperacion() {
		return nroOperacion;
	}
	public void setNroOperacion(Integer nroOperacion) {
		this.nroOperacion = nroOperacion;
	}
	public Boolean getAprobada() {
		return aprobada;
	}
	public void setAprobada(Boolean aprobada) {
		this.aprobada = aprobada;
	}
	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
	public VentaDTO getDTO () {
		//TODO ENVIAR PARAMETROS 
		return new VentaDTO ();
	}
	
}
