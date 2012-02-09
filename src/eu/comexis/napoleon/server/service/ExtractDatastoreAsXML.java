package eu.comexis.napoleon.server.service;

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
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.Tenant;

public class ExtractDatastoreAsXML extends HttpServlet {

  public ExtractDatastoreAsXML() {
    // TODO Auto-generated constructor stub
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    resp.setContentType("text/xml");
    String companyId = req.getParameter("uuid");
    printResults(companyId, resp);
  }
  private void printResults(String companyId, HttpServletResponse response) throws IOException {

    PrintWriter out = response.getWriter();
    
    // print company
    CompanyDao companyData = new CompanyDao();
    Company c = companyData.getById(companyId);
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
      out.println("</Tenant>");
    }
    out.println("</Tenants>");
    // print realEstate
    out.println("<RealEstates>");
    RealEstateDao estateDao = new RealEstateDao();
    for (RealEstate e : estateDao.listAll(companyId)) {
      out.println("<RealEstate id='"+ e.getId() + "'>");
      out.println("<Name>"+ e.getReference() + "</Name>");
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
