package com.example;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @Todo, tfe.jar library
 *
 * Created by gsenff on 12-8-15.
 */
public class RequestUtils {



    public enum RequestHeader {
        Accept("Accept"),
        AcceptCharset("Accept-Charset"),
        AcceptEncoding("Accept-Encoding"),
        AcceptLanguage("Accept-Language"),
        Authorization("Authorization"),
        CacheControl("Cache-Control"),
        Connection("Connection"),
        Cookie("Cookie"),
        ContentLength("Content-Length"),
        ContentType("Content-Type"),
        Date("Date"),
        Expect("Expect"),
        From("From"),
        Host("Host"),
        IfMatch("If-Match"),
        IfModifiedSince("If-Modified-Since"),
        IfNoneMatch("If-None-Match"),
        IfRange("If-Range"),
        IfUnmodifiedSince("If-Unmodified-Since"),
        MaxForwards("Max-Forwards"),
        Pragma("Pragma"),
        ProxyAuthorization("Proxy-Authorization"),
        Range("Range"),
        Referer("Referer"),
        TE("TE"),
        Upgrade("Upgrade"),
        UserAgent("User-Agent"),
        Via("Via"),
        Warning("Warning"),
        XRequestedWith("X-Requested-With"),
        XForwardedFor("X-Forwarded-For");

        private String value;

        RequestHeader(String value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return this.value;
        }

        public static RequestHeader fromString(String value) {
            for(RequestHeader responseHeader : RequestHeader.values()) {
                if(responseHeader.value.equalsIgnoreCase(value)) {
                    return responseHeader;
                }
            }
            return null;
        }

        public static Set<RequestHeader> fromRequest(HttpServletRequest request) {
            Set<RequestHeader> requestHeaders = new HashSet<RequestHeader>();

            Enumeration<?> requestHeaderNames = request.getHeaderNames();
            while(requestHeaderNames.hasMoreElements()) {
                String requestHeaderName = (String)requestHeaderNames.nextElement();

                RequestHeader requestHeader = RequestHeader.fromString(requestHeaderName);

                if(requestHeader != null) {
                    requestHeaders.add(requestHeader);
                }
            }

            return requestHeaders;
        }
    }

}
