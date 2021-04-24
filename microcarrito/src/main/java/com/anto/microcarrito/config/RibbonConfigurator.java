package com.anto.microcarrito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IRule;

@Configuration
public class RibbonConfigurator {
    
    @Bean
    public IRule ribbonRule() {
    	IRule rule = new AvailabilityFilteringRule();
    	return rule;
    }
}
