package com.bankindia.getwayserver;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GetwayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetwayserverApplication.class, args);
	}

	@Bean
	public RouteLocator getRouterLocator(RouteLocatorBuilder builder) {

 return builder.routes()
 .route(p -> p.path("/bankindia/account/**")
 .filters(f->f.rewritePath("/bankindia/account/(?<segment>.*)", "/${segment}")
 .addResponseHeader("X-RESPONSE-TIME", LocalDateTime.now().toString())
   .circuitBreaker(config->config.setName("accountcircuitbreaker").setFallbackUri("forward:/contactsupport"))
 
 )
 .uri("lb://ACCOUNTS"))
 
 
 .route(p -> p.path("/bankindia/loan/**")
 .filters(f->f.rewritePath("/bankindia/loan/(?<segment>.*)", "/${segment}")
 .addResponseHeader("X-RESPONSE-TIME", LocalDateTime.now().toString())
 .retry(retryconfig -> retryconfig.setRetries(3).setBackoff(Duration.ofMillis(100),Duration.ofMillis(10000), 2, true))
 )
 .uri("lb://LOAN")).build();
	}

}
