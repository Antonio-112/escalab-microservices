package com.anto.microcarrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.anto.microcarrito.config.RibbonConfigurator;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "restTemplate", configuration = {RibbonConfigurator.class})
@EnableCircuitBreaker
@EnableResourceServer
public class MicrocarritoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrocarritoApplication.class, args);
	}

}
