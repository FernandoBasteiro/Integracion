package entities;

import java.util.Calendar;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

@Entity
@Table(name="Novedades")
public class NovedadEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaCreacion;
	private Boolean esPaga;
	private Integer cantDias;
	private Integer mes;
	private Integer anio;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public LocalDate getFechaCreacion() {
		return (fechaCreacion == null ? null : LocalDate.fromCalendarFields(fechaCreacion));
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion.toDateTime(LocalTime.MIDNIGHT).toCalendar(Locale.getDefault());
	}
	public Boolean getEsPaga() {
		return esPaga;
	}
	public void setEsPaga(Boolean esPaga) {
		this.esPaga = esPaga;
	}
	public Integer getCantDias() {
		return cantDias;
	}
	public void setCantDias(Integer cantDias) {
		this.cantDias = cantDias;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public NovedadEntity(Integer id, LocalDate fechaCreacion, Boolean esPaga, Integer cantDias, Integer mes,
			Integer anio) {
		this.id = id;
		this.fechaCreacion = fechaCreacion.toDateTime(LocalTime.MIDNIGHT).toCalendar(Locale.getDefault());
		this.esPaga = esPaga;
		this.cantDias = cantDias;
		this.mes = mes;
		this.anio = anio;
	}
	public NovedadEntity() {
	}
	


}
