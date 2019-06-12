package enumeraciones;

public enum TipoFactura {
	A (1, "A"),
	B (2, "B"),
	C (3, "C");
	
	private final int id;
	private final String nombre;
	private TipoFactura(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public static TipoFactura fromId(Integer id) {
		for (TipoFactura tf : TipoFactura.values()) if (tf.getId() == id) return tf;
		return null;
	}
}
