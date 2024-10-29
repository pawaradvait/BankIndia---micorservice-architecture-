package com.bankindia.getwayserver.filter;

import java.nio.file.DirectoryStream.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter{

    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    private FilterUlity filterUtility;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // TODO Auto-generated method stub
HttpHeaders headers = exchange.getRequest().getHeaders();
if(isCorrelationIdPresent(headers)){
    logger.debug("eazyBank-correlation-id found in RequestTraceFilter : {}",
    filterUtility.getCorrelationId(headers));

}else{
    String correlationID = generateCorrelationId();
    exchange = filterUtility.setCorrelationId(exchange, correlationID);
    logger.debug("eazyBank-correlation-id generated in RequestTraceFilter : {}", correlationID);
}
return chain.filter(exchange);
        
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        if (filterUtility.getCorrelationId(requestHeaders) != null) {
            return true;
        } else {
            return false;
        }
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

}
