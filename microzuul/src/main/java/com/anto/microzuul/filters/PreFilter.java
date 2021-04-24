package com.anto.microzuul.filters;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PreFilter.class);
	private static final String FILTERTYPE = "pre";
	private static final Integer FILTERORDER = 1;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		final RequestContext content = RequestContext.getCurrentContext();

		final HttpServletRequest request = content.getRequest();

		request.setAttribute("time", System.currentTimeMillis());
		log.info("IMPORTANTE: Origin Reponse Headers = {}", content.getOriginResponseHeaders());
		try {
			log.info("IMPORTANTE: User principal name = {} ", request.getUserPrincipal().getName());
		} catch (Exception e) {
		}			
		log.info("IMPORTANTE: HTTP Protocol = {}", request.getProtocol());

		List<String> headers = new ArrayList<String>();

		request.getHeaderNames().asIterator().forEachRemaining(str -> headers.add(str));

		if (!request.getRemoteAddr().equals("0:0:0:0:0:0:0:1") || !headers.contains("requiredheader")
		) {

			content.setResponseStatusCode(400);
			content.setResponseBody("Acceso denegado");
			content.setSendZuulResponse(false);

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
