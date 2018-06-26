package CreationShip.demo.NIO.connector;

import CreationShip.demo.NIO.IConnection;
import CreationShip.demo.NIO.Reader;
import CreationShip.demo.NIO.Writer;
import CreationShip.demo.models.Message;
import CreationShip.demo.models.Question;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;

public class AnswerQuestionConnector implements IConnector {

    private Reader reader;
    private Writer writer;
    private MessageService messageService;
    private QuestionService questionService;
    private Question question;


    public AnswerQuestionConnector(MessageService messageService, QuestionService questionService, Reader reader, Writer writer){
        this.messageService = messageService;
        this.questionService = questionService;
        this.reader = reader;
        this.writer = writer;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public String read() {

        String responce = reader.read();

      //  messageService.saveOrUpdate(new Message(question,responce));


        return responce;
    }

    @Override
    public void write() {



        Question question = questionService.getRandomQuestion(1).get(0);
        while (question.getQuestion().length() < 4) {
            question = questionService.getRandomQuestion(1).get(0);
        }




        writer.write(question.getQuestion() + System.lineSeparator());

        System.out.println(question.getQuestion());
        //writer.write(reader.getResponce());
    }

    public void write(String msg) {

        //Question question = questionService.getRandomQuestion(1).get(0);
        //writer.write(question.getQuestion());
        writer.write(msg);
    }

    @Override
    public Question getQuestion() {
        return question;
    }
}
