package negocio;

import org.joda.time.LocalDate;
import java.util.List;
import java.util.Vector;

import enumeraciones.EstadoVenta;
import enumeraciones.TipoFactura;
import dto.ItemVentaDTO;
import dto.VentaDTO;

public class Venta {
	protected Integer id;
	protected LocalDate fechaVenta;
	protected List<ItemVenta> items;
	protected Empleado empleado;
	protected EstadoVenta estado;
	protected Float total;
	//Datos Factura
	protected TipoFactura tipoFact;
	protected String cuit;
	protected LocalDate fechaCobro;
	
	
	public Venta(Integer id, LocalDate fechaVenta, List<ItemVenta> items, Empleado empleado, EstadoVenta estado,
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
	public Venta(LocalDate fechaVenta, List<ItemVenta> items, Empleado empleado, EstadoVenta estado, Float total, 
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
	public LocalDate getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(LocalDate fechaVenta) {
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
	
	protected List<ItemVentaDTO> gesItemsDTO () {
		List<ItemVentaDTO> itemsDTO = new Vector<ItemVentaDTO> ();
		for (ItemVenta i : this.items) {
			itemsDTO.add(i.getDTO());
		}
		return itemsDTO;
	}
	
	/*public VentaDTO getDTO () {
		//TODO ENVIAR PARAMETROS 
		return new VentaDTO ();
	}*/
}
