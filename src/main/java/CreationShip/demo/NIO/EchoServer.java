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

public class EchoServer extends Thread {

   private ServerSocketChannel serverSocket = ServerSocketChannel.open();
   private static ThreadLocal<Integer> countConnection = new ThreadLocal<>();
   private int allocate;

    public static Integer getCountConnection() {
        return countConnection.get();
    }


    public EchoServer(int allocate, InetSocketAddress inetSocketAddress) throws IOException {

       this.allocate = allocate;

        if (inetSocketAddress == null)
            inetSocketAddress = new InetSocketAddress("localhost",8080);
        serverSocket.bind(inetSocketAddress);
        serverSocket.configureBlocking(false);


    }

    @Override
    public void run() {

        Selector selector;
        ByteBuffer buffer;
        countConnection.set(0);

        try {
            selector = Selector.open();
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            buffer = ByteBuffer.allocate(allocate);
        }catch (IOException e){
            System.out.println(e);
            return;
        }

        try {

            while (true) {

                    selector.select();
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = selectedKeys.iterator();

                    /*if(countConnection.get() > 3){
                        try {
                            System.out.println("sleep = " + Thread.currentThread());
                            Thread.sleep(30000);
                            System.out.println("wake up = " + Thread.currentThread());

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }*/

                    while (iter.hasNext()) {

                    SelectionKey key = iter.next();

                    if (key.isAcceptable()) {
                        countConnection.set(countConnection.get()+1);
                        System.out.println("connectioSize = " + countConnection.get() + " thread is" + Thread.currentThread());
                        register(selector, serverSocket);

                    }

                        if (key.isValid() && key.isReadable()) {
                        read(buffer, key, selector);
                        }

                        if (key.isValid() && key.isWritable()) {
                            write(buffer, key, selector);

                    }

                    iter.remove();
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
        System.out.println("end = " + Thread.currentThread());
    }

    private static  void write(ByteBuffer buffer, SelectionKey key, Selector selector) throws IOException {

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

    private void read(ByteBuffer buffer, SelectionKey key, Selector selector) {

        try {
            SocketChannel client = (SocketChannel) key.channel();

            if (client.read(buffer) == -1) {
                buffer.clear();
                key.cancel();
            }

            client.register(selector, SelectionKey.OP_WRITE);
        }catch (IOException e){
            buffer.clear();
            key.cancel();

        }
    }

    private void register(Selector selector, ServerSocketChannel serverSocket)
            throws IOException {

        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);

    }


}