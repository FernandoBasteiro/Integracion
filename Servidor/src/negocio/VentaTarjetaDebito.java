package negocio;

import java.util.List;

import org.joda.time.LocalDate;

import controladores.ConversorFechas;
import dto.VentaDTO;
import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;
import enumeraciones.TipoCuenta;
import enumeraciones.TipoFactura;

public class VentaTarjetaDebito extends Venta {
	private String numeroTarjeta;
	private Integer codigoSeguridad;
	private String nombre;
	private String dni;
	private String fechaVto;
	private Integer nroOperacion;
	private Boolean aprobada;
	private Integer pin;
	private TipoCuenta tipoCuenta;
	
	public VentaTarjetaDebito(Integer id, LocalDate fechaVenta, List<ItemVenta> items, Empleado empleado,
			EstadoVenta estado, Float total, String numeroTarjeta, Integer codigoSeguridad, String nombre, String dni,
			String fechaVto, Integer nroOperacion, Boolean aprobada, Integer pin, TipoCuenta tipoCuenta, 
			TipoFactura tipoFact, String cuit, LocalDate fechaCobro) {
		super(id, fechaVenta, items, empleado, estado, total, tipoFact, cuit, fechaCobro);
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
	
	public VentaTarjetaDebito(LocalDate fechaVenta, List<ItemVenta> items, Empleado empleado,
			EstadoVenta estado, Float total, String numeroTarjeta, Integer codigoSeguridad, String nombre, String dni,
			String fechaVto, Integer nroOperacion, Boolean aprobada, Integer pin, TipoCuenta tipoCuenta, 
			TipoFactura tipoFact, String cuit, LocalDate fechaCobro) {
		super(fechaVenta, items, empleado, estado, total, tipoFact, cuit, fechaCobro);
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
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
	
		return new VentaDTO (this.id, ConversorFechas.convertJodaToJava(this.fechaVenta), this.gesItemsDTO (), this.empleado.getDTO(),
				this.estado, this.total, MedioDePago.TARJETA_DEBITO, 
				null, null, //Datos EFVTO
				this.numeroTarjeta, this.codigoSeguridad, this.nombre, this.dni, //Datos Tarjetas TC+TD	
				this.fechaVto, this.nroOperacion, this.aprobada, //Datos Tarjetas TC+TD		
				null, //Datos TC			
				this.pin, this.tipoCuenta, //Datos TD
				this.tipoFact, this.cuit, ConversorFechas.convertJodaToJava(this.fechaCobro)); //Datos Factura
	}

}
