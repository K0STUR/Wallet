package pl.edu.wszib.wallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.wallet.model.Expense;
import pl.edu.wszib.wallet.model.view.ExpenseModel;
import pl.edu.wszib.wallet.model.view.MonthModel;
import pl.edu.wszib.wallet.services.IExpenseService;
import pl.edu.wszib.wallet.session.SessionObject;

import javax.annotation.Resource;


@Controller
public class WalletController {

    public final String[] months = {"Styczen", "Luty", "Marzec", "Kwiecien", "Maj", "Czerwiec", "Lipiec", "Sierpien", "Wrzesien", "Pazdiernik", "Listopad", "Grudzien"};

    @Autowired
    IExpenseService expenseService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addExpense", method = RequestMethod.GET)
    public String addExpenseForm(Model model) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        String [] category = new Expense().getNames(Expense.Category.class);

        model.addAttribute("isLogged",this.sessionObject.isLogged());
        model.addAttribute("expenseCategory", category);
        model.addAttribute("expense", new ExpenseModel());
        return "addExpense";
    }

    @RequestMapping(value = "/addExpense", method = RequestMethod.POST)
    public String addExpense(@ModelAttribute ExpenseModel expenseModel) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        this.expenseService.addNewExpense(expenseModel);
        return "redirect:/main";
    }

    @RequestMapping(value = "/previousExpense", method = RequestMethod.GET)
    public String previousExpenseForm(Model model) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        model.addAttribute("isLogged",this.sessionObject.isLogged());
        model.addAttribute("monthModel", new MonthModel());
        model.addAttribute("months", months);


        return "/previousExpense";

    }

    @RequestMapping(value = "/previousExpense", method = RequestMethod.POST)
    public String previousExpense(@ModelAttribute MonthModel monthModel, Model model) {

        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        model.addAttribute("isLogged",this.sessionObject.isLogged());
        model.addAttribute("months", months);
        model.addAttribute("expense", this.expenseService.getSumExpensesFromMonth(monthModel.getMonth()));
        model.addAttribute("categoryValue", this.expenseService.getPercentValueOfCategories(monthModel.getMonth()));


        return "/previousExpense";


    }

}
