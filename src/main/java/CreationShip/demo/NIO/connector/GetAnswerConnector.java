package CreationShip.demo.NIO.connector;

import CreationShip.demo.NIO.comunic.Reader;
import CreationShip.demo.NIO.comunic.Writer;
import CreationShip.demo.models.Message;
import CreationShip.demo.models.Question;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;

import java.util.List;

public class GetAnswerConnector implements IConnector {

    private Reader reader;
    private Writer writer;
    private MessageService messageService;
    private QuestionService questionService;
    private Question question;
    private Long messageId;
    private Long oldId;
    private List<Message> messageList;
    private int counter = 0;

    public GetAnswerConnector(MessageService messageService, QuestionService questionService){
        this.messageService = messageService;
        this.questionService = questionService;
    }

    @Override
    public boolean getStateStage() {
       return false;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public String read() {

        reader.enableWriteMode(true);
        writer.write("pos");
        return "Empty";

    }

    @Override
    public void write() {

        writer.write("koo-koo");
        writer.enableReadMode(false);
       /* messageList = messageService.getByQuestion(messageId);

        if (messageList.size() == 0){
            return;
        }

        writer.write(messageList.get(messageList.size()-1).getMessage());
        System.out.println(messageList.get(messageList.size()-1).getMessage());
        writer.enableReadMode(false);


        System.out.println("messageList.size() = "   + messageList.size());
        messageList.forEach(message ->
        {
            writer.write(message.getMessage());
            System.out.println(message.getMessage());
        });

        System.out.println("messageList.size() after = "   + messageList.size());*/




            /*oldId = messageList.get(messageList.size() - 1).getId();
            messageList = messageService.getByQuestion(messageId, oldId);


            if (messageList.size() > 0) {
                messageList.forEach(message ->
                {
                    writer.write(message.getMessage());
                    System.out.println(message.getMessage());
                });
                oldId = messageList.get(messageList.size() - 1).getId();
            }*/
    }

    @Override
    public Question getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(Question question) {
        this.question = question;
        messageId = question.getId();
    }
}
