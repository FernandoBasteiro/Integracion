package enumeraciones;

public enum EstadoVenta {
	ABIERTA (1, "Abierta"),
	FACTURADA (2, "Facturada"),
	CANCELADA (3, "Cancelada");
	
	
	private final int id;
	private final String nombre;
	private EstadoVenta(int id, String nombre) {
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
