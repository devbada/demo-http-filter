package com.example.demo.logging.filter;

import com.example.demo.logging.domain.Http;
import com.example.demo.logging.wrapper.RequestWrapper;
import com.example.demo.logging.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @since       2021.01.26
 * @author      minam
 * @description http logging filter
 **********************************************************************************************************************/
@Component
@Slf4j
//@Profile("prod")
@RequiredArgsConstructor
public class HttpLoggingFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final RequestWrapper requestWrapper  = new RequestWrapper(request);
        final ResponseWrapper responseWrapper = new ResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        ObjectMapper objectMapper = new ObjectMapper();

        log.info(objectMapper.writeValueAsString(getHttp(requestWrapper, responseWrapper)));
        responseWrapper.copyBodyToResponse();
    }

    private Http getHttp(RequestWrapper requestWrapper, ResponseWrapper responseWrapper) {
        return Http.builder()
                .request(Http.Request.builder()
                        .method(requestWrapper.getMethod())
                        .url(requestWrapper.getUrl())
                        .headers(requestWrapper.getHeaders())
                        .body(requestWrapper.getBody())
                        .build())
                .response(Http.Response.builder()
                        .status(responseWrapper.getStatusCode())
                        .headers(responseWrapper.getHeaders())
                        .body(responseWrapper.getBody())
                        .build())
                .build();
    }
}