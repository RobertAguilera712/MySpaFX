package rest;

import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import model.Empleado;

public class Rest {
	private static final Client CLIENT = ClientBuilder.newClient();
	private static WebTarget target;
	private static final Gson GSON = new Gson();
	private static final String URL = "http://localhost:8080/MySpa/api";
	private static String json;

	public static <T> T[] obtenerRegistros(String nombreModulo, String estatus, Class<T[]> clase){
		// http://localhost:8080/MySpa/api/employee/getAll?e=1
		target = CLIENT.target(URL).path(nombreModulo).path("getAll").queryParam("e", estatus);
		json = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return GSON.fromJson(json, clase);
	}


	public static <T> T[] buscar(String nombreModulo, String estatus, String filtro, Class<T[]> clase){
		target = CLIENT.target(URL).path(nombreModulo).path("search").queryParam("filter", filtro).queryParam("e", estatus);
		json = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return GSON.fromJson(json, clase);
	}

	public static void agregarGet(String nombreModulo, String nuevoJson) {
		try {
			target = CLIENT.target(URL).path(nombreModulo).path("insert").queryParam("new", URLEncoder.encode(nuevoJson, "UTF-8").replaceAll("\\+", "%20"));
			json = target.request(MediaType.APPLICATION_JSON).get(String.class);
			System.out.println(json);
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(Rest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static Empleado login(String usuario, String password){
		// URL http://localhost:8080/MySpa/api
		// PATH http://localhost:8080/MySpa/api/employee/login
		target = CLIENT.target(URL).path("employee").path("login");

		Form form = new Form();
		form.param("usuario", usuario);
		form.param("password", password);

		json = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		
		return GSON.fromJson(json, Empleado.class);
	}

	public static void agregarPost(String nombreModulo, String nuevoJson){
		target = CLIENT.target(URL).path(nombreModulo).path("insert");

		System.out.println(nuevoJson);

		Form form = new Form();
		form.param("new", nuevoJson);

		json = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println(json);
	}

	public static void modificarPost(String nombreModulo, String nuevoJson){
		target = CLIENT.target(URL).path(nombreModulo).path("update");

		Form form = new Form();
		form.param("new", nuevoJson);

		json = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println(json);
	}

	public static void modificarGet(String nombreModulo, String nuevoJson){
		try {
			target = CLIENT.target(URL).path(nombreModulo).path("update").queryParam("new", URLEncoder.encode(nuevoJson, "UTF-8").replaceAll("\\+", "%20"));
			json = target.request(MediaType.APPLICATION_JSON).get(String.class);
			System.out.println(json);
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(Rest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void eliminar(String nombreModulo, String id){
		target = CLIENT.target(URL).path(nombreModulo).path("delete").queryParam("id", id);
		json = target.request(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(json);
	}

	
}
