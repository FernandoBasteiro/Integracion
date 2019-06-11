package dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;
import enumeraciones.TipoCuenta;
import enumeraciones.TipoFactura;

public class VentaDTO {
	private Integer id;
	private LocalDateTime fechaVenta;
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
	private Integer dni;
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
	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(LocalDateTime fechaVenta) {
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
	public VentaDTO(Integer id, LocalDateTime fechaVenta, List<ItemVentaDTO> items, EmpleadoDTO empleado,
			EstadoVenta estado, Float total, MedioDePago medioDePago, 
			Float montoRecibido, Float vuelto, //Datos EFVTO
			String numeroTarjeta, Integer codigoSeguridad, String nombre, Integer dni, //Datos Tarjetas TC+TD	
			String fechaVto, Integer nroOperacion, Boolean aprobada, //Datos Tarjetas TC+TD		
			Integer cantCuotas, //Datos TC			
			Integer pin, TipoCuenta tipoCuenta, //Datos TD
			TipoFactura tipoFact, String cuit, LocalDate fechaCobro //Datos Factura
			) {
		this.id = id;
		this.fechaVenta = fechaVenta;
		this.items = items;
		this.empleado = empleado;
		this.estado = estado;
		this.total = total;
		this.medioDePago = medioDePago;
		
	}
	public VentaDTO() {
	}
	
	
}
