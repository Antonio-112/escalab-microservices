package com.anto.microzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.anto.microzuul.filters.PostFilter;
import com.anto.microzuul.filters.PreFilter;
import com.anto.microzuul.filters.SecondPreFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class MicrozuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrozuulApplication.class, args);
	}
	
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	
	@Bean
	public SecondPreFilter secondPreFilter() {
		return new SecondPreFilter(); 
	}
	

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

}
