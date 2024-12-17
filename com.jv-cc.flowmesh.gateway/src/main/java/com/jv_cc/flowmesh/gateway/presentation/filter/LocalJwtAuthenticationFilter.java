package com.jv_cc.flowmesh.gateway.presentation.filter;

import com.jv_cc.flowmesh.gateway.application.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j(topic = "JwtAuthenticationFilter")
@RequiredArgsConstructor
@Component
public class LocalJwtAuthenticationFilter implements GlobalFilter, Ordered {
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(JwtUtil.JwtHeader.KEY_ACCESS_TOKEN);
        String uri = exchange.getRequest().getURI().getPath();
        log.info(uri);
        if (uri.startsWith("/api/auth")) {
            log.info("Pass the JWT Token Validate, URI: {}", uri);
            return chain.filter(exchange);
        }

        if (token != null && !jwtUtil.validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            log.error("JWT Token Validate Fail, Token: {}", token);
            return exchange.getResponse().setComplete();
        }

        String userId = jwtUtil.getUserIdFromToken(token);
        String userRole = jwtUtil.getUserRoleFromToken(token);

        exchange = exchange.mutate()
                .request(exchange.getRequest().mutate()
                        .header(JwtUtil.JwtHeader.KEY_USER_ID, userId)
                        .header(JwtUtil.JwtHeader.KEY_USER_ROLE, userRole)
                        .build()
                )
                .build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}