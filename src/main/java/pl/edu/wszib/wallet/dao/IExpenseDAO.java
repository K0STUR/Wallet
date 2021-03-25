package pl.edu.wszib.wallet.dao;


import pl.edu.wszib.wallet.model.Expense;

import java.util.List;

public interface IExpenseDAO {
    void addNewExpense (Expense expense);
    List<Expense> getExpensesByUserId(int id);

}
