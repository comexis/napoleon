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
import eu.comexis.napoleon.server.dao.CountryDao;
import eu.comexis.napoleon.server.dao.OwnerDao;
import eu.comexis.napoleon.server.dao.RealEstateDao;
import eu.comexis.napoleon.server.dao.TenantDao;
import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.MaritalStatus;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.RealEstateState;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.Title;
import eu.comexis.napoleon.shared.model.TypeOfRealEstate;

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
	    
	    CountryDao countryData = new CountryDao(c.getId());
	    //Cleanup
	    countryData.deleteAll(countryData.listAll());
	    // Add new countries
	    Country cnty = countryData.create();
	    cnty.setName("Belgique");
	    countryData.update(cnty);
	    countryData.addCity(cnty.getId(), "7130 Binche");
	    countryData.addCity(cnty.getId(), "7000 Mons");
	    countryData.addCity(cnty.getId(), "7800 Ath");
	    countryData.addCity(cnty.getId(), "1000 Bruxelles");
	    
	    cnty = countryData.create();
	    cnty.setName("France");
	    countryData.update(cnty);
	    countryData.addCity(cnty.getId(), "62410 Benifontaine");
      
      cnty = countryData.create();
      cnty.setName("Espagne");
      countryData.update(cnty);
      cnty = countryData.create();
      cnty.setName("Suisse");
      countryData.update(cnty);
      cnty = countryData.create();
      cnty.setName("Italie");
      countryData.update(cnty);
      
      Iterator<Country> iter1 = countryData.listAll().iterator();
      while (iter1.hasNext()) {
        cnty = iter1.next();
        out.println("<p>Country (" + cnty.getId() + ") "
            + cnty.getName() + " has been created</p>");
      }
      
	    ApplicationUserDao userData = new ApplicationUserDao(c.getId());
	    //Cleanup
	    userData.deleteAll(userData.listAll());
	    // Add users
	    ApplicationUser u = userData.create();
	    u.setFirstName("Xavier");
	    u.setLastName("Platiaux");
	    u.setEmail("xavier.platiaux@gmail.com");
	    userData.update(u);
	    u = userData.create();
      u.setFirstName("Julien");
      u.setLastName("Dramaix");
      u.setEmail("julien.dramaix@gmail.com");
      userData.update(u);
	    Iterator<ApplicationUser> iterator = userData.listAll().iterator();
      while (iterator.hasNext()) {
        u = iterator.next();
        out.println("<p>User (" + u.getId() + ") "
            + u.getEmail() + " has been created</p>");
      }
      
      
      RealEstateDao estateData = new RealEstateDao(c.getId());
      RealEstate e = new RealEstate();
      e.setReference("REF001");
      e.setStreet("Rue des branleurs, 68");
      e.setCity("7800 Ath");
      e.setCountry("Belgique");
      e.setNumber(new Long(101));
      e.setSquare("Le guet à pintes");
      e.setState(RealEstateState.EXCELLENT);
      e.setType(TypeOfRealEstate.A2);
      e = estateData.update(e);
      out.println("<p>RealEstate (" + e.getId() + "</p>");
      
      Iterator<RealEstate> iter = estateData.listAll().iterator();
      while (iter.hasNext()) {
        e = iter.next();
        out.println("<p>RealEstate (" + e.getId() + ") "
            + e.getReference() + " has been created</p>");
      }
      
      TenantDao tenantData = new TenantDao(c.getId());
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
      
			OwnerDao ownerData = new OwnerDao(c.getId());
			ownerData.deleteAll(ownerData.listAll());
			Owner o = ownerData.create();
			o.setFirstName("Oufti");
			o.setLastName("Biloute");
			o.setTitle(Title.MR);
			o.setCity("7800 Ath");
			o.setStreet("Rue de la brasserie, 69");
			o.setPhoneNumber("064/659874");
			o.setMobilePhoneNumber("0497/063970");
			o.setCountry("Belgique");
			o.setEmail("oufti.biloute@gmail.com");
			o.setDateOfBirth(new Date());
			o.setMaritalStatus(MaritalStatus.MARRIED);

			o = ownerData.update(o);
			// --------------------------------------------------------------------------
			o = ownerData.create();
			o.setFirstName("Machin");
			o.setLastName("Brol");
			o.setTitle(Title.MR);
			o.setCity("7000 Mons");
			o.setStreet("Rue de la bazar, 1");
			o.setPhoneNumber("065/896574");
			o.setMobilePhoneNumber("0497/895476");
			o.setCountry("Belgique");
			o.setEmail("machin.brol@gmail.com");
			o.setDateOfBirth(new Date());
			o.setMaritalStatus(MaritalStatus.SINGLE);

			o = ownerData.update(o);
			// --------------------------------------------------------------------------
			o = ownerData.create();
			o.setFirstName("Cindy");
			o.setLastName("Troforte");
			o.setTitle(Title.MRS);
			o.setCity("1000 Bruxelles");
			o.setStreet("Rue de la gaindaille, 5");
			o.setPhoneNumber("02/6545874");
			o.setMobilePhoneNumber("0497/089654");
			o.setCountry("Belgique");
			o.setEmail("cindy.troforte@gmail.com");
			o.setDateOfBirth(new Date());
			o.setMaritalStatus(MaritalStatus.COHABITATION);
			o = ownerData.update(o);

			Iterator<Owner> iter3 = ownerData.listAll().iterator();
			while (iter3.hasNext()) {
				o = iter3.next();
				out.println("<p>Owner (" + o.getId() + ") "
						+ o.getLastName() + " has been created</p>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
