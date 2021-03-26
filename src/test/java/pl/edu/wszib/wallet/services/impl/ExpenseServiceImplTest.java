package pl.edu.wszib.wallet.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.wallet.configuration.TestConfiguration;
import pl.edu.wszib.wallet.dao.IExpenseDAO;

import pl.edu.wszib.wallet.model.Expense;
import pl.edu.wszib.wallet.model.User;
import pl.edu.wszib.wallet.services.IExpenseService;

import pl.edu.wszib.wallet.session.SessionObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class ExpenseServiceImplTest {

    @Autowired
    IExpenseService expenseService;

    @Autowired
    IExpenseDAO expenseDAO;

    @Resource
    SessionObject sessionObject;


    @Test
    public void getUserExpensesTestCorrect() {

        this.sessionObject.setLoggedUser(generateUser());


        Mockito.when(this.expenseDAO.getExpensesByUserId(this.sessionObject.getLoggedUser().getId())).thenReturn(generateExpensesList());

        List<Expense> result = this.expenseService.getUserExpenses();

        Assert.assertFalse(result.isEmpty());


    }

    @Test
    public void getUserExpensesTestIncorrect() {

        this.sessionObject.setLoggedUser(generateUser());


        Mockito.when(this.expenseDAO.getExpensesByUserId(this.sessionObject.getLoggedUser().getId())).thenReturn(new ArrayList<>());

        List<Expense> result = this.expenseService.getUserExpenses();

        Assert.assertTrue(result.isEmpty());


    }


    private User generateUser() {
        User user = new User();
        user.setId(1);
        user.setLogin("rafal");
        user.setPass("rafal");

        return user;
    }

    private List<Expense> generateExpensesList() {
        List<Expense> expenses = new ArrayList<>();

        Expense expense = new Expense();
        expense.setId(1);
        expense.setName("test");
        expense.setValue(1.1);
        expense.setCategory(Expense.Category.Inne);
        expense.setDate(new Date(2021, 02, 15));
        expense.setUser(generateUser());


        expenses.add(expense);

        return expenses;
    }


}
