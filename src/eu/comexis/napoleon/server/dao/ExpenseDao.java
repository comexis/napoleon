package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Expense;
import eu.comexis.napoleon.shared.model.RealEstate;

public class ExpenseDao extends DAOBase {
  public static Log LOG = LogFactory.getLog(ExpenseDao.class);
  public List<Expense> getAllExpense(Key<Company> companyKey) {
    // create the list to return
    List<Expense> leaseList = new ArrayList<Expense>();
    // get a query with all the estates
    Query<RealEstate> q = ofy().query(RealEstate.class);
    // set the parent to the given company
    q.ancestor(companyKey);
    // loop on each keys and get the corresponding leases
    for (Key<RealEstate> estateKey : q.listKeys()) {
      for (Expense exp : getExpenseForRE(estateKey)) {
        leaseList.add(exp);
      }
    }
    return leaseList;
  }
public List<Expense> getExpenseForRE(Key<RealEstate> estateKey) {
  // create the list to return
  List<Expense> expenseList = new ArrayList<Expense>();
  // get the lease for the company
  Query<Expense> ql = ofy().query(Expense.class);
  ql.ancestor(estateKey);
  return ql.list();
}
}
