package dto;
import java.io.Serializable;

public class ParamGralesDTO implements Serializable{

	private Integer id;
	private String clave;
	private String valor;
	public ParamGralesDTO(Integer id, String clave, String valor) {
		super();
		this.id = id;
		this.clave = clave;
		this.valor = valor;
	}
	public ParamGralesDTO(String clave, String valor) {
		super();
		this.clave = clave;
		this.valor = valor;
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
	
	
	
	
	
}
