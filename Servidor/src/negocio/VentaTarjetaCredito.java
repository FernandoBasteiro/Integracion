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
import enumeraciones.TipoFactura;
import excepciones.ExcepcionProceso;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VentaTarjetaCredito extends Venta {
	private String numeroTarjeta;
	private Integer codigoSeguridad;
	private String nombre;
	private String dni;
	private String fechaVto;
	private Integer nroOperacion;
	private Boolean aprobada;
	private Integer cantCuotas;
	
	public VentaTarjetaCredito(Integer id, LocalDate fechaVenta, List<ItemVenta> items, Empleado empleado,
			EstadoVenta estado, Float total, String numeroTarjeta, Integer codigoSeguridad, String nombre, String dni,
			String fechaVto, Integer nroOperacion, Boolean aprobada, Integer cantCuotas, 
			TipoFactura tipoFact, String cuit, LocalDate fechaCobro) {
		super(id, fechaVenta, items, empleado, estado, total, tipoFact, cuit, fechaCobro);
		this.numeroTarjeta = numeroTarjeta;
		this.codigoSeguridad = codigoSeguridad;
		this.nombre = nombre;
		this.dni = dni;
		this.fechaVto = fechaVto;
		this.nroOperacion = nroOperacion;
		this.aprobada = aprobada;
		this.cantCuotas = cantCuotas;
	}
	
	public VentaTarjetaCredito(LocalDate fechaVenta, List<ItemVenta> items, Empleado empleado,
			EstadoVenta estado, Float total, String numeroTarjeta, Integer codigoSeguridad, String nombre, String dni,
			String fechaVto, Integer nroOperacion, Boolean aprobada, Integer cantCuotas, 
			TipoFactura tipoFact, String cuit, LocalDate fechaCobro) {
		super(fechaVenta, items, empleado, estado, total, tipoFact, cuit, fechaCobro);
		this.numeroTarjeta = numeroTarjeta;
		this.codigoSeguridad = codigoSeguridad;
		this.nombre = nombre;
		this.dni = dni;
		this.fechaVto = fechaVto;
		this.nroOperacion = nroOperacion;
		this.aprobada = aprobada;
		this.cantCuotas = cantCuotas;
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
	public Integer getCantCuotas() {
		return cantCuotas;
	}
	public void setCantCuotas(Integer cantCuotas) {
		this.cantCuotas = cantCuotas;
	}
	
	public VentaDTO getDTO () {
		 
		return new VentaDTO (this.id, ConversorFechas.convertJodaToJava(this.fechaVenta), this.gesItemsDTO (), this.empleado.getDTO(),
				this.estado, this.total, MedioDePago.TARJETA_CREDITO, 
				null, null, //Datos EFVTO
				this.numeroTarjeta, this.codigoSeguridad, this.nombre, this.dni, //Datos Tarjetas TC+TD	
				this.fechaVto, this.nroOperacion, this.aprobada, //Datos Tarjetas TC+TD		
				this.cantCuotas, //Datos TC			
				null, null, //Datos TD
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
	public void confirmar(Integer nroProxFactura) throws ExcepcionProceso {
		
		JsonReader reader;
		try {
			reader = Json.createReader(new StringReader(compraCredito(nroProxFactura)));
			JsonObject cuentasArr = reader.readObject();
	        reader.close();	
	        if (cuentasArr.getInt("code") == 200) {
	        	this.aprobada = true;
	        	this.nroOperacion=cuentasArr.getInt("transaccion");
	        }
		} catch (Exception e) {
			throw new ExcepcionProceso("No se pudo confirmar la Tarjeta de Credito");
		}
		if (this.aprobada == null) throw new ExcepcionProceso ("La tarjeta fue rechazada.");
			
	}

	public VentaTarjetaCredito(Integer id, LocalDate fechaVenta, List<ItemVenta> items, Empleado empleado,
			EstadoVenta estado, Float total, TipoFactura tipoFact, String cuit, LocalDate fechaCobro) {
		super(id, fechaVenta, items, empleado, estado, total, tipoFact, cuit, fechaCobro);
	}

	public VentaTarjetaCredito() {
		// TODO Auto-generated constructor stub
	}
	private String compraCredito(Integer nroProxFactura) throws Exception {
		OkHttpClient client = new OkHttpClient();
		byte[] input = crearJsonCredito(nroProxFactura).getBytes("utf-8");
		RequestBody body = RequestBody.create(input);
		Request request = new Request.Builder()
		  .url("http://paypauli.herokuapp.com/api/txn/")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("User-Agent", "PostmanRuntime/7.15.0")
		  .addHeader("Accept", "*/*")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Host", "paypauli.herokuapp.com")
		  .addHeader("accept-encoding", "gzip, deflate")
		  .addHeader("Connection", "keep-alive")
		  .addHeader("cache-control", "no-cache")
		  .build();

		Response response = client.newCall(request).execute();
		return response.body().string();
	}



	public String crearJsonCredito(Integer nroProxFactura) {
		String numeroTarjeta = this.getNumeroTarjeta();
		String idEstablecimiento = ControladorVentas.getInstance().getParamGral("tc_id_establecimiento");
		//String nroComprobante = this.getId().toString();
		String detalleTransaccion = ControladorVentas.getInstance().getParamGral("razonSocial");
		Float importeTotal =  this.getTotal();
		String cuotas = this.getCantCuotas().toString();
		String cvc = this.getCodigoSeguridad().toString();
		
		
		DecimalFormat priceFormatter = new DecimalFormat("#0.00");
		
		
		JsonObjectBuilder json = Json.createObjectBuilder()
				.add("tarjeta", numeroTarjeta)
				.add("idEstablecimiento",idEstablecimiento)
				.add("nroComprobante",nroProxFactura.toString())
				.add("detalleTransaccion",detalleTransaccion)
				.add("importeTotal", priceFormatter.format(importeTotal).toString())
				.add("cuotas",cuotas)
				.add("cvc", cvc);
		return json.build().toString();
	}

}
