package pl.edu.wszib.wallet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.wallet.dao.IExpenseDAO;
import pl.edu.wszib.wallet.model.Expense;
import pl.edu.wszib.wallet.services.IExpenseService;
import pl.edu.wszib.wallet.session.SessionObject;

import javax.annotation.Resource;

@Service
public class ExpenseServiceImpl implements IExpenseService {

    @Autowired
    IExpenseDAO expenseDAO;


    @Override
    public void addNewExpense(Expense expense) {
        this.expenseDAO.addNewExpense(expense);
    }
}
