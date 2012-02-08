package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.googlecode.objectify.Key;

import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

public class TenantDao extends NapoleonDao<Tenant> {
  public TenantDao() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Tenant create(String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    Tenant tenant = new Tenant();
    System.out.println("Set company key " + companyKey.toString());
    tenant.setCompany(companyKey);
    return tenant;
  }

  /**
   * Retrieves the list of all tenants stored in the database (with the minimum of data to be shown
   * in UI)
   * 
   * @return The list of tenants
   */
  public ArrayList<SimpleTenant> getListSimpleTenants(String companyId) {
    LOG.info("Get list Tenants (" + clazz + ") for company " + companyId);
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    Iterator<Tenant> iterator = this.listAll(companyId).iterator();
    ArrayList<SimpleTenant> tenants = new ArrayList<SimpleTenant>();
    while (iterator.hasNext()) {
      Tenant tenant = iterator.next();
      SimpleTenant o = new SimpleTenant();
      o.setId(tenant.getId());
      o.setName(tenant.getLastName());
      o.setCity(tenant.getCity());
      o.setAddress(tenant.getStreet());
      o.setMobileNumber(tenant.getMobilePhoneNumber());
      o.setPhoneNumber(tenant.getPhoneNumber());
      tenants.add(o);
    }
    return tenants;
  }
  public Tenant update(Tenant tenant, String companyId) {
    String tenantId = tenant.getId();
    LOG.info("Get key for Company ID: " + companyId);
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    CountryDao countryData = new CountryDao();
    if (tenantId == null || tenantId.length() == 0) {
      UUID uuid = UUID.randomUUID();
      System.out.println("Creating Uuid " + uuid.toString());
      tenant.setId(uuid.toString());
      tenant.setCompany(companyKey);
    }
    // if country does not exist, create it.
    Country country = countryData.getByName(tenant.getCountry(), companyKey);
    if (country == null) {
      country = countryData.create(companyKey);
      country.setName(tenant.getCountry());
      countryData.update(country);
    }
    City city = countryData.getCityByName(country.getId(), tenant.getCity());
    if (city == null) {
      city = countryData.addCity(country.getId(), tenant.getCity());
    }
    return update(tenant);
  }
}
