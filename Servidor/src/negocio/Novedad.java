package negocio;

import org.joda.time.LocalDate;

import controladores.ConversorFechas;
import dto.NovedadDTO;

public class Novedad {
	private Integer id;
	private LocalDate fechaCreacion;
	private Boolean esPaga;
	private Integer cantDias;
	private Integer mes;
	private Integer anio;
	
	public Novedad(Integer id, LocalDate fechaCreacion, Boolean esPaga, Integer cantDias, Integer mes, Integer anio) {
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.esPaga = esPaga;
		this.cantDias = cantDias;
		this.mes = mes;
		this.anio = anio;
	}
	
	public Novedad(LocalDate fechaCreacion, Boolean esPaga, Integer cantDias, Integer mes, Integer anio) {
		this.fechaCreacion = fechaCreacion;
		this.esPaga = esPaga;
		this.cantDias = cantDias;
		this.mes = mes;
		this.anio = anio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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

	public NovedadDTO getDTO() {
		return new NovedadDTO(this.id, ConversorFechas.convertJodaToJava(this.fechaCreacion), this.esPaga, this.cantDias, this.mes, this.anio);
	}
}
