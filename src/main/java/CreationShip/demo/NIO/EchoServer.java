package CreationShip.demo.NIO;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoServer {

    private static final String POISON_PILL = "POISON_PILL";

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", 8080));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int count = 0;

        while (true) {



            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {



                SelectionKey key = iter.next();



                if (key.isAcceptable()) {
                    count++;
                    System.out.println("count = "  + count);
                    register(selector, serverSocket);


                }

                if (key.isValid() && key.isReadable()) {

                    answerWithEcho(buffer, key, selector);

                }

                if(key.isValid() && key.isWritable()){
                        isWriteble(buffer, key, selector);

                }

                iter.remove();
            }
        }
    }

    private static  void isWriteble(ByteBuffer buffer, SelectionKey key, Selector selector) throws IOException {

        try {


            SocketChannel client = (SocketChannel) key.channel();
            buffer.flip();
            client.write(buffer);
            buffer.clear();
            buffer.put("poom".getBytes());
            client.register(selector, SelectionKey.OP_READ);
        }catch (IOException e){
            key.cancel();
            buffer.clear();
        }
    }

    private static void answerWithEcho(ByteBuffer buffer, SelectionKey key, Selector selector) {

        try {
            SocketChannel client = (SocketChannel) key.channel();

            if (client.read(buffer) == -1) {
                buffer.clear();
                key.cancel();
            }

            if (new String(buffer.array()).trim().equals(POISON_PILL)) {
                client.close();
                System.out.println("Not accepting client messages anymore");
            }

            client.register(selector, SelectionKey.OP_WRITE);
        }catch (IOException e){
            buffer.clear();
            key.cancel();
        }
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket)
            throws IOException {

        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);

    }


}