package CreationShip.demo.NIO;

import CreationShip.demo.models.Message;
import CreationShip.demo.models.Question;
import CreationShip.demo.service.MessageService;
import CreationShip.demo.service.QuestionService;
import jdk.internal.util.xml.impl.ReaderUTF8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class EchoServer extends Thread {

    private static ThreadLocal<Integer> countConnection = new ThreadLocal<>();

    @Autowired
    private MessageService messageService;

    @Autowired
    private QuestionService questionService;

    private ServerSocketChannel serverSocket = ServerSocketChannel.open();
    private int allocate;

    public EchoServer(int allocate, InetSocketAddress inetSocketAddress) throws IOException {

        this.allocate = allocate;

        if (inetSocketAddress == null)
            inetSocketAddress = new InetSocketAddress("localhost", 8080);
        serverSocket.bind(inetSocketAddress);
        serverSocket.configureBlocking(false);


    }

    public static Integer getCountConnection() {
        return countConnection.get();
    }

    @Override
    public void run() {

        Selector selector;
        ByteBuffer buffer;
        countConnection.set(0);
        Map<SelectionKey, String> lastMessage = new HashMap<>();

        try {
            selector = Selector.open();
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            buffer = ByteBuffer.allocate(allocate);
        } catch (IOException e) {
            System.out.println(e);
            return;
        }

        try {

            while (true) {

                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();



                while (iter.hasNext()) {

                    SelectionKey key = iter.next();

                    if (key.isAcceptable()) {
                        countConnection.set(countConnection.get() + 1);
                        System.out.println("connectioSize = " + countConnection.get() + " thread is" + Thread.currentThread());
                        register(selector, serverSocket);

                    }

                    if (key.isValid() && key.isReadable()) {
                        read(buffer, key, selector, lastMessage);
                    }

                    if (key.isValid() && key.isWritable()) {
                        write(buffer, key, selector,lastMessage);

                    }

                    iter.remove();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("end = " + Thread.currentThread());
    }

    private void write(ByteBuffer buffer, SelectionKey key, Selector selector,Map<SelectionKey, String> lastMessage) throws IOException {

        try {

            SocketChannel client = (SocketChannel) key.channel();


       //     String respone = lastMessage.get(key);

 //           System.out.println(respone);



            //buffer.flip();
            client.write(ByteBuffer.wrap(lastMessage.get(key).getBytes()));
           // buffer.clear();


            //buffer.put(new byte[64]);
            //System.out.println(new String(buffer.array()).trim());

            client.register(selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            key.cancel();
            buffer.clear();
        }
    }

    private void read(ByteBuffer buffer, SelectionKey key, Selector selector, Map<SelectionKey, String> lastMessage) {

     //   buffer.put(new byte[64]);

      //  System.out.println(Arrays.toString(buffer.array()));

        buffer.flip();
        buffer.clear();

        try {

            SocketChannel client = (SocketChannel) key.channel();
            if (client.read(buffer) == -1) {
                buffer.clear();
                key.cancel();
            }


            String responce;

            responce = new String(Arrays.copyOfRange(buffer.array(),0, buffer.position()));

          //  ByteBuffer byteBuffer = ByteBuffer.wrap(Arrays.copyOf(buffer.array(),buffer.position()));

            lastMessage.put(key,responce);

            client.register(selector, SelectionKey.OP_WRITE);

        } catch (IOException e) {
            buffer.clear();
            key.cancel();

        }
    }

    private void register(Selector selector, ServerSocketChannel serverSocket)
            throws IOException {

        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);

//        client.write(ByteBuffer.wrap(questionService.getRandomQuestion(1).get(0).getQuestion().getBytes()));

        client.register(selector, SelectionKey.OP_READ);

    }


}