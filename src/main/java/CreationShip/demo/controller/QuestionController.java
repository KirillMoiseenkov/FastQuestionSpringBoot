package CreationShip.demo.controller;

import CreationShip.demo.models.Question;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@SessionAttributes("question")
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

    @RequestMapping(value = "getRandomQuestionByLanguage")
    @ResponseBody
    public Question getRandomQuestionByLanguage(@RequestParam("lang") String lang)
    {
        return questionService.getRandomQuestionByLanguage(1, lang).get(0);
    }

    @RequestMapping(value = "getAnswerForm")
    @ResponseBody
    public ModelAndView getAnswerForm()
    {
        return new ModelAndView("user_answer_on_question_form.html");
    }

    @RequestMapping(value = "getSessionQuestion")
    @ResponseBody
    public String getSessionQuestion(@ModelAttribute("question") Question question){
        System.out.println("pon");
        return question.toString();
    }

    @RequestMapping(value = "addQuestion")
    public void addNewQuestion(@RequestBody Question question){
        questionService.save(question);
    }
}
