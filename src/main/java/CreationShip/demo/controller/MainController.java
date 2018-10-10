package CreationShip.demo.controller;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class MainController {

    @RequestMapping(value = "index")
    public ModelAndView index(){
        return new ModelAndView("index.html");
    }

}
