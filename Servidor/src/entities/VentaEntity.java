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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import enumeraciones.EstadoVenta;
import enumeraciones.TipoFactura;

public class VentaEntity {
	
	

	public VentaEntity() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "int", nullable=false)
	private Integer id;
	
	@Column(name = "startTime", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private String fechaVenta;
	
	@OneToMany (cascade = CascadeType.ALL) //ESTO DEBERIA SER VECTOR
	@JoinColumn (name = "id")
	private ItemVentaEntity items;
	
	@OneToOne (cascade = CascadeType.ALL) 
	@JoinColumn (name = "legajo")
	private EmpleadoEntity empleado;
	
	@Enumerated(EnumType.STRING)
    @Column(length = 9)
	private EstadoVenta estado;
	
	@Column (columnDefinition = "float", nullable = true)
	private String total;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public ItemVentaEntity getItems() {
		return items;
	}

	public void setItems(ItemVentaEntity items) {
		this.items = items;
	}

	public EmpleadoEntity getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoEntity empleado) {
		this.empleado = empleado;
	}

	public EstadoVenta getEstado() {
		return estado;
	}

	public void setEstado(EstadoVenta estado) {
		this.estado = estado;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
		
}
