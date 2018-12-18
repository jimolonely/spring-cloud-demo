package com.jimo.router;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jimo
 * @date 18-12-18 上午11:04
 */
@Component
public class SimpleFilter extends ZuulFilter {
	private static Logger logger = LoggerFactory.getLogger(SimpleFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL()));

		return null;
	}
}