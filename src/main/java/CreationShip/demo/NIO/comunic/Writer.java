package CreationShip.demo.NIO.comunic;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Writer {

    private ByteBuffer buffer;
    private SelectionKey key;
    private Selector selector;
    private String responce;


    private Boolean mode = true;

    public Writer(ByteBuffer buffer, SelectionKey key, Selector selector){

        this.buffer = buffer;
        this.key = key;
        this.selector = selector;
    }

    public void enableReadMode(Boolean mode) {
        this.mode = mode;
    }

    public void write(String msg){

        try {

            SocketChannel client = (SocketChannel) key.channel();


            client.write(ByteBuffer.wrap(msg.getBytes()));

            if (mode)
            client.register(selector, SelectionKey.OP_READ);
            else  client.register(selector,SelectionKey.OP_WRITE);


        } catch (IOException e) {
            key.cancel();
            buffer.clear();
        }

    };

}
