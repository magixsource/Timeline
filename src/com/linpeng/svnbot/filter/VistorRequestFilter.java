package com.linpeng.svnbot.filter;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * VistorRequestFilter
 * Simple Debug filter use to check request
 *
 * @author linpeng
 * @version 1.0
 */
public class VistorRequestFilter implements Filter {
    private static final DateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(VistorRequestFilter.class);

    @Override
    public void destroy() {
        if (logger.isDebugEnabled()) {
            logger.debug("method destroy is calling..");
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        // when,who visit what
        HttpServletRequest reqest = (HttpServletRequest) req;
        if (logger.isDebugEnabled()) {
            logger.debug("[" + sdf.format(new Date()) + "],"
                    + reqest.getRemoteAddr() + "[VISIT] "
                    + reqest.getRequestURI());
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("method init is calling..");
        }
    }

}
