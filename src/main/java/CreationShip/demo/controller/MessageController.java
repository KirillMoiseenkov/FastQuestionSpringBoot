package CreationShip.demo.controller;

import CreationShip.demo.models.*;
import CreationShip.demo.service.LanguageService;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
public class MessageController {

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

    @RequestMapping(value = "getMessageByQuestion")
    @ResponseBody
    public List <Message> getMessageByQuestion(@RequestBody LongJSON longJSON){
        return messageService.getByQuestion(longJSON.getValue());
    }

    @RequestMapping(value = "addMessage")
    public void addMessage(@RequestBody Message message){

        messageService.saveOrUpdate(message);

    }


}
