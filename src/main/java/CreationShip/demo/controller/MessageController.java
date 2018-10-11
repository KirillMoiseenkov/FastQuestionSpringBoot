package CreationShip.demo.controller;

import CreationShip.demo.models.Message;
import CreationShip.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

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

    @RequestMapping(value = "getMyMessages")
    public List<Message> getMyMessages(HttpSession session)
    {
        Set<Long> ids = (TreeSet<Long>) session.getAttribute("myQuestionsIdsSet");

        return this.messageService.getMessagesByMyQuestionIds(ids);
    }

    @RequestMapping(value = "addMessage")
    public Boolean addMessage(@RequestBody Message message, HttpSession session)
    {

        Integer counter = (Integer) session.getAttribute("Counter");

        if(!message.getMessage().isEmpty())
        {
            if(counter == null)
            {
                session.setAttribute("Counter", 0);
            }
            else
            {
                session.setAttribute("Counter", ++ counter);
            }

            this.messageService.save(message);
            return true;
        }
        return false;
    }


}
