
package eu.comexis.napoleon.server.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.ExpenseService;
import eu.comexis.napoleon.server.dao.ExpenseDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.expense.GetAllExpenseCommand;
import eu.comexis.napoleon.shared.command.expense.GetAllExpenseResponse;
import eu.comexis.napoleon.shared.command.expense.GetExpenseCommand;
import eu.comexis.napoleon.shared.command.expense.GetExpenseResponse;
import eu.comexis.napoleon.shared.command.expense.UpdateExpenseCommand;
import eu.comexis.napoleon.shared.command.expense.UpdateExpenseResponse;
import eu.comexis.napoleon.shared.model.Expense;

public class ExpenseServiceImpl extends RemoteServiceServlet implements ExpenseService{

  public ExpenseServiceImpl() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public GetExpenseResponse execute(GetExpenseCommand command) {
    ExpenseDao dao = new ExpenseDao();
    String companyId = UserManager.INSTANCE.getCompanyId();
    Expense expense = dao.getById(command.getId(), command.getRealEstateId(), companyId);
    GetExpenseResponse response = new GetExpenseResponse();
    response.setExpense(expense);
    return response;
  }

  @Override
  public GetAllExpenseResponse execute(GetAllExpenseCommand command) {
    ExpenseDao dao = new ExpenseDao();
    String companyId = UserManager.INSTANCE.getCompanyId();
    List<Expense> expenseList = new ArrayList<Expense>();
    if (command.getRealEstateId()!=null && !command.getRealEstateId().isEmpty()){
      expenseList = dao.getAllExpense(command.getRealEstateId(),companyId);
    }else{
      expenseList = dao.getAllExpense(companyId);
    }
    GetAllExpenseResponse response = new GetAllExpenseResponse();
    response.setListExpense(expenseList);
    return response;
  }

  @Override
  public UpdateExpenseResponse execute(UpdateExpenseCommand command) {
    String companyId = UserManager.INSTANCE.getCompanyId();
    ExpenseDao dao = new ExpenseDao();
    Expense expense = dao.update(command.getExpense(),companyId);
    UpdateExpenseResponse response = new UpdateExpenseResponse();
    response.setExpense(expense);
    return response;
  }

}
