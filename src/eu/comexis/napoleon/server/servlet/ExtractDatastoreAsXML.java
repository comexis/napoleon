package eu.comexis.napoleon.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
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
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.FileDescriptor;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class ExtractDatastoreAsXML extends HttpServlet {

  public ExtractDatastoreAsXML() {
    // TODO Auto-generated constructor stub
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    resp.setContentType("text/xml");
    CompanyDao companyData = new CompanyDao();
    PrintWriter out = resp.getWriter();
    out.println("<Datastore>");
    for (Company c : companyData.listAll()){
      printResults(c, resp);
    }
    out.println("</Datastore>");
    
  }
  private void printResults(Company c, HttpServletResponse response) throws IOException {

    PrintWriter out = response.getWriter();
    
    // print company
    String companyId = c.getId();
    out.println("<Napoleon client=\"" + c.getName() + "\">");
    out.println("<Company id='"+ c.getId() + "'>");
    out.println("<Name>"+ c.getName() + "</Name>");
    out.println("<Address>"+ c.getAddress() + "</Address>");
    out.println("<Email>"+ c.getEmail() + "</Email>");
        out.println("<Telephone>"+ c.getTelephone() + "</Telephone>");
        out.println("<Fax>"+ c.getFax() + "</Fax>");
    out.println("<URL>"+ c.getUrl() + "</URL>");
    out.println("</Company>");

    // print application user
    ApplicationUserDao appUserDao = new ApplicationUserDao();
    out.println("<Users>");
    for (ApplicationUser u : appUserDao.listAll(companyId)) {
      out.println("<User id='"+ u.getId() + "'>");
      out.println("<Name>"+ u.getLastName() + "</Name>");
      out.println("<FirstName>"+ u.getFirstName() + "</FirstName>");
      out.println("<Email>"+ u.getEmail() + "</Email>");
      out.println("</User>");
    }
    out.println("</Users>");
    // print owner
    out.println("<Owners>");
    OwnerDao ownerData = new OwnerDao();
    for (Owner o : ownerData.listAll(companyId)) {
      out.println("<Owner id='"+ o.getId() + "'>");
      out.println("<Name>"+ o.getLastName() + "</Name>");
      out.println("<FirstName>"+ o.getFirstName() + "</FirstName>");
      out.println("<Telephone>"+ o.getPhoneNumber() + "</Telephone>");
      out.println("<Fax>"+ o.getFax() + "</Fax>");
      out.println("<Email>"+ o.getEmail() + "</Email>");
      out.println("<Street>" + o.getStreet() + "</Street>");
      out.println("<Country>"+ o.getCountry() + "</Country>");
      out.println("<PostalCode>"+ o.getPostalCode() + "</PostalCode>");
      out.println("<City>"+ o.getCity() + "</City>");
      if (o.getFiles() != null && !o.getFiles().isEmpty()){
        out.println("<FileDescriptors>");
        for (FileDescriptor f : o.getFiles()){
          out.println("<FileDescriptor>");
          out.println("<Id>"+f.getId()+"</Id>");
          out.println("<Description>"+f.getDescription()+"</Description>");
          out.println("<FileName>"+f.getFileName()+"</FileName>");
          out.println("</FileDescriptor>");
        }
        out.println("</FileDescriptors>");
      }
      out.println("</Owner>");
    }
    out.println("</Owners>");
    // print tenant
    out.println("<Tenants>");
    TenantDao tenantDao = new TenantDao();
    for (Tenant t : tenantDao.listAll(companyId)) {
      out.println("<Tenant id='"+ t.getId() + "'>");
      out.println("<Name>"+ t.getLastName() + "</Name>");
      out.println("<FirstName>"+ t.getFirstName() + "</FirstName>");
      out.println("<Telephone>"+ t.getPhoneNumber() + "</Telephone>");
      out.println("<Fax>"+ t.getFax() + "</Fax>");
      out.println("<Email>"+ t.getEmail() + "</Email>");
      out.println("<Street>" + t.getStreet() + "</Street>");
      out.println("<Country>"+ t.getCountry() + "</Country>");
      out.println("<PostalCode>"+ t.getPostalCode() + "</PostalCode>");
      out.println("<City>"+ t.getCity() + "</City>");
      out.println("</Tenant>");
    }
    out.println("</Tenants>");
    // print realEstate
    out.println("<RealEstates>");
    RealEstateDao estateDao = new RealEstateDao();
    for (RealEstate e : estateDao.listAll(companyId)) {
      out.println("<RealEstate id='"+ e.getId() + "'>");
      out.println("<Name>"+ e.getReference() + "</Name>");
      out.println("<Street>" + e.getStreet() + "</Street>");
      out.println("<Country>"+ e.getCountry() + "</Country>");
      out.println("<PostalCode>"+ e.getPostalCode() + "</PostalCode>");
      out.println("<City>"+ e.getCity() + "</City>");
      out.println("<Square>" + e.getSquare() + "</Square>");
      out.println("<State>" + e.getState().name() + "</State>");
      out.println("<Type>" + e.getType().name() + "</Type>");
      out.println("<Dim>" + e.getDimension() + "</Dim>");
      SimpleOwner o = estateDao.getOwner(e);
      if (o !=null){
        out.println("<Owner>" + o.getName() + "</Owner>");
      }
      if (!e.getCondominium().isEmpty()){
        out.println("<Condominium>");
        out.println("<Name>" + e.getCondominium() + "</Name>");
        out.println("<HomeownerAssociation>" + e.getHomeownerAssociation() + "</HomeownerAssociation>");
        out.println("<Address>" + e.getAssocAdresss() + "</Address>");
        out.println("</Condominium>");
      }
      out.println("</RealEstate>");
    }
    out.println("</RealEstates>");
    
    // Countries
    out.println("<Countries>");
    CountryDao countryDao = new CountryDao();
    for (Country cnty : countryDao.listAll(companyId)) {
      out.println("<Country id='"+ cnty.getId() + "'>");
      out.println("<Name>"+ cnty.getName() + "</Name>");
      for (City city : countryDao.getListCities(cnty.getId())) {
        out.println("<City id='"+ city.getId() + "'>");
        out.println("<PostalCode>"+ city.getPostalCode() + "</PostalCode>");
        out.println("<Name>"+ city.getName() + "</Name>");
        List<String> lstSquares = city.getSquareList();
        if (lstSquares!=null){
          for(String square:lstSquares){
            out.println("<Square>"+ square + "</Square>");
          }
        }
        out.println("</City>");
      }
      out.println("</Country>");
    }
    out.println("</Countries>");
    out.println("</Napoleon>");
  }
}
