package de.wirthedv.appname.config;

import java.net.URL;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Value("${app.url:?}")
    private URL appUrl;
    
    @Value("${app.locale:de_DE}")
    private Locale defaultLocale;

    @Value("${app.timezone:Europe/Berlin}")
    private TimeZone defaultTimeZone;
    
    public URL getAppUrl() {
        return appUrl;
    }

    public Locale getDefaultLocale() {
        return defaultLocale;
    }

    public TimeZone getDefaultTimeZone() {
        return defaultTimeZone;
    }
    
}
