package CreationShip.demo.controller;

import CreationShip.demo.models.Message;
import CreationShip.demo.models.user.User;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@SessionAttributes(types = User.class)
public class UserController
{

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "validateAuthForm", method = RequestMethod.POST)
    @ResponseBody
    public String validateAuthForm(@RequestBody User user)
    {
        if(this.userService.validateUsernameAndPassword(user.getUsername(), user.getPassword()))
        {
            return "success";
        }

        return "fail";
    }


    @RequestMapping(value = "getMyMessages", method = RequestMethod.POST)
    @ResponseBody
    public List<Message> getMyMessages(@RequestBody User user, SessionStatus sessionStatus)
    {
        System.out.println(this.messageService.getMessagesByUser(user));
        return this.messageService.getMessagesByUser(user);
    }

    @RequestMapping(value = "addNewUser", method = RequestMethod.POST)
    @ResponseBody
    public User addNewUser(@RequestBody User user, SessionStatus sessionStatus)
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
