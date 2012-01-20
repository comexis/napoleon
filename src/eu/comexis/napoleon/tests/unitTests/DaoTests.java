/**
 * 
 */
package eu.comexis.napoleon.tests.unitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eu.comexis.napoleon.server.service.NapoleonDao;
import eu.comexis.napoleon.shared.model.Owner;

/**
 * @author xavier
 *
 */
public class DaoTests {

	/**
	 * @throws java.lang.Exception
	 */
	@Test
	public void testOwner() {
		NapoleonDao model = new NapoleonDao();
		Owner oProprio = model.createOwner();
		oProprio.setLastName("Legrand");
		oProprio.setFirstName("Gouyasse");
		model.updateOwner(oProprio);
		oProprio = model.findOwnerByName("Legrand");
		assertNotNull(oProprio.getId());
	}

}
