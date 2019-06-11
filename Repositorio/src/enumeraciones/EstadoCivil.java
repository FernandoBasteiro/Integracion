package enumeraciones;

public enum EstadoCivil {
	SOLTERO (1, "Soltero"),
	CASADO (2, "Casado"),
	DIVORCIADO (3, "Divorciado"),
	VIUDO (4, "Viudo"),
	EN_CONCUBINATO (5, "En concubinato");
	
	private final int id;
	private final String nombre;
	private EstadoCivil(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public static EstadoCivil fromId(int id) {
		for (EstadoCivil ec : EstadoCivil.values()) if (ec.getId() == id) return ec;
		return null;
	}
	
}
