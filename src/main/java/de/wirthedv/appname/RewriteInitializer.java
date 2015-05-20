package de.wirthedv.appname;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.ocpsoft.rewrite.servlet.impl.RewriteServletContextListener;
import org.ocpsoft.rewrite.servlet.impl.RewriteServletRequestListener;

/**
 * This class does the same as the web-fragment.xml of Rewrite.
 * 
 * See file https://github.com/ocpsoft/rewrite/blob/c960607c4f3a4d099aea4d0a0dc1548b3e29feca/impl-servlet/src/main/resources/META-INF/web-fragment.xml
 * 
 * The is not public for it should not be picked up by Tomcat,
 * but we use it only in manual configuration of embedded Tomcat. 
 * 
 * @author Igor Mukhin
 */
class RewriteInitializer implements ServletContainerInitializer {
    
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        ctx.addListener(RewriteServletRequestListener.class);
        ctx.addListener(RewriteServletContextListener.class);
        
        Dynamic filter = ctx.addFilter("OCPsoft Rewrite Filter", RewriteFilter.class);
        filter.setAsyncSupported(true);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
    }

}
