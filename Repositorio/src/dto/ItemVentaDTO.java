package dto;

import java.io.Serializable;

public class ItemVentaDTO implements Serializable {

	private static final long serialVersionUID = -5039917908700059462L;
	private ProductoDTO producto;
	private Float precio;
	private Integer cantidad;
	
	public ProductoDTO getProducto() {
		return producto;
	}
	public void setProducto(ProductoDTO producto) {
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
	public ItemVentaDTO(ProductoDTO producto, Float precio, Integer cantidad) {
		this.producto = producto;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	public ItemVentaDTO() {
	}
	
	
}
