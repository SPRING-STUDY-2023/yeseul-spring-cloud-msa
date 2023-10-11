package com.example.apigatewayservice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    @Override
    public GatewayFilter apply(Config config) {
        // Custom Pre filter
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom Pre filter: request id = {}", request.getId());

            // Custom Post filter
            return chain.filter(exchange)
                    .then(Mono.fromRunnable( //Mono라는 객체는 WebFlux 라고 해서 스프링5에 추가되어있는 기능 -> 비동기 방식의 서버를 지원할때 단일값 전달할 때 사용
                            () -> log.info("Custom Post filter: response code = {}", response.getStatusCode())));
        };
    }

    public CustomFilter() {
        super(Config.class);
    } // 생성자도 없으면 에러 발생

    public static class Config {
        // Put the configuration properties
    } // 이거 없으면 에러 발생

}