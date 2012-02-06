package eu.comexis.napoleon.server.dao;

import java.util.List;
import java.util.UUID;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

import eu.comexis.napoleon.shared.model.Company;

public class CompanyDao extends DAOBase {
	
	static {
		ObjectifyService.register(Company.class);
	}

	public Company create() {
		Company company = new Company();
		return company;
	}

	public Boolean delete(Company company) {
		try {
			ofy().delete(company);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Company getById(String id) {
		try {
			Company company = ofy().get(Company.class, id);
			return company;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Company getByName(String name) {
		try {
			Company company = ofy().query(Company.class).filter("name =", name)
					.get();
			return company;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Company update(Company company) {
		String ownerId = company.getId();

		if (ownerId == null || ownerId.length() == 0) {
			UUID uuid = UUID.randomUUID();
			System.out.println("Creating Uuid " + uuid.toString());
			company.setId(uuid.toString());
		}
		try {
			Key<Company> entityKey = ofy().put(company);
			return ofy().get(entityKey);
		} catch (Exception e) {
			// should raise a NapoleonDaoUpdateFailed exception
			e.printStackTrace();
			return null;
		}
	}
	public void deleteAll(Iterable<Company> entities) {
    ofy().delete(entities);
  }
	public List<Company> listAll() {
    Query<Company> q = ofy().query(Company.class);
    return q.list();
  }
}
