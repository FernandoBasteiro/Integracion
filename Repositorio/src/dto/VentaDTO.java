package dto;

import java.time.LocalDateTime;
import java.util.List;

import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;

public class VentaDTO {
	private Integer id;
	private LocalDateTime fechaVenta;
	private List<ItemVentaDTO> items;
	private EmpleadoDTO empleado;
	private EstadoVenta estado;
	private Float total;
	private MedioDePago medioDePago;
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
			EstadoVenta estado, Float total, MedioDePago medioDePago) {
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
