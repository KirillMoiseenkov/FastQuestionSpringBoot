package CreationShip.demo.NIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

public class EchoClient {

    private static SocketChannel client;
    private static ByteBuffer buffer;
    private static EchoClient instance;
    private static CharsetEncoder encoder = Charset.forName("US-ASCII").newEncoder();

    public static EchoClient start() {
        if (instance == null)
            instance = new EchoClient();

        return instance;
    }

    public static void stop() throws IOException {
        client.close();
        buffer = null;
    }

    private EchoClient() {
        try {
            client = SocketChannel.open(new InetSocketAddress("localhost", 8080));
            buffer = ByteBuffer.allocate(64);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) throws IOException {

        client.write(ByteBuffer.wrap(msg.getBytes()));

        buffer.flip();
        buffer.clear();

        client.read(buffer);

        String responce = new String(Arrays.copyOfRange(buffer.array(),0, buffer.position()));

        System.out.println(responce);

    }

    public static String bb_to_str(ByteBuffer buffer){
        byte[] bytes;
        if(buffer.hasArray()) {
            bytes = buffer.array();
        } else {
            bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
        }
        return new String(bytes);
    }

    public static void main(String[] args) throws IOException {

        EchoClient echoClient = EchoClient.start();


        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        while (true){

            System.out.println("enter message");

           echoClient.sendMessage(stdIn.readLine());



        }


    }

}