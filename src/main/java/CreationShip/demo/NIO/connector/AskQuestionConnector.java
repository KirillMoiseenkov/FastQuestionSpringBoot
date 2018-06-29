package CreationShip.demo.NIO.connector;

import CreationShip.demo.NIO.Reader;
import CreationShip.demo.NIO.Writer;
import CreationShip.demo.models.Question;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;

public class AskQuestionConnector implements IConnector {

    private Reader reader;
    private Writer writer;
    private MessageService messageService;
    private QuestionService questionService;
    private Question question;

    public AskQuestionConnector(MessageService messageService, QuestionService questionService){
        this.messageService = messageService;
        this.questionService = questionService;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public String read() {

        System.out.println("read question");

       // Question question = new Question(reader.read());
      //  question = questionService.saveOrUpdate(question);

        return reader.read();
    }

    @Override
    public void write() {

        System.out.println("ask question");
        writer.write("Ask question, please" + System.lineSeparator());


    }

    @Override
    public Question getQuestion() {
        return question;
    }
}
