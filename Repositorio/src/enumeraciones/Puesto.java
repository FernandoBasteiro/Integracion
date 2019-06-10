package enumeraciones;

public enum Puesto {
	CAJERO (1, "Cajero"),
	REPOSITOR (2, "Repositor"),
	SUPERVISOR (3, "Supervisor"),
	GERENTE (4, "Gerente"),
	SEGURIDAD (5, "Seguridad");
	
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
