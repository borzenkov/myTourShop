package myTourShop.MVC.controller;

import myTourShop.MVC.model.UserJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by imac on 01.12.16.
 */
@Controller
public class MainController {
    /*First method on start application*/
    /*Попадаем сюда на старте приложения (см. параметры аннотации и настройки пути после деплоя) */

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserJDBCTemplate userJDBCTemplate =
                (UserJDBCTemplate)context.getBean("userJDBCTemplate");

        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("userJSP", (User)context.getBean("user"));
        modelAndView.setViewName("index");
        return modelAndView;
    }
}