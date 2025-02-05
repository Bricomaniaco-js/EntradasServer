package com.bricomaniaco.ticketappserver.components;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.HandlerInterceptor.*;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        LOGGER.info("Incoming request from IP: {}, Method: {}, URI: {}", ipAddress, method, requestURI);
        return true;
    }

    @Override
    public void postHandle(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Implement postHandle logic if needed
    }

    @Override
    public void afterCompletion(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        int status = response.getStatus();
        LOGGER.info("Response Status: {}", status);
        if(status >= 400) {
            ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
            LOGGER.error("Error Request: {}", getRequestBody(wrappedRequest));

        }
    }

    private String getResponseBody(ContentCachingResponseWrapper response) {
        byte[] content = response.getContentAsByteArray();
        try {
            return new String(content, response.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            return "[unknown]";
        }
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {
        byte[] content = request.getContentAsByteArray();
        try {
            return new String(content, request.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            return "[unknown]";
        }
    }
}