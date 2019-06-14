package negocio;

import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import org.joda.time.LocalDate;

import controladores.ControladorVentas;
import controladores.ConversorFechas;
import daos.VentaDAO;
import dto.VentaDTO;
import enumeraciones.EstadoVenta;
import enumeraciones.MedioDePago;
import enumeraciones.TipoCuenta;
import enumeraciones.TipoFactura;
import excepciones.ExcepcionProceso;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VentaTarjetaDebito extends Venta {
	private String numeroTarjeta;
	private Integer codigoSeguridad;
	private String nombre;
	private String dni;
	private String fechaVto;
	private Integer nroOperacion;
	private Boolean aprobada;
	private Integer pin;
	private TipoCuenta tipoCuenta;
	
	public VentaTarjetaDebito(Integer id, LocalDate fechaVenta, List<ItemVenta> items, Empleado empleado,
			EstadoVenta estado, Float total, String numeroTarjeta, Integer codigoSeguridad, String nombre, String dni,
			String fechaVto, Integer nroOperacion, Boolean aprobada, Integer pin, TipoCuenta tipoCuenta, 
			TipoFactura tipoFact, String cuit, LocalDate fechaCobro) {
		super(id, fechaVenta, items, empleado, estado, total, tipoFact, cuit, fechaCobro);
		this.numeroTarjeta = numeroTarjeta;
		this.codigoSeguridad = codigoSeguridad;
		this.nombre = nombre;
		this.dni = dni;
		this.fechaVto = fechaVto;
		this.nroOperacion = nroOperacion;
		this.aprobada = aprobada;
		this.pin = pin;
		this.tipoCuenta = tipoCuenta;
	}
	
	public VentaTarjetaDebito(LocalDate fechaVenta, List<ItemVenta> items, Empleado empleado,
			EstadoVenta estado, Float total, String numeroTarjeta, Integer codigoSeguridad, String nombre, String dni,
			String fechaVto, Integer nroOperacion, Boolean aprobada, Integer pin, TipoCuenta tipoCuenta, 
			TipoFactura tipoFact, String cuit, LocalDate fechaCobro) {
		super(fechaVenta, items, empleado, estado, total, tipoFact, cuit, fechaCobro);
		this.numeroTarjeta = numeroTarjeta;
		this.codigoSeguridad = codigoSeguridad;
		this.nombre = nombre;
		this.dni = dni;
		this.fechaVto = fechaVto;
		this.nroOperacion = nroOperacion;
		this.aprobada = aprobada;
		this.pin = pin;
		this.tipoCuenta = tipoCuenta;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public Integer getCodigoSeguridad() {
		return codigoSeguridad;
	}
	public void setCodigoSeguridad(Integer codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getFechaVto() {
		return fechaVto;
	}
	public void setFechaVto(String fechaVto) {
		this.fechaVto = fechaVto;
	}
	public Integer getNroOperacion() {
		return nroOperacion;
	}
	public void setNroOperacion(Integer nroOperacion) {
		this.nroOperacion = nroOperacion;
	}
	public Boolean getAprobada() {
		return aprobada;
	}
	public void setAprobada(Boolean aprobada) {
		this.aprobada = aprobada;
	}
	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
	public VentaDTO getDTO () {
	
		return new VentaDTO (this.id, ConversorFechas.convertJodaToJava(this.fechaVenta), this.gesItemsDTO (), this.empleado.getDTO(),
				this.estado, this.total, MedioDePago.TARJETA_DEBITO, 
				null, null, //Datos EFVTO
				this.numeroTarjeta, this.codigoSeguridad, this.nombre, this.dni, //Datos Tarjetas TC+TD	
				this.fechaVto, this.nroOperacion, this.aprobada, //Datos Tarjetas TC+TD		
				null, //Datos TC			
				this.pin, this.tipoCuenta, //Datos TD
				this.tipoFact, this.cuit, ConversorFechas.convertJodaToJava(this.fechaCobro)); //Datos Factura
	}

	@Override
	public void grabar() {
		VentaDAO.getinstance().add(this);
	}
	
	@Override
	public void cancelarVenta() {
		for (ItemVenta i : items) {
			i.devolverProducto();
		}	
		this.setEstado(EstadoVenta.ANULADA);
		this.grabar();
	}


	
	
	@Override
	public void confirmar() throws ExcepcionProceso {
		
		JsonReader reader;
		try {
			
	        if (compraDebito().equals(200)) {
	        	this.aprobada = true;
	        	this.nroOperacion=this.getId();
	        }      
		} catch (Exception e) {
			throw new ExcepcionProceso("No se pudo confirmar la Tarjeta de Debito");
		}
		if (this.aprobada == null	) {
			throw new ExcepcionProceso ("La tarjeta fue rechazada.");
		}
			
	}

	public VentaTarjetaDebito(Integer id, LocalDate fechaVenta, List<ItemVenta> items, Empleado empleado,
			EstadoVenta estado, Float total, TipoFactura tipoFact, String cuit, LocalDate fechaCobro) {
		super(id, fechaVenta, items, empleado, estado, total, tipoFact, cuit, fechaCobro);
	}

	public VentaTarjetaDebito() {
		// TODO Auto-generated constructor stub
	}
	private Integer compraDebito() throws Exception {
		OkHttpClient client = new OkHttpClient();
		byte[] input = crearJsonDebito().getBytes("utf-8");
		RequestBody body = RequestBody.create(input);
		Request request = new Request.Builder()
		  .url("https://bank-back.herokuapp.com/api/v1/public/debitar/")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("User-Agent", "PostmanRuntime/7.15.0")
		  .addHeader("Accept", "*/*")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Host", "bank-back.herokuapp.com")
		  .build();
		Response response = client.newCall(request).execute();
		return response.code() ;
	}



	public String crearJsonDebito() {
		String cbuEstablecimiento = ControladorVentas.getInstance().getParamGral("ca_cbu");
		Integer codigoSeguridad = this.getCodigoSeguridad();
		String descripcion = ControladorVentas.getInstance().getParamGral("razonSocial");
		Float monto = this.getTotal();
		String numeroTarjeta = this.getNumeroTarjeta();
		
		String fechaVtoParaPasar = "20"+this.getFechaVto().substring(2, 4)+"-"+this.getFechaVto().substring(0, 2)
				+"-01T00:00:00.000";
		
		DecimalFormat priceFormatter = new DecimalFormat("#0.00");		
		         
		JsonObjectBuilder json = Json.createObjectBuilder()
				.add("cbuEstablecimiento", cbuEstablecimiento)
				.add("codigoSeguridad",codigoSeguridad)
				.add("descripcion",descripcion)
				.add("fechaVencimiento",fechaVtoParaPasar)
				.add("monto", monto)
				.add("numeroTarjeta", numeroTarjeta);
		return json.build().toString();
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
