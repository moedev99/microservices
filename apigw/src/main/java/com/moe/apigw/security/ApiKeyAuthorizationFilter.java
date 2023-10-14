package com.moe.apigw.security;


import lombok.AllArgsConstructor;
import org.apache.http.protocol.HttpCoreContext;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class ApiKeyAuthorizationFilter implements GlobalFilter, Ordered {

    final FakeIsAuthorizedCheckerImp fakeIsAuthorizedCheckerImp;

//        the exchange provides access to the HTTP request and response and also exposes additional server-side processing related properties and features such as request attributes.
//        the chain is a contract to allow a WebFilter to delegate to the next in the chain.
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

        List<String> apiKey = exchange.getRequest().getHeaders().get("apiKey");
        if (!route.getId().equals("customer")
                || apiKey == null
                || !fakeIsAuthorizedCheckerImp.isAuthorized(apiKey.get(0), route.getId())
        ){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Not authorized");
        }

        System.out.println("ApiKeyAuthorizationFiler... checking keys");

//        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorized");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
