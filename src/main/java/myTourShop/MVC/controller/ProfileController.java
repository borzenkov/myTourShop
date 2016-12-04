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

/**
 * Created by imac on 03.12.16.
 */
@Controller
@SessionAttributes("user")
public class ProfileController {

    private ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    UserJDBCTemplate userJDBCTemplate =
            (UserJDBCTemplate)context.getBean("userJDBCTemplate");

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView get(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        boolean existsWithToken = userJDBCTemplate.existsWithToken(user.getToken());
        if(existsWithToken) {
            user = userJDBCTemplate.getUser(user.getToken());
            modelAndView.addObject("user", user);
            modelAndView.setViewName("profile_viewer");
        } else {
            modelAndView.setViewName("sign_in");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/profile_editor", method = RequestMethod.GET)
    public ModelAndView openProfileEditor(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        boolean existsWithToken = userJDBCTemplate.existsWithToken(user.getToken());
        if(existsWithToken) {
            user = userJDBCTemplate.getUser(user.getToken());
            modelAndView.addObject("user", user);
            modelAndView.setViewName("profile_editor");
        } else {
            modelAndView.setViewName("sign_in");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/profile_editor", method = RequestMethod.POST)
    public ModelAndView editProfile(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        boolean existsWithToken = userJDBCTemplate.existsWithToken(user.getToken());
        if(existsWithToken) {
            userJDBCTemplate.updateEmail(user.getToken(), user.getEmail());
            userJDBCTemplate.updatePassword(user.getToken(), user.getPassword());
            modelAndView.setViewName("profile_viewer");
        } else {
            modelAndView.setViewName("sign_in");
        }

        return modelAndView;
    }

    @ModelAttribute("user")
    public User initializeUser(){
        return (User) context.getBean("user");
    }
}
