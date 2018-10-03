package CreationShip.demo.controller;

import CreationShip.demo.models.user.User;
import CreationShip.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController
{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "addNewUser", method = RequestMethod.POST)
    @ResponseBody
    public User addNewUser(@RequestBody User user)
    {
        this.userService.save(user);
        return user;
    }

    @RequestMapping(value = "getRegisterForm",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getRegisterForm()
    {
        return new ModelAndView("register_form.html");
    }

    @RequestMapping(value = "getAuthForm",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getAuthForm()
    {
        return new ModelAndView("auth_form.html");
    }

    @RequestMapping(value = "test",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView test()
    {
        return new ModelAndView("test.html");
    }

}
