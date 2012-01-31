package eu.comexis.napoleon.server.dao;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.Query;

import eu.comexis.napoleon.shared.model.ApplicationUser;

public class ApplicationUserDao extends NapoleonDao<ApplicationUser> {

	public static Log LOG = LogFactory.getLog(ApplicationUserDao.class);

	public ApplicationUserDao(String companyId) {
		super(companyId);
	}

	public ApplicationUser create() {
		ApplicationUser user = new ApplicationUser();
		
		LOG.info("Set company key " + companyKey.toString());

		user.setCompany(companyKey);
		return user;
	}

	public ApplicationUser getByEMail(String email) {
		try {
			Query<ApplicationUser> query = ofy().query(ApplicationUser.class);
			query.ancestor(companyKey);
			ApplicationUser user = query.filter("email", email).get();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ApplicationUser update(ApplicationUser user) {
		String ownerId = user.getId();

		if (ownerId == null || ownerId.length() == 0) {
			UUID uuid = UUID.randomUUID();
			LOG.info("Creating Uuid " + uuid.toString());
			user.setId(uuid.toString());
		}
		return super.update(user);
	}
}
