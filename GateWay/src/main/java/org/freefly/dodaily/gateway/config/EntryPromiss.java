package org.freefly.dodaily.gateway.config;

import org.freefly.dodaily.gateway.client.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(-1)
//@Component
public class EntryPromiss implements GlobalFilter {
    private final String cookieName = "dodaily_user";

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        MultiValueMap<String, HttpCookie> cookies = exchange.getRequest().getCookies();
        HttpCookie cookie = cookies.getFirst(cookieName);
        if (cookie == null) {
            System.out.println("The cookie is null!");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        int flag = userServiceClient.validate(cookie);
        if (flag == 1) {
            System.out.println("GateWay GlobalFilter Success!");
            return chain.filter(exchange);
        } else {
            System.out.println("GateWay GlobalFilter Failed!");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}
