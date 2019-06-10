package entities;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import enumeraciones.EstadoCivil;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Genero;
import enumeraciones.Puesto;


@Entity
@Table(name="Empleados")
public class EmpleadoEntity {
	
	public EmpleadoEntity() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "int", nullable=false)
	private Integer legajoEmpleado;
	

	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String nombre;
	

	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String apellido;
	

	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String dni;
	

	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String domicilio;
	

	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String telefono;


	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String mail;

	

	private EstadoCivil estadoCivil;
	

	private Genero genero;

	@Column(name = "fechaNacimiento", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaNacimiento;

	@Column(name = "fechaIngreso", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaIngreso;

	@Column(name = "fechaEgreso", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaEgreso;
	
	private EstadoEmpleado estado;
	
	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String nacionalidad;
	
	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String password;

	@Column (columnDefinition = "float", nullable = true)
	private Float sueldoBase;
	
		
	@Column(columnDefinition = "int", nullable=true)
	private Integer horasAsignadas;
	
	@Enumerated(EnumType.STRING)
    @Column(length = 9)
	private Puesto puesto;
	
	private String cbu;

	public Integer getLegajoEmpleado() {
		return legajoEmpleado;
	}

	public void setLegajoEmpleado(Integer legajoEmpleado) {
		this.legajoEmpleado = legajoEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Calendar getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Calendar fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public EstadoEmpleado getEstado() {
		return estado;
	}

	public void setEstado(EstadoEmpleado estado) {
		this.estado = estado;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Float getSueldoBase() {
		return sueldoBase;
	}

	public void setSueldoBase(Float sueldoBase) {
		this.sueldoBase = sueldoBase;
	}

	public Integer getHorasAsignadas() {
		return horasAsignadas;
	}

	public void setHorasAsignadas(Integer horasAsignadas) {
		this.horasAsignadas = horasAsignadas;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	

}











