package enumeraciones;

public enum Puesto {
	REPOSITOR (1, "Repositor"),
	SEGURIDAD (2, "Seguridad"),
	CAJERO (3, "Cajero"),
	SUPERVISOR (4, "Supervisor"),
	GERENTE (5, "Gerente");
	
	private final int id;
	private final String nombre;
	private Puesto(int id, String nombre) {
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
