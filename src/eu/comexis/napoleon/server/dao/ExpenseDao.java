package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Contractor;
import eu.comexis.napoleon.shared.model.Expense;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.TypeOfWork;

public class ExpenseDao extends DAOBase {
  public ExpenseDao(){
    super();
  }
  public ExpenseDao(boolean transactional){
    super(transactional);
  }
  public static Log LOG = LogFactory.getLog(ExpenseDao.class);
  public Expense create(String companyId, String realEstateId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    Key<RealEstate> estateKey = new Key<RealEstate>(companyKey,RealEstate.class, realEstateId);
    Expense expense = new Expense();
    expense.setRealEstateKey(estateKey);
    expense.setRealEstateId(realEstateId);
    return expense;
  }
  public List<Expense> getAllExpense(String companyId) {
    // create the list to return
    List<Expense> leaseList = new ArrayList<Expense>();
    // get a query with all the estates
    Query<RealEstate> q = ofy().query(RealEstate.class);
    // set the parent to the given company
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    q.ancestor(companyKey);
    // loop on each keys and get the corresponding leases
    for (RealEstate estate : q.list()) {
      for (Expense exp : getAllExpense(estate.getId(),companyId)) {
        leaseList.add(exp);
      }
    }
    return leaseList;
  }
public List<Expense> getAllExpense(String realEstateId,String companyId) {
  Key<Company> companyKey = new Key<Company>(Company.class, companyId);
  Key<RealEstate> estateKey = new Key<RealEstate>(companyKey,RealEstate.class, realEstateId);
  // get the lease for the company
  Query<Expense> ql = ofy().query(Expense.class);
  ql.ancestor(estateKey);
  List<Expense> lstExp = new ArrayList<Expense>();
  for (Expense e: ql.list()){
    e.setRealEstateId(realEstateId);
    lstExp.add(e);
  }
  return lstExp;
}

public Expense getById(String expenseId, String realEstateId, String companyId) {
  LOG.info("Get expense: id (" + expenseId + "), estate id (" + realEstateId + "), company (" + companyId + ")");
  Key<Company> companyKey = new Key<Company>(Company.class, companyId);
  Key<RealEstate> estateKey = new Key<RealEstate>(companyKey,RealEstate.class, realEstateId);
  Expense exp = ofy().find(new Key<Expense>(estateKey, Expense.class, expenseId));
  exp.setRealEstateId(realEstateId);
  if (exp.getContractorKey()!=null){
    Contractor contractor = ofy().find(exp.getContractorKey());
    exp.setContractor(contractor);
  }
  return exp;
}
public Expense update(Expense expense,String companyId) {
  LOG.info("Update expense");
  String expenseId = expense.getId();
  Key<Company> companyKey = new Key<Company>(Company.class, companyId);
  Key<RealEstate> estateKey = null;
  // create unique id if new entity
  
  if (expenseId == null || expenseId.length() == 0) {
    UUID uuid = UUID.randomUUID();
    System.out.println("Creating Uuid " + uuid.toString());
    expense.setId(uuid.toString());
  }
  //set parent
  if (expense.getRealEstateKey() == null) {
    if (expense.getRealEstateId() != null) {
      estateKey =
          new Key<RealEstate>(companyKey,RealEstate.class, expense.getRealEstateId());
      expense.setRealEstateKey(estateKey);
      
    } else {
      LOG.fatal("Expense cannot be updated, missing parent RealEstate");
      return null;
    }
  }else{
    estateKey =expense.getRealEstateKey();
  }
  if (expense.getTypeOfWork() != null && !expense.getTypeOfWork().isEmpty()) {
    TypeOfWorkDao wrkDao = new TypeOfWorkDao();
    TypeOfWork work = new TypeOfWork();
    work.setName(expense.getTypeOfWork());
    work.setCompany(companyKey);
    wrkDao.update(work);
  }
  if (expense.getContractor() != null) {
    // save the contractor and set the foreign key
    ContractorDao ctorDao = new ContractorDao();
    Contractor contractor =  expense.getContractor();
    contractor.setCompany(companyKey);
    contractor.addSkill(expense.getTypeOfWork());
    ctorDao.update(contractor);
    Key<Contractor> contractorKey = new Key<Contractor>(companyKey,Contractor.class, contractor.getName());
    expense.setContractorKey(contractorKey);
  }
  try {
    ofy().put(expense);
    LOG.info("expense has been updated");
    return getById(expense.getId(),expense.getRealEstateId(),companyId);
  } catch (Exception e) {
    LOG.fatal("expense cannot be updated: ", e);
    return null;
  }
}
}
