package enumeraciones;

import java.io.Serializable;

public enum EstadoEmpleado {
	ACTIVO (1, "Activo"),
	DESVINCULADO (2, "Desvinculado"),
	ANULADO (3, "Anulado");
	
	private final int id;
	private final String nombre;
	private EstadoEmpleado(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public static EstadoEmpleado fromId(Integer id) {
		for (EstadoEmpleado ee : EstadoEmpleado.values()) if (ee.getId() == id) return ee;
		return null;
	}
	
}
