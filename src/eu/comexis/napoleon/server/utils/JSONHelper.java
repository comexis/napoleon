package eu.comexis.napoleon.server.utils;

import org.json.JSONArray;

import eu.comexis.napoleon.server.dao.CompanyDao;
import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Company;

public class JSONHelper {

  public static String toJSONArray(ApplicationUser user) {
    JSONArray json = new JSONArray();
    json.put(user.getEmail());
    json.put(user.getFirstName());
    json.put(user.getLastName());

    Company c = new CompanyDao().getById(user.getCompany().getName());
    json.put(toJSONArray(c));

    return json.toString();
  }

  private static JSONArray toJSONArray(Company c) {
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

  private JSONHelper() {
  }

}
