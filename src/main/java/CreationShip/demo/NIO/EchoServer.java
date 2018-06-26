package CreationShip.demo.NIO;

import CreationShip.demo.NIO.connector.AnswerQuestionConnector;
import CreationShip.demo.NIO.connector.IConnector;
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
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class EchoServer extends Thread {

    private static ThreadLocal<Integer> countConnection = new ThreadLocal<>();
    private static InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);




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

    public EchoServer() throws IOException {

        this.allocate = 64;

        serverSocket.bind(inetSocketAddress);
        serverSocket.configureBlocking(false);


    }

    public static Integer getCountConnection() {
        return countConnection.get();
    }

    public void setAllocate(int allocate) {
        this.allocate = allocate;
    }

    @Override
    public void run() {

        Selector selector;
        ByteBuffer buffer;
        countConnection.set(0);
        Map<SelectionKey, String> lastMessage = new HashMap<>();
        Map<SelectionKey, AnswerQuestionConnector> connectorMap = new HashMap<>();

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

//                Map<SelectionKey,IConnector> ConnectorMap = new HashMap<>();

                while (iter.hasNext()) {

                    Reader reader = null;
                    Writer writer = null;

                    SelectionKey key = iter.next();


                    if (key.isAcceptable()) {

                        countConnection.set(countConnection.get() + 1);
                        System.out.println("connectioSize = " + countConnection.get() + " thread is" + Thread.currentThread());
                        register(selector, serverSocket);

                        System.out.println("new Connector");


                    }

                    if (key.isValid() && key.isReadable()) {


                        reader = new Reader(buffer, key, selector);
                        reader.enableWriteMode(true);

                        if (!connectorMap.containsKey(key)) {
                            connectorMap.put(key,
                                    new AnswerQuestionConnector(messageService, questionService, null, null));
                        }

                        AnswerQuestionConnector answerQuestionConnector = connectorMap.get(key);

                        answerQuestionConnector.setReader(reader);

                        answerQuestionConnector.read();


                        //  read(buffer, key, selector, lastMessage, reader);
                        //read(answerQuestionConnector);
                    }

                    if (key.isValid() && key.isWritable()) {

                        writer = new Writer(buffer, key, selector);
                        writer.enableReadMode(true);


                        AnswerQuestionConnector answerQuestionConnector = connectorMap.get(key);

                        answerQuestionConnector.setWriter(writer);

                        answerQuestionConnector.write();


                        // write(buffer, key, selector, lastMessage, writer);

                    }
                    iter.remove();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("end = " + Thread.currentThread());
    }

    private void write(ByteBuffer buffer, SelectionKey key, Selector selector,
                       Map<SelectionKey, String> lastMessage, Writer writer) throws IOException {

        writer.write(lastMessage.get(key));


    }

    private void write(AnswerQuestionConnector answerQuestionConnector) throws IOException {

        answerQuestionConnector.write("123");

    }

    private void read(ByteBuffer buffer, SelectionKey key, Selector selector, Map<SelectionKey, String> lastMessage, Reader reader) {
        //   private void read(AnswerQuestionConnector answerQuestionConnector) {

        reader = new Reader(buffer, key, selector);

        AnswerQuestionConnector answerQuestionConnector =
                new AnswerQuestionConnector(messageService, questionService, null, null);


        reader.enableWriteMode(true);

        answerQuestionConnector.setReader(reader);
        //lastMessage.put(key,reader.read());
        lastMessage.put(key, answerQuestionConnector.read());

        //answerQuestionConnector.read();

    }

    private void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {

        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);

    }


}