package eu.comexis.napoleon.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.comexis.napoleon.server.dao.ApplicationUserDao;
import eu.comexis.napoleon.server.dao.CompanyDao;
import eu.comexis.napoleon.server.dao.LeaseDao;
import eu.comexis.napoleon.server.dao.OwnerDao;
import eu.comexis.napoleon.server.dao.RealEstateDao;
import eu.comexis.napoleon.server.dao.TenantDao;
import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.MaritalStatus;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.Title;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

@SuppressWarnings("serial")
public class InitDatastore extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {

    deleteAll();

    String companyId = createCompany();

    createApplicationUsers(companyId);
    
    //createApplicationUsersLive(companyId);

    createOwners(companyId);

    createTenantDao(companyId);

    //create300RealEstate(companyId);
    
    createRealEstate(companyId);
    
    createLease(companyId);

    printResults(companyId, resp);

  }
  
  private void createApplicationUsersLive(String companyId) {

    // create user xavier.platiaux@gmail.com
    ApplicationUserDao userData = new ApplicationUserDao();
    ApplicationUser u = userData.create(companyId);
    u.setFirstName("Aiglon");
    u.setLastName("sprl");
    u.setEmail("aiglonsprl@gmail.com");
    userData.update(u);
    
    // create user napoleon.user1@gmail.com
    u = userData.create(companyId);
    u.setFirstName("comexis");
    u.setLastName("user");
    u.setEmail("comexis.partners@gmail.com");
    userData.update(u); 
    

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
    
    // create user napoleon.user1@gmail.com
    u = userData.create(companyId);
    u.setFirstName("user1");
    u.setLastName("napoleon");
    u.setEmail("napoleon.user1@gmail.com");
    userData.update(u); 

    // create user napoleon.user1@gmail.com
    u = userData.create(companyId);
    u.setFirstName("Benoit");
    u.setLastName("Hosselet");
    u.setEmail("benoit.hosselet@gmail.com");
    userData.update(u); 

    
 // create user napoleon.user1@gmail.com
    u = userData.create(companyId);
    u.setFirstName("Benoît");
    u.setLastName("Tytgat");
    u.setEmail("scatyb@gmail.com");
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
    
    c.setLogo("data:image/jpeg;base64,/9j/4AAQSkZJRgABAgAAZABkAAD/7AARRHVja3kAAQAEAAAAZAAA/+4ADkFkb2JlAGTAAAAAAf/bAIQAAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQICAgICAgICAgICAwMDAwMDAwMDAwEBAQEBAQECAQECAgIBAgIDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMD/8AAEQgASwDXAwERAAIRAQMRAf/EALUAAQACAgIDAQEAAAAAAAAAAAAICQcKBQsBBAYDAgEBAAEEAwEAAAAAAAAAAAAAAAYEBQcIAQIDCRAAAAYCAgIBAwMCAwUJAAAAAQIDBAUGBwgAERIJEyEUCjEiFUEWYSMY8FFxQmKBscEyUpIlJhcRAAIBAwMCBQEEBwYEAwkBAAECAxEEBQAhBjESQVEiEwdhcYEyFKGxQlIjFQiR0WIzJBbwwXKCU2MX8ZKiQ3ODNGSlGP/aAAwDAQACEQMRAD8A3+ONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNONNR42T2t1/1FoS2RtgslQGPq+JlkIpu/XFzYbPIpImW/iKnWmZV5qxyhiB2KTVFT4y/vUEhAEwSTjHEeRcxyAxnHLWW5uerdooiDp3SOaKi/ViKnYVO2rFyDkuD4vYnIZ24SC36Cu7Of3UQVZ2+ig+Z21rVZ8/KAZtZR5F6ya4Fl4xE6ybW45pn3Ed978ZjFTcpUeoHM5TarAHkUFZlJXr6GIURHx2o45/SVcPGsnLcn7UxpWK1QPSo3BmcgBgfARsKb93hrXnOf1IwpK8PG8eZVUkCSd+0H6hE6j6GRW811Ewv5Ou7PygJsKasij5dimWs5aBXw7/8AKCw5gMXz6/r4df4cmh/pO4EAQb7LhqbfxLc/oFr0+ldRM/1G8zVgTaYvsruOyav9v5n/AJamXr5+T1WpSSZxOz2vTqqs11UUnF0w9Nqz7NmBugVcuqZZxaSRGyX1EQQk3SvX0Agj+sE5L/SbexQmfiWSWWQAn2bpRGxp0CyxkoxP+JI6amOC/qPtpZBDyPHvEm38WBu9T5/w2o23krOdbKWv2ymDtpaEzyXgXI1eyLU3IkScuYZyISMK+OT5DRNjhHREJevSyRe/Ju7RRUEP3FAxBAw6s8j4vn+I5FsVyK1ltb1egYbMP3kYVV1Pgykj79bC4LkOF5LYjJYO4juLQ7VU7qf3WXqrDxDAHWcuWDV50400400400400400400400400400400400400400400400400400400400401CH2Bbx460C15ns13dEs9POHJKzjHH6L0rKSyDfX7ZwvGwiDgU1zMYtk2bKvJJ58ZwaMUFDAU6gpJqTv464FlPkXkkeCx59uADvnmIqsMIIDORUVNSFRajuYgVAqRD+ccyx/B8C+YvfXLXsijrQySEHtWvgNqs3goPU0B647ZnaHNm3uVpzMmd7i7tdsmF3AMWJDuUKvTYdRYVW1Wo8Es5dI16tsCAUhESGOssJflcKLLmOqb6f8T4hguDYVMDx+FYrJKFm6yzSUoZZWoO5jvTwWtFAG2vn9yXkuX5flJMpnJTNO/wCFRUJEtdkiXegHn1PViTU6+/wFoNtrs9j2z5awric1kxpTp4tbsN3nLZUaLW2csVqD1+RKXuczCsXbWEbKJC/XTUFNoZwkU4+RugtvJvkrhfD8umDzt0IctNF3oixPMxBPaAVjVjV9wq9WofDVbhOC8q5JjpMnhrb3LKJijOzIiqaVahdlGwpU9BUanRade9XNXaxQcfbC6m2i9Z2R0ru20t/mTbMzsDEub1FXGxQsBj5OLxuSZqw0RWFgyLKPGL0zwwLfQ/n5BzGMHKuZcuyN3k+K5iC342M3DYQo1krMIWhRmlUylH91TUdrrsPOoGsiycZ4px63tsfncdNLmWxr3Mri5K+r3XUKojLJSgFe1qmvUEahftbqTkrFM7P5QqmBMuVTV+wRtEu1AvUxD2GyUiMreSKhXLNGxJ8lqsQZySEZKTasai5eGSXWMgUFA+QfrkDg/OcXm7CHF5DIWMvKo2lgmi70SZ5oJZFaT2a1HeFD7AgDodQTlPEr/F3MmSsrG5i448cUkMna7RrHKoYIZKUJDMdya+eviNSNwM36VZaicuYRtDiIkW6rVCy1t0qurU75X01gVcVq3RBFSIyEe4IY3xKfRwzVN8qB01AA3LzzPhGA53h3w/IIBJCa9kg2khc7e5G37y+Kn0sNmBGrbxXleb4fk1yeEl9uX9pG/wAuVAd0dfI9FI3B3BB12OukG42NN5tf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");
    companyData.update(c);

    return c.getId();

  }

  private void createOwners(String companyId) {
    OwnerDao ownerData = new OwnerDao();
    ownerData.deleteAll(ownerData.listAll(companyId));

    Owner o = ownerData.create(companyId);
    o.setTitle(Title.MRS);
    o.setLastName("Propriétaire 1");
    o.setFirstName("Prénom 1");
    o.setCity("Ath");
    o.setPostalCode("7800");
    o.setStreet("Rue de la brasserie");
    o.setNumber("12");
    o.setPhoneNumber("064/659874");
    o.setMobilePhoneNumber("0497/063970");
    o.setCountry("Belgique");
    o.setEmail("prop1@gmail.com");
    o.setDateOfBirth(new Date());
    o.setMaritalStatus(MaritalStatus.MARRIED);
    o = ownerData.update(o);

    // --------------------------------------------------------------------------
    o = ownerData.create(companyId);
    o.setTitle(Title.MR);
    o.setLastName("Propriétaire 2");
    o.setFirstName("Prénom 2");
    o.setPostalCode("7000");
    o.setCity("Mons");
    o.setStreet("Rue de la Poste");
    o.setNumber("25");
    o.setPhoneNumber("065/896574");
    o.setMobilePhoneNumber("0497/895476");
    o.setCountry("Belgique");
    o.setEmail("prop2@gmail.com");
    o.setDateOfBirth(new Date());
    o.setMaritalStatus(MaritalStatus.SINGLE);
    o = ownerData.update(o);

    // --------------------------------------------------------------------------
    o = ownerData.create(companyId);
    o.setTitle(Title.MISS);
    o.setLastName("Propriétaire 3");
    o.setFirstName("Prénom 3");
    o.setCity("Bruxelles");
    o.setPostalCode("1000");
    o.setStreet("Rue de la Gare");
    o.setNumber("101");
    o.setPhoneNumber("02/6545874");
    o.setMobilePhoneNumber("0497/089654");
    o.setCountry("Belgique");
    o.setEmail("prop3@gmail.com");
    o.setDateOfBirth(new Date());
    o.setMaritalStatus(MaritalStatus.COHABITATION);
    o = ownerData.update(o);

  }
  private void createRealEstate(String companyId) {
    RealEstateDao eDao = new RealEstateDao();
    OwnerDao ownerData = new OwnerDao();
    List<Owner> allOwners = ownerData.listAll(companyId);
    RealEstate e = eDao.create(companyId);
    e.setReference("Residence du Granite Rose /1");
    e.setCondominium("Residence du Granite Rose");
    e.setHomeownerAssociation("Syndic de la Mer");
    e.setStreet("Rue de l'océan");
    e.setNumber("12");
    e.setPostalCode("7800");
    e.setCity("Ath");
    e.setCountry("Belgique");
    e.setOwnerKey(ownerData.getOwnerKey(allOwners.get(0).getId(),companyId));
    eDao.update(e);
    
    e = eDao.create(companyId);
    e.setReference("Residence du Granite Rose /2");
    e.setCondominium("Residence du Granite Rose");
    e.setHomeownerAssociation("Syndic de la Mer");
    e.setStreet("Rue de l'océan");
    e.setNumber("12");
    e.setPostalCode("7800");
    e.setCity("Ath");
    e.setCountry("Belgique");
    e.setOwnerKey(ownerData.getOwnerKey(allOwners.get(1).getId(),companyId));
    eDao.update(e);
    
    e = eDao.create(companyId);
    e.setReference("Residence les Alouettes");
    e.setStreet("Rue des Marins");
    e.setNumber("25");
    e.setPostalCode("7000");
    e.setCity("Mons");
    e.setCountry("Belgique");
    e.setOwnerKey(ownerData.getOwnerKey(allOwners.get(1).getId(),companyId));
    eDao.update(e);
  }
  private void create300RealEstate(String companyId) {
    RealEstateDao eDao = new RealEstateDao();
    OwnerDao ownerData = new OwnerDao();
    List<Owner> allOwners = ownerData.listAll(companyId);
    RealEstate e = null;
    int j=0;
    for(int i=0; i< 300 ; i++){
      e = eDao.create(companyId);
      e.setReference("Residence " + i);
      e.setStreet("Rue " + i);
      e.setNumber("" + i);
      e.setPostalCode("" + (7000 + i));
      e.setCity("Ville " + i);
      e.setCountry("Belgique");
      e.setOwnerKey(ownerData.getOwnerKey(allOwners.get(j).getId(),companyId));
      eDao.update(e);
      j++;
      if (j > 2){
        j=0;
      }
    }
  }
  
  private void createLease(String companyId){
    Calendar cal1 = Calendar.getInstance(
        TimeZone.getTimeZone("CEST"), Locale.FRANCE);
    
    LeaseDao lDao = new LeaseDao();
    Lease l = new Lease();
    RealEstateDao eDao = new RealEstateDao();
    TenantDao tDao = new TenantDao();
    List<SimpleRealEstate> allEstate = eDao.getListSimpleRealEstates(companyId);
    List<SimpleTenant> allTenant = tDao.getListSimpleTenants(companyId);
    l.setRealEstate(allEstate.get(0));
    l.setTenant(allTenant.get(0));
    cal1.set(2011, Calendar.SEPTEMBER, 1, 00, 00);
    l.setStartDate(cal1.getTime());
    cal1.set(2012, Calendar.JUNE, 30, 00, 00);
    l.setEndDate(cal1.getTime());
    lDao.update(l,companyId);
  }

  private void createTenantDao(String companyId) {
    TenantDao tenantData = new TenantDao();
    tenantData.deleteAll(tenantData.listAll(companyId));
    Tenant t = tenantData.create(companyId);
    t.setFirstName("Sophie");
    t.setLastName("Delamontagne");
    t.setTitle(Title.MISS);
    t.setCity("Grenoble");
    t.setPostalCode("38000");
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
    t.setCity("Marseille");
    t.setPostalCode("13008");
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