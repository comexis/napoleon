package eu.comexis.napoleon.server.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Objectify;

import eu.comexis.napoleon.server.dao.ApplicationUserDao;
import eu.comexis.napoleon.server.dao.CompanyDao;
import eu.comexis.napoleon.server.dao.OwnerDao;
import eu.comexis.napoleon.server.dao.TenantDao;
import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.MaritalStatus;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.Title;

@SuppressWarnings("serial")
public class InitDatastore extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		deleteAll();

		String companyId = createCompany();

		createApplicationUsers(companyId);

		createOwners(companyId);

		createTenantDao(companyId);

		printResults(companyId, resp);


	}

	private void printResults(String companyId, HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");	
		out.println("<title>Napoleon initialisation</title>");
		out.println("</head>");
		out.println("<body>");

		//print company
		CompanyDao companyData = new CompanyDao();
		Company c = companyData.getById(companyId);
		out.println("<p>Company (" + c.getId() + ") " + c.getName()
				+ " has been created</p>");

		//print application user
		ApplicationUserDao appUserDao = new ApplicationUserDao();
		for  (ApplicationUser u : appUserDao.listAll()){
			out.println("<p>User (" + u.getId() + ") " + u.getEmail()
					+ " has been created</p>");
		}


		//print owner
		OwnerDao ownerData = new OwnerDao(companyId);
		for (Owner o : ownerData.listAll()){
			out.println("<p>Owner (" + o.getId() + ") " + o.getLastName()
					+ " has been created</p>");
		}

		//print tenant

		TenantDao tenantDao = new TenantDao(companyId);
		for (Tenant t : tenantDao.listAll()){
			out.println("<p>Tenant (" + t.getId() + ") " + t.getLastName()
					+ " has been created</p>");
		}


	}

	private void createOwners(String companyId) {
		OwnerDao ownerData = new OwnerDao(companyId);
		ownerData.deleteAll(ownerData.listAll());

		Owner o = ownerData.create();
		o.setFirstName("Oufti");
		o.setLastName("Biloute");
		o.setPostalCode("7800");
		o.setCity("Ath");
		o.setStreet("Rue de la brasserie, 69");
		o.setPhoneNumber("064/659874");
		o.setMobilePhoneNumber("0497/063970");
		o.setCountry("Belgiaue");
		o.setEmail("oufti.biloute@gmail.com");
		o.setDateOfBirth(new Date());
		o.setMaritalStatus(MaritalStatus.MARRIED);
		o = ownerData.update(o);

		// --------------------------------------------------------------------------
		o = ownerData.create();
		o.setFirstName("Machin");
		o.setLastName("Brol");
		o.setPostalCode("7000");
		o.setCity("Mons");
		o.setStreet("Rue de la bazar, 1");
		o.setPhoneNumber("065/896574");
		o.setMobilePhoneNumber("0497/895476");
		o.setCountry("Belgiaue");
		o.setEmail("machin.brol@gmail.com");
		o.setDateOfBirth(new Date());
		o.setMaritalStatus(MaritalStatus.SINGLE);
		o = ownerData.update(o);

		// --------------------------------------------------------------------------
		o = ownerData.create();
		o.setFirstName("Cindy");
		o.setLastName("Troforte");
		o.setPostalCode("1000");
		o.setCity("Bruxelles");
		o.setStreet("Rue de la gaindaille, 5");
		o.setPhoneNumber("02/6545874");
		o.setMobilePhoneNumber("0497/089654");
		o.setCountry("Belgiaue");
		o.setEmail("cindy.troforte@gmail.com");
		o.setDateOfBirth(new Date());
		o.setMaritalStatus(MaritalStatus.COHABITATION);
		o = ownerData.update(o);

	}

	private void createApplicationUsers(String companyId) {

		//create user xavier.platiaux@gmail.com
		ApplicationUserDao userData = new ApplicationUserDao();
		ApplicationUser u = userData.create(companyId);
		u.setFirstName("Xavier");
		u.setLastName("Platiaux");
		u.setEmail("xavier.platiaux@gmail.com");
		userData.update(u);

		//create user julien.dramaix@gmail.com
		u = userData.create(companyId);
		u.setFirstName("Julien");
		u.setLastName("Dramaix");
		u.setEmail("julien.dramaix@gmail.com");
		userData.update(u);

	}

      
	private void createTenantDao(String companyId) {
      TenantDao tenantData = new TenantDao(companyId);
      tenantData.deleteAll(tenantData.listAll());
      Tenant t = tenantData.create();
      t.setFirstName("Sophie");
      t.setLastName("Delamontagne");
      t.setTitle(Title.MISS);
      t.setCity("38000 Grenoble");
      t.setStreet("Rue de 3 pucelles, 69");
      t.setPhoneNumber("+33 4 69 69 69 69");
      t.setMobilePhoneNumber("+33 5 69 69 69 69");
      t.setCountry("France");
      t.setEmail("sophie.delamontagne@gmail.com");
      t.setDateOfBirth(new Date());
      t.setMaritalStatus(MaritalStatus.SINGLE);
      t = tenantData.update(t);
      
      
      t = tenantData.create();
      t.setFirstName("Martine");
      t.setLastName("Auclubmed");
      t.setTitle(Title.MISS);
      t.setCity("13008 Marseille");
      t.setStreet("Rue de la bouillabaise, 69");
      t.setPhoneNumber("+33 9 69 69 69 69");
      t.setMobilePhoneNumber("+33 7 69 69 69 69");
      t.setCountry("France");
      t.setEmail("martine.auclubmed@gmail.com");
      t.setDateOfBirth(new Date());
      t.setMaritalStatus(MaritalStatus.SINGLE);
      t = tenantData.update(t);
      
	}

	private String createCompany() {
		CompanyDao companyData = new CompanyDao();
		Company c = companyData.create();
		c.setName("Agence de l'aiglon");
		c.setAddress("Passage de l'Ergot, 44 - 1348 Louvain-la-Neuve");
		c.setEmail("aiglon@skynet.be");
		c.setUrl("http://www.aiglon.be");
		c.setTelephone("010/45.51.00");
		c.setFax(" 010/45.59.58");
		companyData.update(c);

		return c.getId();

	}



	private void deleteAll() {
		//first delete company
		//TODO add deleteAll and listAll in CompanyDao
		CompanyDao companyData = new CompanyDao();
		Objectify ofy = companyData.ofy();
		ofy.delete(ofy.query(Company.class).list());

		//delete application users
		ApplicationUserDao appUserDao = new ApplicationUserDao();
		appUserDao.deleteAll(appUserDao.listAll());

		//delete owners
		OwnerDao ownerData = new OwnerDao("dummy");
		ownerData.deleteAll(ownerData.listAll());

		//delete tenant
		TenantDao tenantData = new TenantDao("dummy");
	    tenantData.deleteAll(tenantData.listAll());

	}

}