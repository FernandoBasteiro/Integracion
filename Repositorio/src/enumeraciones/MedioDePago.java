package enumeraciones;

public enum MedioDePago {
	EFECTIVO (1, "Efectivo"),
	TARJETA_CREDITO(2, "Tarjeta de Credito"),
	TARJETA_DEBITO(3, "Tarjeta de Debito");
	
	private final int id;
	private final String nombre;
	private MedioDePago(int id, String nombre) {
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
