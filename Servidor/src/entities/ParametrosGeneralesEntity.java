package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ParametrosGenerales")
public class ParametrosGeneralesEntity {
	
	private String tc_establecimiento;
	private String bco_cuit;
	private String bco_cbu;
	private String bco_razonSocial;
	private Integer lqs_id;

}
