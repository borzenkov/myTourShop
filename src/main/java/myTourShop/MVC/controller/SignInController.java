package myTourShop.MVC.controller;

import myTourShop.MVC.model.User;
import myTourShop.MVC.model.UserJDBCTemplate;
import myTourShop.utils.TokenGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static myTourShop.MVC.Constants.SIGN_IN_FAILED_MESSAGE;
import static myTourShop.MVC.Constants.SIGN_IN_SUCCEEDED_MESSAGE;

/**
 * Created by imac on 02.12.16.
 */
@Controller
@SessionAttributes("user")
public class SignInController {

    private ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    UserJDBCTemplate userJDBCTemplate =
            (UserJDBCTemplate)context.getBean("userJDBCTemplate");

    @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
    public ModelAndView get() {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) context.getBean("user");
        modelAndView.addObject("user", user);
        modelAndView.setViewName("sign_in");
        return modelAndView;
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public ModelAndView post(@ModelAttribute("user") User user) {

        ModelAndView modelAndView = new ModelAndView();

        String email = user.getEmail();
        String password = user.getPassword();
        boolean userExists = userJDBCTemplate.exists(email, password);

        if ( !userExists ) {
            modelAndView.setViewName("sign_in_bad_result");
        } else {
            String token = TokenGenerator.nextToken();
            user.setToken(token);
            userJDBCTemplate.updateToken(email, token);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("sign_in_good_result");
        }

        return modelAndView;
    }

    @ModelAttribute("user")
    public User initializeUser(){
        return (User) context.getBean("user");
    }
}
