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

	public static <T> T[] getAll(String moduleName, String status, Class<T[]> clazz){
		target = client.target(url).path(moduleName).path("getAll").queryParam("e", status);
		json = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return gson.fromJson(json, clazz);
	}
	
}
