package negocio;

import org.joda.time.LocalDate;
import dto.EmpleadoDTO;
import enumeraciones.EstadoCivil;
import enumeraciones.EstadoEmpleado;
import enumeraciones.Genero;
import enumeraciones.Puesto;
 
public class Empleado {
	private String nombre;
	private String apellido;
	private Integer legajo;
	private String dni;
	private String domicilio;
	private String telefono;
	private String email;
	private EstadoCivil estadoCivil;
	private Genero genero;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIngreso;
	private LocalDate fechaEgreso;
	private EstadoEmpleado estadoEmpleado;
	private String nacionalidad;
	private String password;
	private Float sueldoBase;
	private Integer horasAsignadas;
	private Puesto puesto;
	private String cbu;
	private String session;
	
	public Empleado(String nombre, String apellido, Integer legajo, String dni, String domicilio, String telefono,
			String email, EstadoCivil estadoCivil, Genero genero, LocalDate fechaNacimiento, LocalDate fechaIngreso,
			LocalDate fechaEgreso, EstadoEmpleado estadoEmpleado, String nacionalidad, String password,
			Float sueldoBase, Integer horasAsignadas, Puesto puesto, String cbu, String session) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.legajo = legajo;
		this.dni = dni;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.email = email;
		this.estadoCivil = estadoCivil;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.estadoEmpleado = estadoEmpleado;
		this.nacionalidad = nacionalidad;
		this.password = password;
		this.sueldoBase = sueldoBase;
		this.horasAsignadas = horasAsignadas;
		this.puesto = puesto;
		this.cbu = cbu;
		this.session = session;
	}
	public Empleado(String nombre, String apellido, String dni, String domicilio, String telefono, String email,
			EstadoCivil estadoCivil, Genero genero, LocalDate fechaNacimiento, LocalDate fechaIngreso,
			LocalDate fechaEgreso, EstadoEmpleado estadoEmpleado, String nacionalidad, String password,
			Float sueldoBase, Integer horasAsignadas, Puesto puesto, String cbu, String session) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.email = email;
		this.estadoCivil = estadoCivil;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.estadoEmpleado = estadoEmpleado;
		this.nacionalidad = nacionalidad;
		this.password = password;
		this.sueldoBase = sueldoBase;
		this.horasAsignadas = horasAsignadas;
		this.puesto = puesto;
		this.cbu = cbu;
		this.session = session;
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
	public Integer getLegajo() {
		return legajo;
	}
	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public LocalDate getFechaEgreso() {
		return fechaEgreso;
	}
	public void setFechaEgreso(LocalDate fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}
	public EstadoEmpleado getEstadoEmpleado() {
		return estadoEmpleado;
	}
	public void setEstadoEmpleado(EstadoEmpleado estadoEmpleado) {
		this.estadoEmpleado = estadoEmpleado;
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
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public Empleado() {
		super();
	}
	
	public boolean verificarPassword(String p) {
		if (this.password == p) {
			return true;
		}
		return false;
	}
	
	public EmpleadoDTO getDTO () {
		//TODO ENVIAR PARAMETROS 
		return new EmpleadoDTO ();
	}

}
