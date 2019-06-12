
package enumeraciones;

public enum EstadoFactura {
	PENDIENTE (1,"Pendiente"),
	COBRADA (2, "Cobrada"),
	ANULADA (3, "Anulada");
	
	private final int id;
	private final String nombre;
	private EstadoFactura(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public static EstadoFactura fromId(Integer id) {
		for (EstadoFactura ef : EstadoFactura.values()) if (ef.getId() == id) return ef;
		return null;
	}

}
