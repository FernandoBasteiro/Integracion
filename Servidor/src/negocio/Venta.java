package negocio;

import java.time.LocalDateTime;
import java.util.List;

import enumeraciones.EstadoVenta;

public class Venta {
	private Integer id;
	private LocalDateTime fechaVenta;
	private List<ItemVenta> items;
	private Empleado empleado;
	private EstadoVenta estado;
	private Float total;
	public Venta(Integer id, LocalDateTime fechaVenta, List<ItemVenta> items, Empleado empleado, EstadoVenta estado,
			Float total) {
		super();
		this.id = id;
		this.fechaVenta = fechaVenta;
		this.items = items;
		this.empleado = empleado;
		this.estado = estado;
		this.total = total;
	}
	public Venta(LocalDateTime fechaVenta, List<ItemVenta> items, Empleado empleado, EstadoVenta estado, Float total) {
		super();
		this.fechaVenta = fechaVenta;
		this.items = items;
		this.empleado = empleado;
		this.estado = estado;
		this.total = total;
	}
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
	public List<ItemVenta> getItems() {
		return items;
	}
	public void setItems(List<ItemVenta> items) {
		this.items = items;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
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
	public Venta() {
		super();
	}
	
	
}
