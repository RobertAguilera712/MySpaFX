package rest;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class Rest {
	private static final Client client = ClientBuilder.newClient();
	private static WebTarget target;
	private static final Gson gson = new Gson();
	private static Type listType;
	private static final String url = "http://localhost:8080/MySpa/api";
	private static String json;

	public static <T> T[] obtenerRegistros(String nombreModulo, String estatus, Class<T[]> clase){
		target = client.target(url).path(nombreModulo).path("getAll").queryParam("e", estatus);
		json = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return gson.fromJson(json, clase);
	}

	public static <T> T[] buscar(String nombreModulo, String estatus, String filtro, Class<T[]> clase){
		target = client.target(url).path(nombreModulo).path("search").queryParam("filter", filtro).queryParam("e", estatus);
		json = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return gson.fromJson(json, clase);
	}

	
}
