package CreationShip.demo.NIO;

public interface IConnection {

    public void upStage();
    public void downStage();

    public void write();
    public String read();
}
