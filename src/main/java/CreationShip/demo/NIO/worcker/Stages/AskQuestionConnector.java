package CreationShip.demo.NIO.worcker.Stages;

import CreationShip.demo.NIO.comunic.Reader;
import CreationShip.demo.NIO.comunic.Writer;
import CreationShip.demo.models.Question;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;

public class AskQuestionConnector implements IConnector {

    private Reader reader;
    private Writer writer;
    private MessageService messageService;
    private QuestionService questionService;
    private Question question;
    private int counter = 0;

    public AskQuestionConnector(MessageService messageService, QuestionService questionService){
        this.messageService = messageService;
        this.questionService = questionService;
    }

    @Override
    public boolean getStateStage() {
        if(counter > 1){
            counter = 0;
            return true;
        }else {
            return false;
        }
    }

    public void setReader(Reader reader) {
        this.reader = reader;

        counter++;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public String read() {

        System.out.println("read question");

        question = new Question(reader.read());
        question = questionService.saveOrUpdate(question);

        return question.getQuestion();//reader.read();
    }

    @Override
    public void write() {

        System.out.println("ask question");
        writer.write("Ask question, please");


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
