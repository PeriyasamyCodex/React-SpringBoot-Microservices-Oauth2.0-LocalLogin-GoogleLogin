package com.jtp.eurekaserver.jtpeurekaservermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class JTPEurekaServerManager {

	public static void main(String[] args) {
		SpringApplication.run(JTPEurekaServerManager.class, args);
	}

}
