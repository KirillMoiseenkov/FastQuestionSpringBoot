package CreationShip.demo.NIO.connector;

import CreationShip.demo.NIO.comunic.Reader;
import CreationShip.demo.NIO.comunic.Writer;
import CreationShip.demo.models.Question;

public interface IConnector {

    public String read();
    public void write();
    public Question getQuestion();

    public boolean getStateStage();

    public void setWriter(Writer writer);
    public void setReader(Reader reader);

    public void setQuestion(Question question);
}
