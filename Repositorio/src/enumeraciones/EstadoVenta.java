package enumeraciones;

public enum EstadoVenta {
	ABIERTA (1, "Abierta"),
	FACTURADA (2, "Facturada"),
	COBRADA (3, "Cobrada"),
	ANULADA (4, "Anulada");
	
	
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
	public static EstadoVenta fromId(Integer id) {
		for (EstadoVenta ev : EstadoVenta.values()) if (ev.getId() == id) return ev;
		return null;
	}
}
