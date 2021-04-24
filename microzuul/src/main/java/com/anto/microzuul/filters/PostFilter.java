package com.anto.microzuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PostFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PostFilter.class);
	private static final String FILTERTYPE = "post";
	private static final Integer FILTERORDER = 1;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext content = RequestContext.getCurrentContext();
		HttpServletRequest request = content.getRequest();
		
		long inicio = (long) request.getAttribute("time");
		long finala = (long) System.currentTimeMillis();
		/**
		 * Se registrara el tiempo total que tardo la respuesta
		 */
		log.info("Tiempo de respuesta en milisegundos: {} ", finala-inicio );
		
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
