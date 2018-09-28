package CreationShip.demo.controller;

import CreationShip.demo.models.Message;
import CreationShip.demo.models.Question;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    QuestionService questionService;


    @RequestMapping(value = "getAllMessage")
    @ResponseBody
    public String getAllMessage(){
        return messageService.getAll().toString();
    }

}
