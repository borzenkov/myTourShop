package myTourShop.MVC.controller;

import myTourShop.MVC.model.User;
import myTourShop.MVC.model.UserJDBCTemplate;
import myTourShop.utils.HashCodeGenerator;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static myTourShop.MVC.Constants.EMAIL_EXISTS_MESSAGE;
import static myTourShop.MVC.Constants.SIGN_UP_SUCCEEDED_MESSAGE;
import static myTourShop.MVC.Constants.USER_ROLE;
import static org.apache.log4j.Logger.getLogger;

/**
 * Created by imac on 02.12.16.
 */
@Controller
@SessionAttributes("user")
public class SignUpController {

    private ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    private UserJDBCTemplate userJDBCTemplate =
            (UserJDBCTemplate)context.getBean("userJDBCTemplate");
    private static final Logger logger = getLogger(SignUpController.class);

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public ModelAndView get(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("sign_up");
        return modelAndView;
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ModelAndView post(@ModelAttribute("user") User user) {

        ModelAndView modelAndView = new ModelAndView();

        String email = user.getEmail();
        boolean userExists = userJDBCTemplate.exists(email);

        if ( !userExists ) {
            String passwordHashCode = HashCodeGenerator.getHashCode( user.getPassword() );
            userJDBCTemplate.create(user.getEmail(), passwordHashCode, USER_ROLE);
            modelAndView.setViewName("sign_up_good_result");
            logger.debug("A new user created. Email: " + user.getEmail());
        } else {
            modelAndView.setViewName("sign_up_bad_result");
        }

        return modelAndView;
    }

    @ModelAttribute("user")
    public User initializeUser(){
        return (User) context.getBean("user");
    }
}
