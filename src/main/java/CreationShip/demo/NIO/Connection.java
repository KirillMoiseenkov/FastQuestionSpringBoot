package CreationShip.demo.NIO;

import CreationShip.demo.NIO.connector.AnswerQuestionConnector;
import CreationShip.demo.NIO.connector.AskQuestionConnector;
import CreationShip.demo.NIO.connector.GetAnswerConnector;
import CreationShip.demo.NIO.connector.IConnector;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;

import java.util.HashMap;

public class Connection implements IConnection{

    private int stage = 1;
    private IConnector iConnector;
    private AnswerQuestionConnector answerQuestionConnector;
    private AskQuestionConnector askQuestionConnector;
    private GetAnswerConnector getAnswerConnector;


    private HashMap<Integer, IConnector> stages = new HashMap<>();

    public Connection(MessageService messageService, QuestionService questionService, Reader reader, Writer writer){

        answerQuestionConnector = new AnswerQuestionConnector(messageService,questionService, reader, writer);
        askQuestionConnector = new AskQuestionConnector(messageService,questionService, reader, writer);
        getAnswerConnector = new GetAnswerConnector(messageService,questionService, reader, writer);

        stages.put(1,answerQuestionConnector);
        stages.put(2,askQuestionConnector);
        stages.put(3,getAnswerConnector);

        iConnector = stages.get(1);
    }

    @Override
    public void upStage() {
        stage++;
        iConnector = stages.get(stage);

    }

    @Override
    public void downStage() {
        stage--;
        iConnector = stages.get(stage);
    }

    @Override
    public void write() {
        iConnector.write();
    }

    @Override
    public String read() {
       return iConnector.read();
    }
}
