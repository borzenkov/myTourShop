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
@SessionAttributes("token")
public class ProfileController {

    private ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    UserJDBCTemplate userJDBCTemplate =
            (UserJDBCTemplate)context.getBean("userJDBCTemplate");

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView get(@ModelAttribute("token") String token) {
        ModelAndView modelAndView = new ModelAndView();
        boolean existsWithToken = userJDBCTemplate.existsWithToken(token);
        if(existsWithToken) {
            User user = userJDBCTemplate.getUser(token);
            modelAndView.addObject("user", user);
        } else {}

        modelAndView.setViewName("profile_viewer");
        return modelAndView;
    }

    @RequestMapping(value = "/profile_editor", method = RequestMethod.GET)
    public ModelAndView openProfileEditor(@ModelAttribute("token") String token) {
        ModelAndView modelAndView = new ModelAndView();
        boolean existsWithToken = userJDBCTemplate.existsWithToken(token);
        if(existsWithToken) {
            User user = userJDBCTemplate.getUser(token);
            modelAndView.addObject("user", user);
        } else {}

        modelAndView.setViewName("profile_editor");
        return modelAndView;
    }

    @RequestMapping(value = "/profile_editor", method = RequestMethod.POST)
    public ModelAndView editProfile(@ModelAttribute("token") String token, @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        boolean existsWithToken = userJDBCTemplate.existsWithToken(token);
        if(existsWithToken) {
            userJDBCTemplate.updateEmail(token, user.getEmail());
            userJDBCTemplate.updatePassword(token, user.getPassword());
        } else {}

        return modelAndView;
    }
}
