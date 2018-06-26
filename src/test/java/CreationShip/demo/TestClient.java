package CreationShip.demo;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {
    public static void main(String[] args) throws IOException {

        Socket socket = null;



        try {


            socket = new Socket("localhost", 8080);

            PrintWriter out = null;

            out = new PrintWriter(socket.getOutputStream(), true);
            InputStream in = socket.getInputStream();

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));


            out.println("om");

            for (;;) {
                System.out.println(br.readLine());
                System.out.print("client: ");
                String userInput = stdIn.readLine();
                if ("q".equals(userInput)) {
                    break;
                }

                out.println(userInput);

            }

            String message;
            while ((message = br.readLine()) != null) {
                System.out.println(message);
            }

            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            socket.close();
        }
    }
}
