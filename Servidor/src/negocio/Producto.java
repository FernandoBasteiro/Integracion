package negocio;

public class Producto {
	private Integer codigo;
	private String nombre;
	private String descripcion;
	private String presentacion;
	private Float precio;
	private Stock stock;
	public Producto(Integer codigo, String nombre, String descripcion, String presentacion, Float precio, Stock stock) {
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
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
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
	
	
	
	
	
	
	
	
	
	
	
}
