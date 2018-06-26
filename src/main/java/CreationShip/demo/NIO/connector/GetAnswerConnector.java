package CreationShip.demo.NIO.connector;

import CreationShip.demo.NIO.Reader;
import CreationShip.demo.NIO.Writer;
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

    public GetAnswerConnector(MessageService messageService, QuestionService questionService, Reader reader, Writer writer){
        this.messageService = messageService;
        this.questionService = questionService;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public String read() {

        return null;
        }

    @Override
    public void write() {

        messageList = messageService.getByQuestion(messageId);
        if (messageList.size() > 0){
            return;
        }

            oldId = messageList.get(messageList.size() - 1).getId();

            messageList = messageService.getByQuestion(messageId, oldId);

            if (messageList.size() > 0) {
                messageList.forEach(message -> writer.write(message.getMessage()));
                oldId = messageList.get(messageList.size() - 1).getId();
            }
    }

    @Override
    public Question getQuestion() {
        return question;
    }
}
