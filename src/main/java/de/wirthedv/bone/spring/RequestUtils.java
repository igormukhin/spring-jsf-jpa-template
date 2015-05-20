package de.wirthedv.bone.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.UrlPathHelper;

public class RequestUtils {

    private static UrlPathHelper pathHelper = new UrlPathHelper();

    public static String getOriginatingRequestPath(HttpServletRequest request) {
        String uri = pathHelper.getOriginatingRequestUri(request);
        String contextPath = pathHelper.getOriginatingContextPath(request);
        return uri.substring(contextPath.length());
    }    
    
    public static String getRequestPath(HttpServletRequest request) {
        String uri = pathHelper.getRequestUri(request);
        String contextPath = pathHelper.getContextPath(request);
        return uri.substring(contextPath.length());
    }    

}
