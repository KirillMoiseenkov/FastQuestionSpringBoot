package CreationShip.demo.controller;

import CreationShip.demo.models.Message;
import CreationShip.demo.models.user.Role;
import CreationShip.demo.models.user.User;
import CreationShip.demo.service.LanguageService;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;
import CreationShip.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @Autowired
    QuestionService questionService;

    @Autowired
    LanguageService languageService;

    @RequestMapping(value = "getAllMessage")
    @ResponseBody
    public Message getAllMessage(){
        return messageService.getAll().get(0);
    }

    @RequestMapping(value = "getQuestionStatus")
    public Integer getQuestionStatus(HttpSession session)
    {
        return (Integer) session.getAttribute("Counter");
    }

    @RequestMapping(value = "addMessage")
    public Boolean addMessage(@RequestBody Message message, HttpSession session)
    {

        Integer counter = (Integer) session.getAttribute("Counter");

        if(!message.getMessage().isEmpty())
        {
            if(counter == null)
            {
                session.setAttribute("Counter", 1);
            }
            else
            {
                if(counter >= 3)
                    session.setAttribute("Counter", 1);
                else
                    session.setAttribute("Counter", ++ counter);
            }

            this.messageService.save(message);
            return true;
        }
        return false;
    }


}
