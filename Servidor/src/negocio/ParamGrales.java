package negocio;

import dto.ParamGralesDTO;
import dto.ProductoDTO;

public class ParamGrales {

	private Integer id;
	private String clave;
	private String valor;
	public ParamGrales(Integer id, String clave, String valor) {
		super();
		this.id = id;
		this.clave = clave;
		this.valor = valor;
	}
	public ParamGrales(String clave, String valor) {
		super();
		this.clave = clave;
		this.valor = valor;
	}
	public ParamGrales() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	public ParamGralesDTO getDTO() {
		return new ParamGralesDTO(this.id,this.clave,this.valor);
		}
	
}
