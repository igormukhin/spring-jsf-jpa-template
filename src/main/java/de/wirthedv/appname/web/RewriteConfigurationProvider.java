package de.wirthedv.appname.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Response;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

/**
 * This is the configuration of OCPsoft Rewrite.
 * 
 * This class is set as the main configuration provider in:
 * src/main/resources/META-INF/services/org.ocpsoft.rewrite.config.ConfigurationProvider
 * 
 * This is why it doesn't need @RewriteConfiguration annotation
 * 
 * @author Igor Mukhin
 */
public class RewriteConfigurationProvider extends HttpConfigurationProvider {
    
    @Override
    public int priority() {
        return 10;
    }

    @Override
    public Configuration getConfiguration(final ServletContext context) {
        return ConfigurationBuilder.begin()
                // welcome page
                .addRule(Join.path("/").to("/index.xhtml"))
                
                // sends 403 for all *.jsf requests
                .addRule()
                      .when(Direction.isInbound().and(Path.matches("{path}.jsf")))
                      .perform(Response.setStatus(HttpServletResponse.SC_FORBIDDEN).and(Response.complete()))
                      .where("path").matches(".*")
                
                // rewrite *.jsf to *.xhtml
//                .addRule()
//                    .when(Direction.isInbound().and(Path.matches("{path}/{name}.jsf")))
//                    .perform(Forward.to("{path}/{name}.xhtml"))
//                    .where("path").matches(".*")
                    
                ;

    }
    
}
