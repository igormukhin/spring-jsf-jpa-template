package de.wirthedv.appname.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfiguration {
    
    @Autowired
    private ApplicationConfiguration appConf;
    
    /**
     * TODO: consider replacing SessionLocaleResolver with something more sophisticated,
     * like CoockieResolver before user login, and SessionResolver after.
     * 
     * You can also add a {@link LocaleChangeInterceptor} or handle locale change somewhere else,
     * where you can also update timezone and so on.
     */
    @Bean
    public SessionLocaleResolver sessionLocaleResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(appConf.getDefaultLocale());
        resolver.setDefaultTimeZone(appConf.getDefaultTimeZone());
        return resolver;
    }
    
}
