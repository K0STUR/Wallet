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
import java.util.ArrayList;
import java.util.Calendar;
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

    @Override
    public Double getSumExpensesFromMonth(String month) {
        List<Expense> expenses = new ArrayList<>();

        Double sum =0.0;

        Date date = new Date();

        expenses = this.expenseDAO.getExpensesByUserId(this.sessionObject.getLoggedUser().getId());

        switch (month){
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
                date.setMonth(Calendar.AUGUST);;
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
            if(expense.getDate().getMonth()==date.getMonth()) {
                sum+=expense.getValue();

            }
        }

        return sum;


    }

}
