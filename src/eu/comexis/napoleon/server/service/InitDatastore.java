package eu.comexis.napoleon.server.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.comexis.napoleon.server.dao.ApplicationUserDao;
import eu.comexis.napoleon.server.dao.CompanyDao;
import eu.comexis.napoleon.server.dao.OwnerDao;
import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.MaritalStatus;
import eu.comexis.napoleon.shared.model.Owner;

public class InitDatastore extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
		
		  
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">");
			out.println("<title>Napoleon initialisation</title>");
			// create the company
			CompanyDao companyData = new CompanyDao();
			Company c = companyData.create();
			c.setName("Agence de l'aiglon");
	    c.setAddress("Passage de l'Ergot, 44 - 1348 Louvain-la-Neuve");
	    c.setEmail("aiglon@skynet.be");
	    c.setUrl("http://www.aiglon.be");
	    c.setTelephone("010/45.51.00");
	    c.setFax(" 010/45.59.58");
	    companyData.update(c);
	    out.println("<p>Company (" + c.getId() + ") "
          + c.getName() + " has been created</p>");
	    
	    ApplicationUserDao userData = new ApplicationUserDao(c.getId());
	    ApplicationUser u = userData.create();
	    u.setFirstName("Xavier");
	    u.setLastName("Platiaux");
	    u.setEmail("xavier.platiaux@gmail.com");
	    userData.update(u);
	    Iterator<ApplicationUser> iterator = userData.listAll().iterator();
      while (iterator.hasNext()) {
        u = iterator.next();
        out.println("<p>User (" + u.getId() + ") "
            + u.getEmail() + " has been created</p>");
      }
			OwnerDao ownerData = new OwnerDao(c.getId());
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

			Iterator<Owner> iter = ownerData.listAll().iterator();
			while (iter.hasNext()) {
				o = iter.next();
				out.println("<p>Owner (" + o.getId() + ") "
						+ o.getLastName() + " has been created</p>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
