package CreationShip.demo.IO;
//Prepare to working


import CreationShip.demo.dao.MessageDaoImpl;
import CreationShip.demo.models.Message;
import CreationShip.demo.models.Question;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.List;


public class Worker implements Runnable {

    private static final Logger log = Logger.getLogger(Worker.class);
    private Socket socket;
    private MessageService messageService;
    private QuestionService questionService;
    private InputStream in;
    private PrintWriter out;

    public Worker() {
    }

    public Worker(Socket socket) {

        messageService = new MessageService();
        questionService = new QuestionService();
        this.socket = socket;

    }

    public Worker(Socket socket, MessageService messageService, QuestionService questionService) {

        this.messageService = messageService;
        this.questionService = questionService;
        this.socket = socket;
    }


    public void run() {

        try {
            InputStream in = socket.getInputStream();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            MessageGetter messageGetter = new MessageGetter(br);
            MessageSender messageSender = new MessageSender(out);
            //Inititalization

            for (int i = 0; i < 3; i++) {
                Long id = 1L;
                Question question = new Question();

                do {
                    question = questionService.getRandomQuestion(1).get(0);
                } while (question == null);

                messageSender.send(question.getQuestion());

                messageGetter.getMessage();
                log.debug(messageGetter.getCache());

                Message message = new Message(question, messageGetter.getCache());

                messageService.saveOrUpdate(message);

            }
            //Ask on the Question from DB


            out.println("ask your question");
            messageGetter.getMessage();
            log.debug("question is " + messageGetter.getCache());
            Question question = new Question(messageGetter.getCache());
            log.debug(messageGetter.getCache());

            Long messageId = questionService.saveOrUpdate(question).getId();
            List<Question> questionList;
            List<Message> messageList;


            questionList = questionService.getAll();

            while (true) {
                messageList = messageService.getByQuestion(messageId);
                if (messageList.size() > 0)
                    break;
            }

            messageList.forEach(message -> messageSender.send(message.getMessage()));

            Long oldId = messageList.get(messageList.size() - 1).getId();
            Long id;


            while (true) {
                messageList = messageService.getByQuestion(messageId, oldId);

                if (messageList.size() > 0) {
                    messageList.forEach(message -> messageSender.send(message.getMessage()));
                    oldId = messageList.get(messageList.size() - 1).getId();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            }
            finally {

            try {
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
