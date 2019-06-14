package controladores;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println(averiguarCBUEmpleado());
		System.out.println(compraCredito());

	}

	private static void listarEmpleados() throws Exception {
		URL url = new URL("https://bank-back.herokuapp.com/api/v1/cuentas/1231231231");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// conn.setRequestProperty("Content-Length",
		// String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		// conn.getOutputStream().write();

		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println(response.toString());
		}
		
		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char) c);
		String response = sb.toString();
	}

	private static void pegarleAlBanco() throws Exception {
		URL url = new URL("https://bank-back.herokuapp.com/api/v1/usuario");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("PUT");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		String jsonInputString = crearJson();

		 try (OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println(response.toString());
		}
		con.getResponseCode();
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

		MediaType mediaType = MediaType.parse("application/json");
		//RequestBody body = RequestBody.create(mediaType, "{\n    \"tarjeta\": \"5491326606977978\",\n    \"idEstablecimiento\": \"2\",\n    \"nroComprobante\": \"123123\",\n    \"detalleTransaccion\": \"Cafe de Prueba\",\n    \"importeTotal\": \"500.50\",\n    \"cuotas\": \"1\",\n    \"cvc\": \"866\"\n}");
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

}
