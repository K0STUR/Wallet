package pl.edu.wszib.wallet.services;

import pl.edu.wszib.wallet.model.Expense;
import pl.edu.wszib.wallet.model.view.ExpenseModel;

import java.util.List;
import java.util.Map;

public interface IExpenseService {
    void addNewExpense (ExpenseModel expenseModel);
    List<Expense> getUserExpenses();
    Double getSumExpensesFromMonth(String month);
    List<Expense> getExpensesFromMonth(String month);
    Map<String, Double> getPercentValueOfCategories(String month);
}