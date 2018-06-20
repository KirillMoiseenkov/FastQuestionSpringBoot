package CreationShip.demo.IO;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class Acceptor implements Runnable{

    public static int   PORT = 8080;
    private ServerSocket serverSocket;
    private boolean      isStopped    = false;

    @Autowired
    private MessageService messageService;

    @Autowired
    private QuestionService questionService;


    public Acceptor(){};

    public Acceptor(int PORT){
        Acceptor.PORT = PORT;
    }

    public void run()
    {

            openServerSocket();

        System.out.println("server started on the port:" + " " + PORT);
            while(!isStopped()){
                Socket clientSocket = null;

                try {
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    if(isStopped()) {
                        System.out.println("Server is Stopped.") ;
                        return;
                    }
                    throw new RuntimeException(
                            "Error accepting client connection", e);
                }

                System.out.println("connect");

               // new Thread(new Worker(clientSocket)).start();
                new Thread(new Worker(clientSocket,messageService,questionService)).start();
            }

     }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket(){
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {

            throw new RuntimeException("Cannot open port" + PORT + " : " ,  e);
        }
    }

}
