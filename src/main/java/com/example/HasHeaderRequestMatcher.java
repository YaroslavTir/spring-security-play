package com.example;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @author k.kondratov on 3/3/2016.
 */
public class HasHeaderRequestMatcher implements RequestMatcher {

    private String headerName;

    public HasHeaderRequestMatcher(String headerName) {
        this.headerName = headerName;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return request.getHeader(this.headerName) != null;
    }
}
