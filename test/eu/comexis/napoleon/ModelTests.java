/**
 * 
 */
package eu.comexis.napoleon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.MaritalStatus;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.Tenant;

/**
 * @author xavier
 * 
 */
public class ModelTests {

  private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
      new LocalDatastoreServiceTestConfig());
  private static Objectify ofy;
  private static SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    ObjectifyService.register(Owner.class);
    ObjectifyService.register(Tenant.class);
    ObjectifyService.register(RealEstate.class);
    ObjectifyService.register(Condo.class);
    ObjectifyService.register(Lease.class);
    ofy = ObjectifyService.begin();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  private final DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    helper.setUp();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    helper.tearDown();
  }

  @Test
  public void testCreateStoreAndUpdateOwner() {
    // create a new owner
    Owner oProprio = getProprio();
    // save it to the datastore
    ofy.put(oProprio);
    assertNotNull(oProprio.getId());
    oProprio = null;
    Owner oProprio2 = ofy.query(Owner.class).filter("lastName", "Legrand").get();
    assertEquals("Legrand", oProprio2.getLastName());
    assertEquals("Gouyasse", oProprio2.getFirstName());
    assertEquals("7800 Ath", oProprio2.getCity());
    assertEquals("Belgique", oProprio2.getCountry());
    assertEquals("legrand.gouyasse@ducasse.org", oProprio2.getEmail());
    assertEquals("Rue de la brasserie, 69", oProprio2.getStreet());
    assertEquals(new BigDecimal(10.25), oProprio2.getFee());
    assertEquals("%", oProprio2.getUnit());
    assertEquals("069-0696969-69", oProprio2.getBankAccountNumber());
    assertEquals("BE69 0690 6969 6969", oProprio2.getIBAN());
    assertEquals("GKCCBEBB", oProprio2.getBIC());
    assertEquals("Sun Jun 08 23:00:00 UTC 1969", oProprio2.getDateOfBirth().toString());

    oProprio2.setFirstName("Biloute");
    oProprio2.setFee(new BigDecimal(650.00));
    //oProprio2.setUnit("EUR");
    ofy.put(oProprio2);
    oProprio2 = null;
    Owner oProprio3 = ofy.query(Owner.class).filter("firstName", "Gouyasse").get();
    assertNull(oProprio3);
    oProprio3 = ofy.query(Owner.class).filter("firstName", "Biloute").get();
    assertNotNull(oProprio3);
    assertEquals(new BigDecimal(650.00), oProprio3.getFee());
    assertEquals("EUR", oProprio3.getUnit());
    assertEquals(MaritalStatus.MARRIED, oProprio3.getMaritalStatus());
  }

  @Test
  public void testCreateStoreAndUpdateTenant() {
    // create a new tenant
    Tenant oLoc = getTenant();
    // save it to the datastore
    ofy.put(oLoc);
    oLoc = null;
    Tenant oLoc2 = ofy.query(Tenant.class).filter("firstName", "Eliot").get();
    assertNotNull(oLoc2);
    oLoc2.setCity("Binche");
    ofy.put(oLoc2);
    oLoc2 = null;
    Tenant oLoc3 = ofy.query(Tenant.class).filter("city", "Binche").get();
    assertNotNull(oLoc3);
  }

  private Owner getProprio() {
    Owner oProprio = new Owner();
    oProprio.setLastName("Legrand");
    oProprio.setFirstName("Gouyasse");
    try {
      oProprio.setDateOfBirth(dfm.parse("1969-06-09"));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    oProprio.setCity("Ath");
    oProprio.setCountry("Belgique");
    oProprio.setEmail("legrand.gouyasse@ducasse.org");
    oProprio.setStreet("Rue de la brasserie, 69");
    oProprio.setFee(new BigDecimal(10.25));
    //oProprio.setUnit("%");
    oProprio.setBankAccountNumber("069-0696969-69");
    oProprio.setIBAN("BE69 0690 6969 6969");
    oProprio.setBIC("GKCCBEBB");
    oProprio.setFax("069 69 69 01");
    oProprio.setPhoneNumber("069 69 69 02");
    oProprio.setMobilePhoneNumber("0469 69 69 03");
    oProprio.setMaritalStatus(MaritalStatus.MARRIED);
    return oProprio;
  }

  private Tenant getTenant() {
    Tenant oLocataire = new Tenant();
    oLocataire.setLastName("Ledragon");
    oLocataire.setFirstName("Eliot");
    try {
      oLocataire.setDateOfBirth(dfm.parse("1969-06-09"));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    oLocataire.setCity("Mons");
    oLocataire.setCountry("Belgique");
    oLocataire.setEmail("eliot.ledragon@guindaille.org");
    oLocataire.setStreet("Rue de la malterie, 69");
    oLocataire.setBankAccountNumber("069-0696969-65");
    oLocataire.setIBAN("BE69 0690 6969 6965");
    oLocataire.setBIC("GKCCBEBB");
    oLocataire.setFax("069 69 69 03");
    oLocataire.setPhoneNumber("069 69 69 04");
    oLocataire.setMobilePhoneNumber("0469 69 69 05");
    return oLocataire;
  }
}
