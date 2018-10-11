package CreationShip.demo.controller;

import CreationShip.demo.models.Question;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

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

    @RequestMapping(value = "getRandomQuestion")
    @ResponseBody
    public Question getRandomQuestion(){
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

    @RequestMapping(value = "getQuestionForm")
    @ResponseBody
    public ModelAndView getQuestionForm()
    {
        return new ModelAndView("send_my_question_form.html");
    }

    @RequestMapping(value = "addNewQuestion")
    public Boolean addNewQuestion(@RequestBody Question question, HttpSession session)
    {

        Integer counter = (Integer) session.getAttribute("Counter");

        if(counter != null
                && counter >= 3
                    && !question.getQuestion().isEmpty())
        {
            session.setAttribute("Counter", 0);
            Long id = questionService.save(question).getId();

            Set<Long> ids = (TreeSet<Long>) session.getAttribute("myQuestionsIdsSet");

            if(ids == null)
            {
                ids = new TreeSet<Long>();
            }

            ids.add(id);
            session.setAttribute("myQuestionsIdsSet", ids);

            return true;
        }

        return false;
    }
}
