package pl.edu.wszib.wallet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.wallet.dao.IExpenseDAO;
import pl.edu.wszib.wallet.model.Expense;
import pl.edu.wszib.wallet.model.view.ExpenseModel;
import pl.edu.wszib.wallet.services.IExpenseService;
import pl.edu.wszib.wallet.session.SessionObject;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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

    @Override
    public List<Expense> getExpensesFromMonth(String month) {
        List<Expense> expenses = new ArrayList<>();

        List<Expense> expensesFromMonth = new ArrayList<>();

        Date date = new Date();

        expenses = this.expenseDAO.getExpensesByUserId(this.sessionObject.getLoggedUser().getId());

        switch (month) {
            case "Styczen":
                date.setMonth(Calendar.JANUARY);
                break;
            case "Luty":
                date.setMonth(Calendar.FEBRUARY);
                break;
            case "Marzec":
                date.setMonth(Calendar.MARCH);
                break;
            case "Kwiecien":
                date.setMonth(Calendar.APRIL);
                break;
            case "Maj":
                date.setMonth(Calendar.MAY);
                break;
            case "Czerwiec":
                date.setMonth(Calendar.JUNE);
                break;
            case "Lipiec":
                date.setMonth(Calendar.JULY);
                break;
            case "Sierpien":
                date.setMonth(Calendar.AUGUST);
                ;
                break;
            case "Wrzesien":
                date.setMonth(Calendar.SEPTEMBER);
                break;
            case "Pazdiernik":
                date.setMonth(Calendar.OCTOBER);
                break;
            case "Listopad":
                date.setMonth(Calendar.NOVEMBER);
                break;
            case "Grudzien":
                date.setMonth(Calendar.DECEMBER);
                break;
            default:
                break;
        }

        for (Expense expense : expenses) {
            if (expense.getDate().getMonth() == date.getMonth()) {
                expensesFromMonth.add(expense);

            }
        }

        return expensesFromMonth;


    }

    @Override
    public Double getSumExpensesFromMonth(String month) {
        List<Expense> expenses = getExpensesFromMonth(month);


        Double sum = 0.0;

        for (Expense expense : expenses) {
            sum += expense.getValue();
        }

        return sum;


    }

    @Override
    public Map<String, Double> getPercentValueOfCategories(String month) {
        Map<String, Double> categoriesValue = new LinkedHashMap<>();

        List<Expense> expenses = getExpensesFromMonth(month);
        Double sum = getSumExpensesFromMonth(month);



        String[] category = new Expense().getNames(Expense.Category.class);

        if(expenses.isEmpty()) {
            for(int i=0; i<category.length;i++) {
                categoriesValue.put(category[i], 0.0);
            }
            return categoriesValue;
        }


        for (int i = 0; i < category.length; i++) {
            Double categoryPercent = 0.0;
            categoryPercent += getSumCategory(category[i], expenses);
            Double value = BigDecimal.valueOf(categoryPercent*100/sum).setScale(2, RoundingMode.UP).doubleValue();
            categoriesValue.put(category[i], value);
            //categoriesValue.put(category[i],(categoryPercent*100)/sum);
        }

        return categoriesValue;


    }


    private Double getSumCategory(String category, List<Expense> expenses) {

        Double sum = 0.0;

        for (Expense expense : expenses) {
            if (expense.getCategory().toString().equals(category)) {
                sum += expense.getValue();
            }
        }


        return sum;

    }
}
