package eu.comexis.napoleon.server.dao;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Query;

import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Company;

public class ApplicationUserDao extends NapoleonDao<ApplicationUser> {

	public static Log LOG = LogFactory.getLog(ApplicationUserDao.class);

	public ApplicationUserDao() {
		super();
	}

	public ApplicationUser create(String companyId) {
		ApplicationUser user = new ApplicationUser();
		
		LOG.info("Set company key " + companyKey.toString());

		user.setCompany(new Key<Company>(Company.class, companyId));
		return user;
	}

	public ApplicationUser getByEMail(String email) {
		try {
			Query<ApplicationUser> query = ofy().query(ApplicationUser.class);
			//query.ancestor(companyKey);
			ApplicationUser user = query.filter("email", email).get();
			return user;
		} catch (Exception e) {
			LOG.error("Cannot get ApplicationUser by email(" + email + ")", e);
			return null;
		}
	}

	@Override
	public ApplicationUser update(ApplicationUser user) {
		String userId = user.getId();

		if (userId == null || userId.length() == 0) {
			UUID uuid = UUID.randomUUID();
			LOG.info("Creating Uuid " + uuid.toString());
			user.setId(uuid.toString());
		}else{
			LOG.info("Update Application user with Uuid " + userId);
		}
		return super.update(user);
	}
}
