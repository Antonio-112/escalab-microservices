package com.anto.microcarrito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;

@Configuration
public class RibbonConfigurator {
    
    @Bean
    public IRule ribbonRule() {
    	return new RoundRobinCustomizedRule();
    }
    @Bean
    public IPing ribbonPing() {
        return new PingUrl();
    }
    
    
}
