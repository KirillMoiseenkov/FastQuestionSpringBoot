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
    private Question question;// = new Question();
    private int counter = 0;

    public AnswerQuestionConnector(MessageService messageService, QuestionService questionService){
        this.messageService = messageService;
        this.questionService = questionService;

    }



    @Override
    public boolean getStateStage() {
        if(counter > 3){
            counter = 0;
            return true;
        }else {
            return false;
        }
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setWriter(Writer writer) {

        counter++;
        this.writer = writer;
    }

    @Override
    public String read() {

        String responce = reader.read();

        System.out.println(question.toString());

        Message message = new Message(question,responce);
        messageService.saveOrUpdate(message);


        return responce;
    }

    @Override
    public void write() {



        question = questionService.getRandomQuestion(1).get(0);
        while (question.getQuestion().length() < 4) {
            question = questionService.getRandomQuestion(1).get(0);
        }


        writer.write(question.getQuestion() + System.lineSeparator());

        System.out.println( "question is " + question.getQuestion());

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

    @Override
    public void setQuestion(Question question) {
        this.question = question;
    }


}
