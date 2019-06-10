package negocio;

import java.time.LocalDateTime;
import java.util.List;

import enumeraciones.EstadoVenta;

public class Venta {
	private Integer id;
	private LocalDateTime fechaVenta;
	private List<ItemVenta> items;
	private Empleado empleado;
	private EstadoVenta estado;
	private Float total;
}
