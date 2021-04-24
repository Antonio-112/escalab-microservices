package com.anto.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
	
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/scope","/user").access("#oauth2.hasScope('write')")
		.anyRequest().authenticated()
		.and().csrf().disable();
		
		http.exceptionHandling().authenticationEntryPoint(new AuthExceptionEntryPoint()).and()
		.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
	}
}
