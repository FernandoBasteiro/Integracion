package entities;

import java.util.ArrayList;
import java.util.Calendar;

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
	private Calendar fechaVenta;
	
	@OneToMany (cascade = CascadeType.ALL) //ESTO DEBERIA SER VECTOR
	@JoinColumn (name = "id")
	private ArrayList<ItemVentaEntity> items; 
	
	@OneToOne (cascade = CascadeType.ALL) 
	@JoinColumn (name = "legajo")
	private EmpleadoEntity empleado;
	
	@Enumerated(EnumType.STRING)
    @Column(length = 9)
	private EstadoVenta estado;
	
	@Column (columnDefinition = "float", nullable = true)
	private Float total;
	
	
	@Column (columnDefinition = "int", nullable = true)
	private Integer nroOperacion;
	
	@Column (columnDefinition = "boolean", nullable = true)
	private boolean aprobada;
	
	@Column (columnDefinition = "int", nullable = true)
	private Integer cantCuotas;
	
	@Column (columnDefinition = "int", nullable = true) //Aca vamos a tener que agarrar los ultimos 4 digitos en algun momento
	private Integer ultimos4DigitosTarjeta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Calendar fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public ArrayList<ItemVentaEntity> getItems() {
		return items;
	}

	public void setItems(ArrayList<ItemVentaEntity> items) {
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

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Integer getNroOperacion() {
		return nroOperacion;
	}

	public void setNroOperacion(Integer nroOperacion) {
		this.nroOperacion = nroOperacion;
	}

	public boolean isAprobada() {
		return aprobada;
	}

	public void setAprobada(boolean aprobada) {
		this.aprobada = aprobada;
	}

	public Integer getCantCuotas() {
		return cantCuotas;
	}

	public void setCantCuotas(Integer cantCuotas) {
		this.cantCuotas = cantCuotas;
	}

	public Integer getUltimos4DigitosTarjeta() {
		return ultimos4DigitosTarjeta;
	}

	public void setUltimos4DigitosTarjeta(Integer ultimos4DigitosTarjeta) {
		this.ultimos4DigitosTarjeta = ultimos4DigitosTarjeta;
	}
	
	
	
		
}
