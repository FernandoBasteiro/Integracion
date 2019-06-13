package dto;

import java.io.Serializable;

public class ProductoDTO implements Serializable {

	private static final long serialVersionUID = -3210096887067507740L;
	private Long codigo;
	private String nombre;
	private String descripcion;
	private String presentacion;
	private Float precio;
	private StockDTO stock;
	
	public String getCodigoStr() {
		return codigo.toString();
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public StockDTO getStock() {
		return stock;
	}
	public void setStock(StockDTO stock) {
		this.stock = stock;
	}
	public ProductoDTO(Long codigo2, String nombre, String descripcion, String presentacion, Float precio,
			StockDTO stock) {
		this.codigo = codigo2;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.presentacion = presentacion;
		this.precio = precio;
		this.stock = stock;
	}
	public ProductoDTO() {
	}
	
	
}
