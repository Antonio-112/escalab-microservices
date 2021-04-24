package com.anto.microzuul.filters;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class SecondPreFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(SecondPreFilter.class);
	private static final String FILTERTYPE = "pre";
	private static final Integer FILTERORDER = 2;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext content = RequestContext.getCurrentContext();
		HttpServletRequest request = content.getRequest();
		
		String path = request.getServletPath();
		
		log.info("Request type: {}", request.getMethod());
		log.info("Request URL: {}", request.getRequestURL().toString());
		if (path.startsWith("/catalogo")) {
			log.info("Request Service Name: producto.service");
		}else if (path.startsWith("/compra")) {
			log.info("Request Service Name: carrito.service");
		}else if (path.startsWith("/uaa")) {
			log.info("Request Service Name: micro.uaa");
		}
		
		
		return null;
	}

	@Override
	public String filterType() {
		return FILTERTYPE;
	}

	@Override
	public int filterOrder() {
		return FILTERORDER;
	}

}
