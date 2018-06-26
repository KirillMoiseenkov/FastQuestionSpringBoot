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

    public Connection(MessageService messageService, QuestionService questionService){

        answerQuestionConnector = new AnswerQuestionConnector(messageService,questionService);
        askQuestionConnector = new AskQuestionConnector(messageService,questionService);
        getAnswerConnector = new GetAnswerConnector(messageService,questionService);

        stages.put(1,answerQuestionConnector);
        stages.put(2,askQuestionConnector);
        stages.put(3,getAnswerConnector);

        iConnector = stages.get(1);
    }


    public void setReader(Reader reader){
        answerQuestionConnector.setReader(reader);
        askQuestionConnector.setReader(reader);
        getAnswerConnector.setReader(reader);
    }

    public void setWrite(Writer write){
        answerQuestionConnector.setWriter(write);
        askQuestionConnector.setWriter(write);
        getAnswerConnector.setWriter(write);
    }

    public int getStage() {
        return stage;
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
