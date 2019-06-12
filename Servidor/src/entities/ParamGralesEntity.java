package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ParamGrales")
public class ParamGralesEntity {
	
	public ParamGralesEntity () {
	}
	
	@Id
	private String  cuit; // los usa el bco + liquidar sueldo
	
	private Integer tc_id_establecimiento;	 //lo usa la entidad crediticia
	private String  bco_cbu;          	     //lo usa la entidad bancaria
	private String  bco_razonSocial;	 	//lo usa la entidad bancaria
	
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public Integer getTc_id_establecimiento() {
		return tc_id_establecimiento;
	}
	public void setTc_id_establecimiento(Integer tc_id_establecimiento) {
		this.tc_id_establecimiento = tc_id_establecimiento;
	}
	public String getBco_cbu() {
		return bco_cbu;
	}
	public void setBco_cbu(String bco_cbu) {
		this.bco_cbu = bco_cbu;
	}
	public String getBco_razonSocial() {
		return bco_razonSocial;
	}
	public void setBco_razonSocial(String bco_razonSocial) {
		this.bco_razonSocial = bco_razonSocial;
	}
	
	

}
