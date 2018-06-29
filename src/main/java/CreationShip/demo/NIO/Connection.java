package CreationShip.demo.NIO;

import CreationShip.demo.NIO.connector.AnswerQuestionConnector;
import CreationShip.demo.NIO.connector.AskQuestionConnector;
import CreationShip.demo.NIO.connector.GetAnswerConnector;
import CreationShip.demo.NIO.connector.IConnector;
import CreationShip.demo.models.Question;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;

import java.util.HashMap;

public class Connection implements IConnection{

    private int stage = 1;
    private IConnector iConnector;
    private AnswerQuestionConnector answerQuestionConnector;
    private AskQuestionConnector askQuestionConnector;
    private GetAnswerConnector getAnswerConnector;

    private int counter = 0;

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
        counter = 0;
        stage++;
        iConnector = stages.get(stage);

        System.out.println("up stage");

    }

    @Override
    public void downStage() {
        counter = 0;
        stage--;
        iConnector = stages.get(stage);

        System.out.println("down stage");
    }

    @Override
    public void write() {

        if(iConnector.getStateStage()) {
            Question question = new Question();
            question = iConnector.getQuestion();
            upStage();
            iConnector.setQuestion(question);
        }

        iConnector.write();

    }

    @Override
    public String read() {
       // counter++;


        String msg = iConnector.read();

        return msg;


    }

    public int getCounter() {
        return counter;
    }
}
