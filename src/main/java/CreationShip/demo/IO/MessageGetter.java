package CreationShip.demo.IO;

import org.springframework.context.annotation.Description;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public class MessageGetter {

   private BufferedReader br;
   private String cache;

    MessageGetter(BufferedReader br){
        this.br = br;
    }

   public String getMessage() throws IOException {
       return (cache = br.readLine());
    }

    public String getCache(){
        return cache;
    }

}
