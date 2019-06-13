package negocio;

import daos.ProductoDAO;
import dto.ProductoDTO;
import dto.StockDTO;
import excepciones.ExcepcionProceso;

public class Producto {
	private Long codigo;
	private String nombre;
	private String descripcion;
	private String presentacion;
	private Float precio;
	private Stock stock;
	public Producto(Long codigo, String nombre, String descripcion, String presentacion, Float precio, Stock stock) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.presentacion = presentacion;
		this.precio = precio;
		this.stock = stock;
	}
	public Producto(String nombre, String descripcion, String presentacion, Float precio, Stock stock) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.presentacion = presentacion;
		this.precio = precio;
		this.stock = stock;
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
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Producto() {
		super();
	}
	
	public void devolverStock(Integer cant) {
		this.stock.devolverStock(cant);
	}
	
	public ProductoDTO getDTO() {
		return new ProductoDTO(this.codigo, this.nombre, this.descripcion, this.presentacion, this.precio, this.stock.getDTO());
	}
	
	public void guardar() {
		ProductoDAO.getinstance().add(this);
	}
	
	public void guardarStock() {
		this.stock.guardar();
	}
	
	public void bajaProducto() {
		this.stock.bajaStock();
	}
	
	public void actualizarStock(Integer cTot, Integer cDisp, Integer cMin) {
		this.stock.actualizarStock(cTot, cDisp, cMin);
	}
	
}
