package eu.comexis.napoleon.server.service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.comexis.napoleon.server.dao.OwnerDao;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class InitDatastore extends HttpServlet{

  public void service (HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      PrintWriter out = response.getWriter() ;
      out.println ("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">") ;
      out.println ("<title>Napoleon initialisation</title>") ;
      OwnerDao ownerData = new OwnerDao();
      ownerData.deleteAll(ownerData.listAll());
      Owner o = ownerData.create();
      o.setFirstName("Oufti");
      o.setLastName("Biloute");
      o.setPostalCode("7800");
      o.setCity("Ath");
      o.setStreet("Rue de la brasserie, 69");
      o = ownerData.update(o);
      // --------------------------------------------------------------------------
      o = ownerData.create();
      o.setFirstName("Machin");
      o.setLastName("Brol");
      o.setPostalCode("7000");
      o.setCity("Mons");
      o.setStreet("Rue de la bazar, 1");
      o = ownerData.update(o);
      // --------------------------------------------------------------------------
      o = ownerData.create();
      o.setFirstName("Cindy");
      o.setLastName("Troforte");
      o.setPostalCode("1000");
      o.setCity("Bruxelles");
      o.setStreet("Rue de la gaindaille, 5");
      o = ownerData.update(o);
      Iterator<Owner> iterator =  ownerData.listAll().iterator();
      while (iterator.hasNext()) {
        o = iterator.next();
        out.println ("<p>Owner (" + o.getId().toString()  + ") " + o.getLastName()  + " has been created</p>") ;
      }
    }
    catch (IOException e)
    {
      e.printStackTrace() ;
    }
  }
}
