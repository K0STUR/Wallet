package pl.edu.wszib.wallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.wallet.model.Expense;
import pl.edu.wszib.wallet.model.view.ExpenseModel;
import pl.edu.wszib.wallet.services.IExpenseService;
import pl.edu.wszib.wallet.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class WalletController {

    @Autowired
    IExpenseService expenseService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addExpense", method = RequestMethod.GET)
    public String addExpenseForm(Model model){
        if(!this.sessionObject.isLogged()){
            return "redirect:/login";
        }
        model.addAttribute("expense",new ExpenseModel());
        return "addExpense";
    }
    @RequestMapping(value = "/addExpense", method = RequestMethod.POST)
    public String addExpense(@ModelAttribute ExpenseModel expenseModel){
        if(!this.sessionObject.isLogged()){
            return "redirect:/login";
        }
       // expense.setUser(this.sessionObject.getLoggedUser());
        this.expenseService.addNewExpense(expenseModel);
        return "redirect:/main";
    }

}
