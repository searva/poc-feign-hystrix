package searva.feign.service1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="server-2", fallback=SimpleClient.HystrixClientFallback.class)
public interface SimpleClient {
	@GetMapping( path="/api/hello")
	public String hello();
	
	@Component
	static class HystrixClientFallback implements SimpleClient {
	    @Override
	    public String hello() {
	        return "--timeout--";
	    }
	}

}
