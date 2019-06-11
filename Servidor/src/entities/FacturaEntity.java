package entities;

import java.util.Calendar;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;


import enumeraciones.EstadoCivil;
import enumeraciones.EstadoFactura;
import enumeraciones.TipoFactura;


@Entity
@Table(name="Facturas")
public class FacturaEntity {
	
	public FacturaEntity() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "int", nullable=false)
	private Integer numero;
	
	@Column(name = "fechaFacturacion", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaFacturacion;
		
	@Enumerated(EnumType.STRING)
    @Column(length = 9)
	private TipoFactura tipo;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id")
	private VentaEntity venta;
	
	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String cuit;
		
	@Enumerated(EnumType.STRING)
    @Column(length = 9)
	private EstadoFactura estado;
	
	@Column(name = "fechaCobro", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaCobro;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public LocalDate getFechaFacturacion() {
		return (fechaFacturacion == null ? null : LocalDate.fromCalendarFields(fechaFacturacion));
	}

	public void setFechaFacturacion(LocalDate fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion.toDateTime(LocalTime.MIDNIGHT).toCalendar(Locale.getDefault());
	}

	public TipoFactura getTipo() {
		return tipo;
	}

	public void setTipo(TipoFactura tipo) {
		this.tipo = tipo;
	}

	public VentaEntity getVenta() {
		return venta;
	}

	public void setVenta(VentaEntity venta) {
		this.venta = venta;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public EstadoFactura getEstado() {
		return estado;
	}

	public void setEstado(EstadoFactura estado) {
		this.estado = estado;
	}

	public LocalDate getFechaCobro() {
		return (fechaCobro == null ? null : LocalDate.fromCalendarFields(fechaCobro));
	}

	public void setFechaCobro(LocalDate fechaCobro) {
		this.fechaCobro = fechaCobro.toDateTime(LocalTime.MIDNIGHT).toCalendar(Locale.getDefault());
	}

	
}
