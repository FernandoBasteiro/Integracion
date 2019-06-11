package negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import enumeraciones.EstadoVenta;
import enumeraciones.TipoFactura;
import dto.VentaDTO;

public class Venta {
	private Integer id;
	private LocalDateTime fechaVenta;
	private List<ItemVenta> items;
	private Empleado empleado;
	private EstadoVenta estado;
	private Float total;
	
	//Datos Factura
	private TipoFactura tipoFact;
	private String cuit;
	private LocalDate fechaCobro;
	
	
	public Venta(Integer id, LocalDateTime fechaVenta, List<ItemVenta> items, Empleado empleado, EstadoVenta estado,
			Float total, TipoFactura tipoFact, String cuit, LocalDate fechaCobro) {
		super();
		this.id = id;
		this.fechaVenta = fechaVenta;
		this.items = items;
		this.empleado = empleado;
		this.estado = estado;
		this.total = total;
		this.tipoFact = tipoFact;
		this.cuit = cuit;
		this.fechaCobro = fechaCobro;
	}
	public Venta(LocalDateTime fechaVenta, List<ItemVenta> items, Empleado empleado, EstadoVenta estado, Float total, 
			TipoFactura tipoFact, String cuit, LocalDate fechaCobro) {
		super();
		this.fechaVenta = fechaVenta;
		this.items = items;
		this.empleado = empleado;
		this.estado = estado;
		this.total = total;
		this.tipoFact = tipoFact;
		this.cuit = cuit;
		this.fechaCobro = fechaCobro;
	}
	public Venta() {
		super();
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
	
	public void cancelarVenta() {
		for (ItemVenta i : items) {
			i.devolverProducto();
		}	
		this.setEstado(EstadoVenta.ANULADA);
	}
	
	public void marcarFacturaCobrada() {
		this.setFechaCobro(LocalDate.now());
		this.setEstado(EstadoVenta.COBRADA);
	}
	
	public VentaDTO getDTO () {
		//TODO ENVIAR PARAMETROS 
		return new VentaDTO ();
	}
}
