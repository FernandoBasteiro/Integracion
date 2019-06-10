package dto;

import java.time.LocalDateTime;
import java.util.List;

import enumeraciones.EstadoVenta;

public class VentaDTO {
	private Integer id;
	private LocalDateTime fechaVenta;
	private List<ItemVentaDTO> items;
	private EmpleadoDTO empleado;
	private EstadoVenta estado;
	private Float total;
}
