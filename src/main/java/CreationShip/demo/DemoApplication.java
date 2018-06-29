package CreationShip.demo;

import CreationShip.demo.IO.Acceptor;
import CreationShip.demo.NIO.Handler;
import CreationShip.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	//@Autowired
	//Acceptor acceptor;

	@Autowired
	Handler handler;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {

		handler.startServer();
		//Acceptor.PORT = 8080;

		//acceptor.run();

	}
}
