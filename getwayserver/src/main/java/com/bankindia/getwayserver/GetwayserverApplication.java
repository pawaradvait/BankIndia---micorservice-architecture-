package com.bankindia.getwayserver;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import reactor.core.publisher.Mono;

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
//  .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter()).setKeyResolver(userKeyResolver()))
 
 )

 .uri("lb://LOAN")).build();
	}


		@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
	}


	// @Bean
	// public RedisRateLimiter redisRateLimiter() {
	// 	return new RedisRateLimiter(1, 1, 1);
	// }

	// @Bean
	// KeyResolver userKeyResolver() {
	// 	return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
	// 			.defaultIfEmpty("anonymous");
	// }

}
