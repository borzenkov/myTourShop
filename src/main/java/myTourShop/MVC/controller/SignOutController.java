package myTourShop.MVC.controller;

import myTourShop.MVC.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by imac on 03.12.16.
 */
@Controller
@SessionAttributes("token")
public class SignOutController {

    @RequestMapping(value = "/sign_out", method = RequestMethod.GET)
    public ModelAndView get() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("token", "");
        modelAndView.setViewName("index_for_guest");
        return modelAndView;
    }
}
