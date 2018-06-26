package CreationShip.demo.NIO.connector;

import CreationShip.demo.models.Question;

public interface IConnector {

    public String read();
    public void write();
    public Question getQuestion();



}
