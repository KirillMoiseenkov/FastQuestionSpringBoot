package CreationShip.demo.NIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class Reader {

    private ByteBuffer buffer;
    private SelectionKey key;
    private Selector selector;
    private String responce;


    private Boolean mode = true;

    public Reader(ByteBuffer buffer, SelectionKey key, Selector selector){

        this.buffer = buffer;
        this.key = key;
        this.selector = selector;
    }


    public void enableWriteMode(Boolean mode) {
        this.mode = mode;
    }


    public String getResponce() {
        return responce;
    }

    public  String read(){

        buffer.flip();
        buffer.clear();

      try {

            SocketChannel client = (SocketChannel) key.channel();
            if (client.read(buffer) == -1) {
                buffer.clear();
                key.cancel();
            }

            responce = new String(Arrays.copyOfRange(buffer.array(),0, buffer.position()));

            if (mode) client.register(selector, SelectionKey.OP_WRITE);
            else client.register(selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            buffer.clear();
            key.cancel();

        }

        return responce;
    }

}
