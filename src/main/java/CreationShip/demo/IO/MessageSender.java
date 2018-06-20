package CreationShip.demo.IO;

import java.io.PrintWriter;

public class MessageSender {

    private PrintWriter out;

    MessageSender(PrintWriter out){
        this.out = out;
    }

    public void send(String message){
        out.println(message);
    }
}
