package pl.edu.wszib.wallet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.wallet.dao.IExpenseDAO;
import pl.edu.wszib.wallet.model.Expense;
import pl.edu.wszib.wallet.model.view.ExpenseModel;
import pl.edu.wszib.wallet.services.IExpenseService;
import pl.edu.wszib.wallet.session.SessionObject;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseServiceImpl implements IExpenseService {

    @Autowired
    IExpenseDAO expenseDAO;
    @Resource
    SessionObject sessionObject;


    @Override
    public void addNewExpense(ExpenseModel expenseModel) {

        Expense expense = new Expense();

        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        expense.setName(expenseModel.getName());
        expense.setValue(expenseModel.getValue());
        expense.setDate(date);
        expense.setUser(this.sessionObject.getLoggedUser());
        expense.setCategory(Expense.Category.valueOf(expenseModel.getCategory()));

        this.expenseDAO.addNewExpense(expense);

    }

    @Override
    public List<Expense> getUserExpenses() {
        return this.expenseDAO.getExpensesByUserId(this.sessionObject.getLoggedUser().getId());
    }

}
