package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Productos")
public class ProductoEntity {

	
	
	
	public ProductoEntity() {
		super();
	}

	@Id
	@Column(columnDefinition = "int", nullable=false)
	private Integer codigo;
	

	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String nombre;
	
	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String descripcion;
	
	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String presentacion;
	
	@Column(columnDefinition = "float", nullable=false)
	private Float precio;
	
	@OneToOne (cascade = CascadeType.ALL)
	private StockEntity stock;

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

	public StockEntity getStock() {
		return stock;
	}

	public void setStock(StockEntity stock) {
		this.stock = stock;
	}
	
	
	
}
