package controladores;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Test {

	public static void main(String[] args) throws Exception {
		pegarleAlBanco();

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
	}
	
	public static String crearJson() {
		JsonObjectBuilder json = Json.createObjectBuilder();
		Long idUsuario = Long.parseLong("20332419006");
		String pwd = "password";
		String nombre = "Fernando"; //Fisica
		String apellido = "Basteiro"; //Juridica
		String razonSocial = "Super Sarasa";
		JsonObjectBuilder idRol = Json.createObjectBuilder().add("id", 1);
		JsonObjectBuilder idProducto = Json.createObjectBuilder().add("id", 5); //Fisica
		//JsonObjectBuilder idProducto = Json.createObjectBuilder().add("id", 6); //Juridica
		json.add("idUsuario", idUsuario);
		json.add("contrasena", pwd);
		json.add("nombre", nombre);
		json.add("apellido", apellido);
		json.add("idRol", idRol);
		json.add("idProducto", idProducto);
		
		return json.build().toString();
	}
}
