package eu.comexis.napoleon.server.utils;

import org.json.JSONArray;

import eu.comexis.napoleon.shared.model.AppUser;
import eu.comexis.napoleon.shared.model.Client;

public class JSONHelper {
	
	private JSONHelper() {
	}
	
	
	public static String toJSONArray(AppUser user){
		JSONArray json = new JSONArray();
		json.put(user.getEmail());
		json.put(user.getFirstName());
		json.put(user.getLastName());
		json.put(toJSONArray(user.getClient()));
		
		return json.toString();
	}
	
	private static JSONArray toJSONArray(Client c){
		JSONArray json = new JSONArray();
		json.put(c.getId());
		json.put(c.getAddress());
		json.put(c.getEmail());
		json.put(c.getFax());
		json.put(c.getName());
		json.put(c.getTelephone());
		json.put(c.getUrl());
		
		return json;
	}

}
