package CreationShip.demo;

import CreationShip.demo.IO.Acceptor;
import CreationShip.demo.IO.AcceptorNIO;
import CreationShip.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.Executor;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	MessageService messageService;

	@Autowired
	Acceptor acceptor;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {

		Acceptor.PORT = 8080;

		acceptor.run();

	}
}
