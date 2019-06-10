package enumeraciones;

public enum TipoCuenta {
	CAJA_AHORRO (1, "Caja de Ahorro"),
	CTA_CTE (2, "Cuenta Corriente");
	
	private final int id;
	private final String nombre;
	private TipoCuenta(int id, String nombre) {
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
