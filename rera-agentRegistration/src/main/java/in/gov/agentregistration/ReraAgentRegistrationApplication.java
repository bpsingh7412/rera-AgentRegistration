package in.gov.agentregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import in.gov.agentregistration.security.AuthSecurity;

@SpringBootApplication
public class ReraAgentRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReraAgentRegistrationApplication.class, args);
		System.out.println("Agent Registration server started");
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public FilterRegistrationBean<AuthSecurity> dawsonApiFilter() {
		FilterRegistrationBean<AuthSecurity> registration = new FilterRegistrationBean<AuthSecurity>();
		registration.setFilter(new AuthSecurity());
		// In case you want the filter to apply to specific URL patterns only
		registration.addUrlPatterns("/agent_reg/secure/*");
		return registration;
	}

}
