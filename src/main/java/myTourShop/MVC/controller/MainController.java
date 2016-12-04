package myTourShop.MVC.controller;

import myTourShop.MVC.model.User;
import myTourShop.MVC.model.UserJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static myTourShop.MVC.Constants.ADMIN_ROLE;

/**
 * Created by imac on 01.12.16.
 */
@Controller
@SessionAttributes("user")
public class MainController {

    private ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    UserJDBCTemplate userJDBCTemplate =
            (UserJDBCTemplate)context.getBean("userJDBCTemplate");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView get(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();

        if ( userJDBCTemplate.existsWithToken(user.getToken()) ) {
            if ( ADMIN_ROLE.equals(user.getRole()) ) {
                modelAndView.setViewName("index_for_admin");
            } else {
                modelAndView.setViewName("index_for_user");
            }
        } else {
            modelAndView.setViewName("index_for_guest");
        }

        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @ModelAttribute("user")
    public User initializeUser(){
        return (User) context.getBean("user");
    }
}