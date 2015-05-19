package de.wirthedv.appname.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UrlPathHelper;

import de.wirthedv.bone.spring.AntPathMatcherUtils;
import de.wirthedv.bone.spring.RequestScopedComponent;

@RequestScopedComponent("navbarBean")
public class NavbarBean {
    
    @Autowired
    private HttpServletRequest request;
    
    private String requestPath; 
    
    private String activeMenuItem;
    
    public String getActiveMenuItem() {
        if (activeMenuItem == null) {
            activeMenuItem = resolveActiveMenuItem();
        }
        
        return activeMenuItem;
    }

    private String resolveActiveMenuItem() {
        if (pathMatches("/;/index.jsf")) {
            return "home";
        } else if (pathMatches("/cities/**")) {
            return "cities";
        }
        
        return "unknown";
    }
    
    private boolean pathMatches(String pathPattern) {
        String path = getRequestPath();
        return AntPathMatcherUtils.matches(pathPattern, path);
    }
    
    private String getRequestPath() {
        if (requestPath == null) {
            UrlPathHelper pathHelper = new UrlPathHelper();
            String uri = pathHelper.getOriginatingRequestUri(request);
            String contextPath = pathHelper.getOriginatingContextPath(request);
            requestPath = uri.substring(contextPath.length());
        }
        return requestPath;
    }    
}
