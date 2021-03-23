package pl.edu.wszib.wallet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.wallet.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {

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

        model.addAttribute("isLogged",this.sessionObject.isLogged());

        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        return "main";
    }

}
