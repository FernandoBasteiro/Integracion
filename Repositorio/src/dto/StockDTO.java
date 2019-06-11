package dto;

import java.io.Serializable;

public class StockDTO implements Serializable {

	private static final long serialVersionUID = 1013178992333396233L;
	private Integer cantidadMinimo;
	private Integer cantidadTotal;
	private Integer cantidadDisponible;
	public Integer getCantidadMinimo() {
		return cantidadMinimo;
	}
	public void setCantidadMinimo(Integer cantidadMinimo) {
		this.cantidadMinimo = cantidadMinimo;
	}
	public Integer getCantidadTotal() {
		return cantidadTotal;
	}
	public void setCantidadTotal(Integer cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}
	public Integer getCantidadDisponible() {
		return cantidadDisponible;
	}
	public void setCantidadDisponible(Integer cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}
	public StockDTO(Integer cantidadMinimo, Integer cantidadTotal, Integer cantidadDisponible) {
		this.cantidadMinimo = cantidadMinimo;
		this.cantidadTotal = cantidadTotal;
		this.cantidadDisponible = cantidadDisponible;
	}
	public StockDTO() {
	}
	
	
}
