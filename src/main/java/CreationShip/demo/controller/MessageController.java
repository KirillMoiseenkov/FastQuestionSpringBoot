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

/*    @RequestMapping(value = "getMessageByQuestion")
    @ResponseBody
    public List<Message> getMessageByQuestion(@RequestBody LongJSON id){

        System.out.println(id.toString());

        return null;
    }*/

    @RequestMapping(value = "addMessage")
    public void addMessage(@RequestBody Message message){

        messageService.save(message);

    }


}
