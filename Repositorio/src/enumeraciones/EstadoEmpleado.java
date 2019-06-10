package enumeraciones;

public enum EstadoEmpleado {
	ACTIVO (1, "Activo"),
	LICENCIA_PAGA (2, "Licencia Paga"),
	LICENCIA_NO_PAGA (3, "Licencia No Paga"),
	DESVINCULADO (4, "Desvinculado");
	
	private final int id;
	private final String nombre;
	private EstadoEmpleado(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	
	
}
