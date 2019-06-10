package enumeraciones;

public enum Genero {
	FEMENINO (1, "Femenino"),
	MASCULINO (2, "Masculino"),
	NO_BINARIO (3, "No Binario");
	
	private final int id;
	private final String nombre;
	private Genero(int id, String nombre) {
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
