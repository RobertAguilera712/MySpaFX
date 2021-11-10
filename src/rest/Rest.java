package rest;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class Rest {
	private static final Client client = ClientBuilder.newClient();
	private static WebTarget target;
	private static final Gson gson = new Gson();
	private static final String url = "http://localhost:8080/MySpa/api";
	private static String json;

	public static <T> T[] obtenerRegistros(String nombreModulo, String estatus, Class<T[]> clase){
		// http://localhost:8080/MySpa/api/employee/getAll?e=1
		target = client.target(url).path(nombreModulo).path("getAll").queryParam("e", estatus);
		json = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return gson.fromJson(json, clase);
	}

	public static <T> T[] buscar(String nombreModulo, String estatus, String filtro, Class<T[]> clase){
		target = client.target(url).path(nombreModulo).path("search").queryParam("filter", filtro).queryParam("e", estatus);
		json = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return gson.fromJson(json, clase);
	}

	public static void agregarPost(String nombreModulo, String nuevoJson){
		target = client.target(url).path(nombreModulo).path("insert");

		Form form = new Form();
		form.param("new", nuevoJson);

		json = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println(json);
	}

	public static void modificarPost(String nombreModulo, String nuevoJson){
		target = client.target(url).path(nombreModulo).path("update");

		Form form = new Form();
		form.param("new", nuevoJson);

		json = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println(json);
	}

	public static void eliminar(String nombreModulo, String id){
		target = client.target(url).path(nombreModulo).path("delete").queryParam("id", id);
		json = target.request(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(json);
	}

	
}
