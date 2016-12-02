package myTourShop.springMVC.controller;

import myTourShop.springMVC.model.User;
import myTourShop.springMVC.model.UserJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by imac on 02.12.16.
 */
@Controller
public class SignupController {

    private ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signupGet() {
        User user = (User) context.getBean("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signupPost(@ModelAttribute("user") User user) {
        UserJDBCTemplate userJDBCTemplate =
                (UserJDBCTemplate)context.getBean("userJDBCTemplate");
        userJDBCTemplate.create(user.getEmail(), user.getPassword());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup-result");
        return modelAndView;
    }
}
