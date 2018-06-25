package searva.feign.service1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import feign.hystrix.SetterFactory;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
public class Service1Application {

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}
	
	@Bean
	@ConditionalOnProperty(name = "feign.hystrix.enabled", matchIfMissing= false) 
	public SetterFactory setterFactory() {
	  return (target, method) -> HystrixCommand.Setter
	        .withGroupKey(HystrixCommandGroupKey.Factory.asKey( target.name()))
	        .andCommandKey(HystrixCommandKey.Factory.asKey( target.name()));
	}
	
	
	@RestController
	public static class SimpleController {
		
		@Autowired
		SimpleClient client;
		
		@GetMapping( path="/api/hello")
		public String hello(){
			return "hola " + client.hello();
		}
		
	}
}
