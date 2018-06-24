package CreationShip.demo.NIO;

import javax.swing.tree.FixedHeightLayoutCache;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Handler {

    private  static EchoServer echoServer;

    public static void main(String[] args) throws IOException {

        echoServer = new EchoServer(64, null);

        Thread thread = echoServer;

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.submit(thread);
    }

}
