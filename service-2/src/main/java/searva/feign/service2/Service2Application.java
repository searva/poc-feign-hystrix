package searva.feign.service2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Service2Application {

	public static void main(String[] args) {
		SpringApplication.run(Service2Application.class, args);
	}
	
	@RestController
	public static class SimpleController {
		
		@GetMapping( path="/api/hello")
		public String hello(){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Sergio!";
		}
		
	}
}
