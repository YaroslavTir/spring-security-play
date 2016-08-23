package com.example;


import org.springframework.http.HttpHeaders;
import org.springframework.security.web.util.matcher.*;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;

/**
 * Created by a.demkina on 19-8-2016.
 */
public class WebSecurityCheckingUtil {

    public static final String API_URL_ANT_PATH =  "/**";

    public static RequestMatcher allowedApiIpRequestMatcher(IpProvider ipProvider) {
        if (ipProvider.getAllowedIps().isEmpty()) {
            return matcher -> false; // not allowed if no ip addresses are set
        }

        OrRequestMatcher orRequestMatcher = new OrRequestMatcher(
                //TODO switch to collections instead of array maybe
                Arrays
                        .stream(ipProvider.getAllowedIps().toArray(new String[ipProvider.getAllowedIps().size()]))
                        .map((allowedIp) -> (RequestMatcher) request -> {
                            IpAddressMatcher matcher = new IpAddressMatcher(allowedIp);
                            String xForwardedForHeader = request.getHeader(RequestUtils.RequestHeader.XForwardedFor.toString());
                            return matcher.matches(request) || matcher.matches(xForwardedForHeader);
                        })
                        .collect(toList())
        );

//        return new AndRequestMatcher(orRequestMatcher, apiUrlAndHasNoAuthorizationHeader());
        return orRequestMatcher;
    }

    public static RequestMatcher apiUrlMatcher() {
        return new AntPathRequestMatcher(API_URL_ANT_PATH);
    }

    public static RequestMatcher hasAuthorizationHeader() {
        return new HasHeaderRequestMatcher(HttpHeaders.AUTHORIZATION);
    }

    private static RequestMatcher apiUrlAndHasNoAuthorizationHeader() {
        return new AndRequestMatcher(
                apiUrlMatcher(),
                new NegatedRequestMatcher(hasAuthorizationHeader()));
    }
}
