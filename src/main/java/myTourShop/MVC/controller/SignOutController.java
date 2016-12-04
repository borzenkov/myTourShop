package myTourShop.MVC.controller;

import myTourShop.MVC.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by imac on 03.12.16.
 */
@Controller
@SessionAttributes("user")
public class SignOutController {

    private ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    @RequestMapping(value = "/sign_out", method = RequestMethod.GET)
    public ModelAndView get() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", (User) context.getBean("user"));
        modelAndView.setViewName("index_for_guest");
        return modelAndView;
    }
}
