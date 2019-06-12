package controladores;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

	public static void main(String[] args) throws Exception {
		listarEmpleados();

	}

	private static void listarEmpleados() throws Exception {
		URL url = new URL("http://localhost:8080/Web/Private/listarProductos");
		/*
		 * Map<String,Object> params = new LinkedHashMap<>(); params.put("name",
		 * "Freddie the Fish"); params.put("email", "fishie@seamail.example.com");
		 * params.put("reply_to_thread", 10394); params.put("message",
		 * "Shark attacks in Botany Bay have gotten out of control. We need more defensive dolphins to protect the schools here, but Mayor Porpoise is too busy stuffing his snout with lobsters. He's so shellfish."
		 * );
		 */

		/*
		 * StringBuilder postData = new StringBuilder(); for (Map.Entry<String,Object>
		 * param : params.entrySet()) { if (postData.length() != 0)
		 * postData.append('&'); postData.append(URLEncoder.encode(param.getKey(),
		 * "UTF-8")); postData.append('=');
		 * postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
		 * "UTF-8")); } byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		 */

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// conn.setRequestProperty("Content-Length",
		// String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		// conn.getOutputStream().write();

		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char) c);
		String response = sb.toString();
	}

	private static void pegarleAlBanco() throws Exception {
		URL url = new URL("http://localhost:8080/Web/Private/listarProductos");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		String jsonInputString = null; // JSON

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

		/*
		 * Map<String,Object> params = new LinkedHashMap<>(); params.put("name",
		 * "Freddie the Fish"); params.put("email", "fishie@seamail.example.com");
		 * params.put("reply_to_thread", 10394); params.put("message",
		 * "Shark attacks in Botany Bay have gotten out of control. We need more defensive dolphins to protect the schools here, but Mayor Porpoise is too busy stuffing his snout with lobsters. He's so shellfish."
		 * );
		 */

		/*
		 * StringBuilder postData = new StringBuilder(); for (Map.Entry<String,Object>
		 * param : params.entrySet()) { if (postData.length() != 0)
		 * postData.append('&'); postData.append(URLEncoder.encode(param.getKey(),
		 * "UTF-8")); postData.append('=');
		 * postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
		 * "UTF-8")); } byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		 */

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// conn.setRequestProperty("Content-Length",
		// String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		// conn.getOutputStream().write();

		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char) c);
		String response = sb.toString();
	}
}
