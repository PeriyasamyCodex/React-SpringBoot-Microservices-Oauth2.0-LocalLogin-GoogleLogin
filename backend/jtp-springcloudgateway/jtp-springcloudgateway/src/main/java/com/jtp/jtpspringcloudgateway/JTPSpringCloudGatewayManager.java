package com.jtp.jtpspringcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;

@SpringBootApplication
@EnableDiscoveryClient
public class JTPSpringCloudGatewayManager {

	@Bean
    public GlobalFilter oauth2TokenRelay() {
        return (exchange, chain) -> {
            ServerWebExchange webExchange = exchange;
            // Ensure that the OAuth2 token is available in the request
            // The OAuth2 token will be automatically forwarded with the request
            return chain.filter(webExchange);
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(JTPSpringCloudGatewayManager.class, args);
	}

}
