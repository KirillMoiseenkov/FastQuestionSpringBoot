package CreationShip.demo.controller;

import CreationShip.demo.models.Question;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController(value = "question")
public class QuestionController {

    @Autowired
    MessageService messageService;

    @Autowired
    QuestionService questionService;


    @RequestMapping(value = "getAllQuestion")
    @ResponseBody
    public List<Question> getAllQuestion(){
        return questionService.getAll();
    }

    @RequestMapping(value = "getRandomQustion")
    @ResponseBody
    public Question getRandomQustion(){
        return questionService.getRandomQuestion(1).get(0);
    }

}
