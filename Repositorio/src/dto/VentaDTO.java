package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;
import enumeraciones.TipoCuenta;
import enumeraciones.TipoFactura;

public class VentaDTO implements Serializable {

	private static final long serialVersionUID = 7443701781909431163L;
	private Integer id;
	private LocalDate fechaVenta;
	private List<ItemVentaDTO> items;
	private EmpleadoDTO empleado;
	private EstadoVenta estado;
	private Float total;
	
	private MedioDePago medioDePago;
	
	//Datos EFTVO
	private Float montoRecibido;
	private Float vuelto;
	
	//Datos Tarjetas TC+TD
	private String numeroTarjeta;
	private Integer codigoSeguridad;
	private String nombre;
	private String dni;
	private String fechaVto;
	private Integer nroOperacion;
	private Boolean aprobada;
	
	//Datos TC
	private Integer cantCuotas;
	
	//Datos TD
	private Integer pin;
	private TipoCuenta tipoCuenta;
	
	//Datos Factura
	private TipoFactura tipoFact;
	private String cuit;
	private LocalDate fechaCobro;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public List<ItemVentaDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemVentaDTO> items) {
		this.items = items;
	}
	public EmpleadoDTO getEmpleado() {
		return empleado;
	}
	public void setEmpleado(EmpleadoDTO empleado) {
		this.empleado = empleado;
	}
	public EstadoVenta getEstado() {
		return estado;
	}
	public void setEstado(EstadoVenta estado) {
		this.estado = estado;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public MedioDePago getMedioDePago() {
		return medioDePago;
	}
	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
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
	public Integer getCantCuotas() {
		return cantCuotas;
	}
	public void setCantCuotas(Integer cantCuotas) {
		this.cantCuotas = cantCuotas;
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
	public TipoFactura getTipoFact() {
		return tipoFact;
	}
	public void setTipoFact(TipoFactura tipoFact) {
		this.tipoFact = tipoFact;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public LocalDate getFechaCobro() {
		return fechaCobro;
	}
	public void setFechaCobro(LocalDate fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
	public VentaDTO(Integer id, LocalDate fechaVenta, List<ItemVentaDTO> items, EmpleadoDTO empleado,
			EstadoVenta estado, Float total, MedioDePago medioDePago, 
			Float montoRecibido, Float vuelto, //Datos EFVTO
			String numeroTarjeta, Integer codigoSeguridad, String nombre, String dni, //Datos Tarjetas TC+TD	
			String fechaVto, Integer nroOperacion, Boolean aprobada, //Datos Tarjetas TC+TD		
			Integer cantCuotas, //Datos TC			
			Integer pin, TipoCuenta tipoCuenta, //Datos TD
			TipoFactura tipoFact, String cuit, LocalDate fechaCobro ) { //Datos Factura
		this.id = id;
		this.fechaVenta = fechaVenta;
		this.items = items;
		this.empleado = empleado;
		this.estado = estado;
		this.total = total;
		this.medioDePago = medioDePago;
		this.montoRecibido = montoRecibido;
		this.vuelto = vuelto;
		this.numeroTarjeta = numeroTarjeta;
		this.codigoSeguridad = codigoSeguridad;
		this.nombre = nombre;
		this.dni = dni;
		this.fechaVto = fechaVto;
		this.nroOperacion = nroOperacion;
		this.aprobada = aprobada;
		this.cantCuotas = cantCuotas;
		this.pin = pin;
		this.tipoCuenta = tipoCuenta;
		this.tipoFact = tipoFact;
		this.cuit = cuit;
		this.fechaCobro = fechaCobro;
	}
	
	public VentaDTO() {
	}
	

}
