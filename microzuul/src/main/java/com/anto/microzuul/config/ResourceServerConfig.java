package com.anto.microzuul.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/uaa/oauth/**")
		.permitAll()
		.antMatchers(HttpMethod.GET, "/catalogo/productos")
		.hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.GET, "/catalogo/producto/{idProducto}"
								   , "/compra/producto/{idProducto}")
		.hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.POST ,"/catalogo/producto"
									 ,"/compra/carrito/{idProducto}")
		.hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE 	,"/catalogo/producto/{idProducto}"
										,"/compra/carrito/{idCarrito}")
		.hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		//************************ALL Configuration***********************//
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("GET","HEADER","OPTION"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
		corsConfig.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", corsConfig);
		//************************ALL Configuration***********************//
		
		CorsConfiguration secondCorsConfig = new CorsConfiguration();
		secondCorsConfig.setAllowedOrigins(Arrays.asList("19.102.0.1"));
		secondCorsConfig.setAllowedMethods(Arrays.asList("POST","PUT","DELETE","PATCH"));
		secondCorsConfig.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
		secondCorsConfig.setAllowCredentials(true);
		source.registerCorsConfiguration("/compra/**", secondCorsConfig);
		source.registerCorsConfiguration("/catalogo/**", secondCorsConfig);
		
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
