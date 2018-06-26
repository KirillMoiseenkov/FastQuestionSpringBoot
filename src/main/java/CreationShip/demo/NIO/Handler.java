package CreationShip.demo.NIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.tree.FixedHeightLayoutCache;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class Handler {


    @Autowired
    private EchoServer echoServer;

    public void startServer(){
        echoServer.run();
    }

    public static void main(String[] args) throws IOException {

        EchoServer echoServer = new EchoServer();
                //new EchoServer(64, null);


        /*Thread thread = echoServer;

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.submit(thread);*/

        echoServer.run();


    }

}
