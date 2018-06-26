package CreationShip.demo.NIO.connector;

import CreationShip.demo.NIO.Reader;
import CreationShip.demo.NIO.Writer;
import CreationShip.demo.models.Question;

public interface IConnector {

    public String read();
    public void write();
    public Question getQuestion();

    public void setWriter(Writer writer);
    public void setReader(Reader reader);

}
