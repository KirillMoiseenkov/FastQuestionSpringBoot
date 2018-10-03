package CreationShip.demo.controller;

import CreationShip.demo.models.Message;
import CreationShip.demo.models.user.User;
import CreationShip.demo.service.GetTokenSerivce;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@SessionAttributes(types = User.class)
public class UserController
{

    @Autowired
    private GetTokenSerivce getTokenSerivce;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "validateAuthForm", method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateAuthForm(@RequestBody User user)
    {
        return this.userService.validateUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @RequestMapping(value = "getMyMessages", method = RequestMethod.POST)
    @ResponseBody
    public List<Message> getMyMessages(@ModelAttribute @RequestBody User user)
    {
        return this.messageService.getMessagesByUser(user);
    }

    @RequestMapping(value = "addNewUser", method = RequestMethod.POST)
    @ResponseBody
    public User addNewUser(@RequestBody User user)
    {
        if(this.userService.validateUsernameAndPassword(user.getUsername(), user.getPassword()))
        {
            this.userService.save(user);
            return user;
        }

        return null;
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
