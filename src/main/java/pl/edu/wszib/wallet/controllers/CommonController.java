package pl.edu.wszib.wallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.wallet.services.IExpenseService;
import pl.edu.wszib.wallet.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {

    @Autowired
    IExpenseService expenseService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage(){
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model){

        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        model.addAttribute("isLogged",this.sessionObject.isLogged());
        model.addAttribute("expenses", this.expenseService.getUserExpenses());

        return "main";
    }



}
