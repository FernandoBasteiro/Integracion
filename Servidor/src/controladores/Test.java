package controladores;

import java.io.IOException;
import java.io.StringReader;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import daos.VentaDAO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println(consultarCobranza());
	}

	
	private static void crearCuentaBancaria() throws Exception {
		OkHttpClient client = new OkHttpClient();
		byte[] input = crearJson().getBytes("utf-8");
		RequestBody body = RequestBody.create(input);
		Request request = new Request.Builder()
		  .url("https://bank-back.herokuapp.com/api/v1/usuario")
		  .put(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("Postman-Token", "c17b7809-88f5-4b43-a848-93ac24536b41")
		  .build();

		Response response = client.newCall(request).execute();
	}
	
	public static String crearJson() {
		JsonObjectBuilder json = Json.createObjectBuilder();
		Long idUsuario = Long.parseLong("30313231344");
		String pwd = "password";
		String nombre = "Fernando"; //Fisica
		String apellido = "Basteiro"; //Juridica
		//String razonSocial = "Super Sarasa S.R.L.";
		JsonObjectBuilder idRol = Json.createObjectBuilder().add("id", 1);
		//JsonObjectBuilder idProducto = Json.createObjectBuilder().add("id", 5); //Fisica
		JsonObjectBuilder idProducto = Json.createObjectBuilder().add("id", 6); //Juridica
		json.add("idUsuario", idUsuario);
		json.add("contrasena", pwd);
		json.add("nombre", nombre);
		json.add("apellido", apellido);
		//json.add("razonSocial", razonSocial);
		json.add("idRol", idRol);
		json.add("idProducto", idProducto);
		
		return json.build().toString();
	}
		
	private static String averiguarCBUEmpleado() throws Exception {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("https://bank-back.herokuapp.com/api/v1/cuentas/23308334589").get().build();
		Response response = client.newCall(request).execute();
		JsonReader reader = Json.createReader(new StringReader(response.body().string()));
		JsonArray cuentasArr = reader.readArray();
        reader.close();
        List<JsonObject> cuentas = cuentasArr.getValuesAs(JsonObject.class);
		for (JsonObject cuenta : cuentas) {
			if (cuenta.getString("tipoCuenta").equals("CAJA_AHORRO")) return cuenta.getString("cbu");
		}
		return null;
	}
	
	private static String compraCredito() throws Exception {
		OkHttpClient client = new OkHttpClient();
		byte[] input = crearJsonCredito().getBytes("utf-8");
		RequestBody body = RequestBody.create(input);
		Request request = new Request.Builder()
		  .url("http://paypauli.herokuapp.com/api/txn/")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("User-Agent", "PostmanRuntime/7.15.0")
		  .addHeader("Accept", "*/*")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "c2ccb434-9399-466b-929e-be30277f3ed2,516af216-ea59-4356-8c61-46cc463a964a")
		  .addHeader("Host", "paypauli.herokuapp.com")
		  .addHeader("accept-encoding", "gzip, deflate")
		  .addHeader("content-length", "210")
		  .addHeader("Connection", "keep-alive")
		  .addHeader("cache-control", "no-cache")
		  .build();

		Response response = client.newCall(request).execute();
		return response.body().string();
	}
	
	public static String crearJsonCredito() {
		JsonObjectBuilder json = Json.createObjectBuilder()
				.add("tarjeta", "5491326606977978")
				.add("idEstablecimiento", "9")
				.add("nroComprobante", "1234")
				.add("detalleTransaccion", "Super Sarasa S.R.L.")
				.add("importeTotal", "500.50")
				.add("cuotas", "1")
				.add("cvc", "866");
		return json.build().toString();
	}

	private static Integer compraDebito() throws Exception {
		OkHttpClient client = new OkHttpClient();
		byte[] input = crearJsonDebito().getBytes("utf-8");
		RequestBody body = RequestBody.create(input);
		Request request = new Request.Builder()
		  .url("https://bank-back.herokuapp.com/api/v1/public/debitar")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("User-Agent", "PostmanRuntime/7.15.0")
		  .addHeader("Accept", "*/*")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "01dc7d2e-4d70-41d7-94d7-d164671fb4d5,5118ffc9-c697-4fb7-a605-6ccce3b81a4a")
		  .addHeader("Host", "bank-back.herokuapp.com")
		  .addHeader("accept-encoding", "gzip, deflate")
		  .addHeader("content-length", "229")
		  .addHeader("Connection", "keep-alive")
		  .addHeader("cache-control", "no-cache")
		  .build();

		Response response = client.newCall(request).execute();
		return response.code();
	}
	
	public static String crearJsonDebito() {
		JsonObjectBuilder json = Json.createObjectBuilder()
				.add("cbuEstablecimiento", "1234567891011614055292")
				.add("codigoSeguridad", 885)
				.add("descripcion", "Super Sarasa S.R.L.")
				.add("fechaVencimiento", "2020-06-01T00:00:00.000")
				.add("monto", 150)
				.add("numeroTarjeta", "4517650281045768");
		return json.build().toString();
	}
	
	public static ArrayList<Integer> consultarCobranza() throws Exception {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("http://paypauli.herokuapp.com/api/consultar/resumen/abonados/9/201906").get().build();
		Response response = client.newCall(request).execute();
		String json = response.body().string();
		try {
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonArray cobrosArr = reader.readArray();
        reader.close();
        ArrayList<Integer> codOps = new ArrayList<Integer>();
        List<JsonObject> cobros = cobrosArr.getValuesAs(JsonObject.class);
		for (JsonObject cobro : cobros) {
			codOps.add(cobro.getInt("comprobante"));
		}
		return codOps;
		}
		catch (Exception e) {
			throw new Exception();
		}
	}
	
}
