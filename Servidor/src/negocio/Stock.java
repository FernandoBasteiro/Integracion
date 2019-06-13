package negocio;

import daos.StockDAO;
import dto.StockDTO;
import excepciones.ExcepcionProceso;

public class Stock {
	private Integer id;
	private Integer cantidadMinimo;
	private Integer cantidadTotal;
	private Integer cantidadDisponible;
	
	public Stock(Integer cantidadMinimo, Integer cantidadTotal, Integer cantidadDisponible) {
		super();
		this.cantidadMinimo = cantidadMinimo;
		this.cantidadTotal = cantidadTotal;
		this.cantidadDisponible = cantidadDisponible;
	}
	public Stock(Integer id, Integer cantidadMinimo, Integer cantidadTotal, Integer cantidadDisponible) {
		super();
		this.id=id;
		this.cantidadMinimo = cantidadMinimo;
		this.cantidadTotal = cantidadTotal;
		this.cantidadDisponible = cantidadDisponible;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Stock() {
		super();
	}
	
	public void devolverStock(Integer cant) {
		this.cantidadDisponible += cant;
	}
	
	public void bajaStock() {
		this.cantidadMinimo = 0;
		this.cantidadTotal = 0;
		this.cantidadTotal = 0;
	}
	
	public void descontarStock(Integer cant) throws ExcepcionProceso {
		if (cant <= this.cantidadDisponible) {
			this.cantidadDisponible -= cant;
		}
		else throw new ExcepcionProceso("No hay stock disponible.");
	}
	
	public StockDTO getDTO () {
		return new StockDTO (this.cantidadMinimo, this.cantidadTotal, this.cantidadDisponible);
	}
	
	public void guardar() {
		StockDAO.getinstance().add(this);
	}
	
	public void actualizarStock(Integer cTot, Integer cDisp, Integer cMin) {
		this.cantidadTotal = cTot;
		this.cantidadMinimo = cMin;
		this.cantidadDisponible = cDisp;		
	}
}
