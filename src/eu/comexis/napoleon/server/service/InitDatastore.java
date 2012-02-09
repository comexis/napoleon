package eu.comexis.napoleon.server.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.comexis.napoleon.server.dao.ApplicationUserDao;
import eu.comexis.napoleon.server.dao.CompanyDao;
import eu.comexis.napoleon.server.dao.CondoDao;
import eu.comexis.napoleon.server.dao.OwnerDao;
import eu.comexis.napoleon.server.dao.RealEstateDao;
import eu.comexis.napoleon.server.dao.TenantDao;
import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.MaritalStatus;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.RealEstateState;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.Title;
import eu.comexis.napoleon.shared.model.TypeOfRealEstate;

@SuppressWarnings("serial")
public class InitDatastore extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {

    deleteAll();

    String companyId = createCompany();

    createApplicationUsers(companyId);

    createOwners(companyId);

    createTenantDao(companyId);

    createRealEstate(companyId);

    printResults(companyId, resp);

  }

  private void createApplicationUsers(String companyId) {

    // create user xavier.platiaux@gmail.com
    ApplicationUserDao userData = new ApplicationUserDao();
    ApplicationUser u = userData.create(companyId);
    u.setFirstName("Xavier");
    u.setLastName("Platiaux");
    u.setEmail("xavier.platiaux@gmail.com");
    userData.update(u);

    // create user julien.dramaix@gmail.com
    u = userData.create(companyId);
    u.setFirstName("Julien");
    u.setLastName("Dramaix");
    u.setEmail("julien.dramaix@gmail.com");
    userData.update(u);

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

  private void createOwners(String companyId) {
    OwnerDao ownerData = new OwnerDao();
    ownerData.deleteAll(ownerData.listAll(companyId));

    Owner o = ownerData.create(companyId);
    o.setTitle(Title.MRS);
    o.setFirstName("Oufti");
    o.setLastName("Biloute");
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
    o = ownerData.create(companyId);
    o.setTitle(Title.MR);
    o.setFirstName("Machin");
    o.setLastName("Brol");
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
    o = ownerData.create(companyId);
    o.setTitle(Title.MISS);
    o.setFirstName("Cindy");
    o.setLastName("Troforte");
    o.setCity("1000 Bruxelles");
    o.setStreet("Rue de la gaindaille, 5");
    o.setPhoneNumber("02/6545874");
    o.setMobilePhoneNumber("0497/089654");
    o.setCountry("Belgique");
    o.setEmail("cindy.troforte@gmail.com");
    o.setDateOfBirth(new Date());
    o.setMaritalStatus(MaritalStatus.COHABITATION);
    o = ownerData.update(o);

  }

  private void createRealEstate(String companyId) {
    CondoDao cdoData = new CondoDao();
    Condo cdo = cdoData.create(companyId);
    cdo.setName("Résidence les Chopinnettes");
    cdo.setHomeownerAssociation("Lé valeureu d'joueu d'côte");
    cdo.setCountry("Belgique");
    cdo.setCity("7000 Mons");
    cdo.setStreet("Rue du get à pinte, 33");
    cdo.setPhoneNumber("065 256489");
    cdo.setMobilePhoneNumber("0477 258945");
    cdo.setEmail("chopinette@gmail.com");
    cdo = cdoData.update(cdo);
    System.out.println("Copropriété: " + cdo.getId());
    OwnerDao ownerData = new OwnerDao();
    Owner o = ownerData.create(companyId);
    o.setTitle(Title.MRS);
    o.setFirstName("Jean");
    o.setLastName("de Florette");
    o.setCity("7800 Ath");
    o.setStreet("Rue de la gouyasse, 1");
    o.setPhoneNumber("064/659874");
    o.setMobilePhoneNumber("0497/063970");
    o.setCountry("Belgique");
    o.setEmail("jean.deflorette@gmail.com");
    o.setDateOfBirth(new Date());
    o.setMaritalStatus(MaritalStatus.MARRIED);
    o = ownerData.update(o);
    RealEstateDao estateData = new RealEstateDao();
    RealEstate e = estateData.create(companyId);
    e.setReference("Résidence les Chopinnettes - appartement 1");
    e.setStreet("Rue des branleurs, 68");
    e.setCity("1348 Louvain-la-Neuve");
    e.setCountry("Belgique");
    e.setNumber("101 bis");
    e.setSquare("Le guet à pintes");
    e.setState(RealEstateState.EXCELLENT);
    e.setType(TypeOfRealEstate.A2);
    estateData.setCondo(e,cdo);
    estateData.setOwner(e,o);
    e = estateData.update(e);
  }

  private void createTenantDao(String companyId) {
    TenantDao tenantData = new TenantDao();
    tenantData.deleteAll(tenantData.listAll(companyId));
    Tenant t = tenantData.create(companyId);
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

    t = tenantData.create(companyId);
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

  private void deleteAll() {
    CompanyDao companyData = new CompanyDao();
    for (Company c : companyData.listAll()) {
      // delete application users
      ApplicationUserDao appUserDao = new ApplicationUserDao();
      appUserDao.deleteAll(appUserDao.listAll(c.getId()));

      // delete owners
      OwnerDao ownerData = new OwnerDao();
      ownerData.deleteAll(ownerData.listAll(c.getId()));

      // delete tenant
      TenantDao tenantData = new TenantDao();
      tenantData.deleteAll(tenantData.listAll(c.getId()));

      companyData.delete(c);
    }
    // At the end delete company
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

    // print company
    CompanyDao companyData = new CompanyDao();
    Company c = companyData.getById(companyId);
    out.println("<p>Company (" + c.getId() + ") " + c.getName() + " has been created</p>");

    // print application user
    ApplicationUserDao appUserDao = new ApplicationUserDao();
    for (ApplicationUser u : appUserDao.listAll(companyId)) {
      out.println("<p>User (" + u.getId() + ") " + u.getEmail() + " has been created</p>");
    }

    // print owner
    OwnerDao ownerData = new OwnerDao();
    for (Owner o : ownerData.listAll(companyId)) {
      out.println("<p>Owner (" + o.getId() + ") " + o.getLastName() + " has been created</p>");
    }

    // print tenant

    TenantDao tenantDao = new TenantDao();
    for (Tenant t : tenantDao.listAll(companyId)) {
      out.println("<p>Tenant (" + t.getId() + ") " + t.getLastName() + " has been created</p>");
    }

    // print realEstate

    RealEstateDao estateDao = new RealEstateDao();
    for (RealEstate t : estateDao.listAll(companyId)) {
      out.println("<p>Real Estate (" + t.getId() + ") " + t.getReference()
          + " has been created</p>");
    }
  }

}