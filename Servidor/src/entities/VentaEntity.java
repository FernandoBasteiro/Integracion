package entities;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;
import enumeraciones.TipoFactura;

@Entity
@Table(name="Ventas")
public class VentaEntity {
	
	

	public VentaEntity() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaVenta;
	
	@OneToMany (cascade = CascadeType.ALL) 
	@JoinColumn (name = "id_venta")
	private List<ItemVentaEntity> items; 
	@OneToOne  
	private EmpleadoEntity empleado;
	
	private EstadoVenta estado;
	
	private Float total;
	
	
	//DATOS FACTURA
	

	private TipoFactura tipo;
	
	private String cuit;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaCobro;
	
	//DATOS PARTICULARES DE CADA HERENCIA
	
	private Integer nroOperacion;
	
	private boolean aprobada;
	
	private Integer cantCuotas;
	
	//@Column (columnDefinition = "int", nullable = true) //Aca vamos a tener que agarrar los ultimos 4 digitos en algun momento
	private String ultimos4DigitosTarjeta;
	
	private MedioDePago medioDePago;

	public MedioDePago getMedioDePago() {
		return medioDePago;
	}
	

	public TipoFactura getTipo() {
		return tipo;
	}


	public void setTipo(TipoFactura tipo) {
		this.tipo = tipo;
	}


	public String getCuit() {
		return cuit;
	}


	public void setCuit(String cuit) {
		this.cuit = cuit;
	}


	public LocalDate getFechaCobro() {
		return (fechaCobro == null ? null : LocalDate.fromCalendarFields(fechaCobro));
	}

	public void setFechaCobro(LocalDate fechaCobro) {
		this.fechaCobro = (fechaCobro == null ? null : fechaCobro.toDateTime(LocalTime.MIDNIGHT).toCalendar(Locale.getDefault()));
	}


	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}

	public Integer getId() {
		return id;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFechaVenta() {
		return (fechaVenta == null ? null : LocalDate.fromCalendarFields(fechaVenta));
	}

	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = (fechaVenta == null ? null : fechaVenta.toDateTime(LocalTime.MIDNIGHT).toCalendar(Locale.getDefault()));
	}

	public List<ItemVentaEntity> getItemVentas() {
		return items;
	}

	public void setItemVentas(List<ItemVentaEntity> itemVentas) {
		this.items = itemVentas;
	}


	public void setFechaVenta(Calendar fechaVenta) {
		this.fechaVenta = fechaVenta;
	}


	public void setFechaCobro(Calendar fechaCobro) {
		this.fechaCobro = fechaCobro;
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

	public String getUltimos4DigitosTarjeta() {
		return ultimos4DigitosTarjeta;
	}

	public void setUltimos4DigitosTarjeta(String ultimos4DigitosTarjeta) {
		this.ultimos4DigitosTarjeta = ultimos4DigitosTarjeta;
	}
	
	
	
		
}
