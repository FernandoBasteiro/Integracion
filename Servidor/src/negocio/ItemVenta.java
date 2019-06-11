package negocio;

import dto.ItemVentaDTO;
import dto.ProductoDTO;

public class ItemVenta {
	private Producto producto;
	private Float precio;
	private Integer cantidad;
	public Producto getProducto() {
		return producto;
	}
	
	public ItemVenta() {
		super();
	}

	public ItemVenta(Producto producto, Float precio, Integer cantidad) {
		super();
		this.producto = producto;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public void devolverProducto() {
		this.producto.devolverStock(this.cantidad);
	}
	
	public ItemVentaDTO getDTO () {
		//TODO ENVIAR PARAMETROS 
				return new ItemVentaDTO(this.producto.getDTO(), this.precio, this.cantidad);
	}
}
