package CreationShip.demo.NIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Handler {


    @Autowired
    private Server server;

    public void startServer(){
        server.run();
    }

    public static void main(String[] args) throws IOException {

        Server server = new Server();
                //new EchoServer(64, null);


        /*Thread thread = echoServer;

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.submit(thread);*/

        server.run();


    }

}
