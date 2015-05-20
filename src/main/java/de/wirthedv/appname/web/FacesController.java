package de.wirthedv.appname.web;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import de.wirthedv.bone.spring.RequestUtils;

/**
 * Redirects *.xhtml requests to FacesServlet.
 *  
 * @author igor
 */
@Controller
@ConditionalOnWebApplication
public class FacesController {
    
    @Autowired
    private ServletContext servletContext;
    
    private RequestDispatcher facesRequestDispatcher;
    
    @RequestMapping("/**/*.xhtml")
    public void facesServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (facesRequestDispatcher == null) {
            facesRequestDispatcher = servletContext.getNamedDispatcher("FacesServlet");
        }
        
        String path = RequestUtils.getRequestPath(request);
        if (isFacesView(path) && !checkFacesViewExists(path)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        facesRequestDispatcher.include(request, response);
    }
    
    private boolean isFacesView(String path) {
        return !path.startsWith("/javax.faces.resource/");
    }

    private boolean checkFacesViewExists(String path) throws MalformedURLException {
        String viewPath = path.substring(0, path.lastIndexOf('.')) + ".xhtml";
        boolean exists = (servletContext.getResource(viewPath) != null);
        return exists;
    }

}
