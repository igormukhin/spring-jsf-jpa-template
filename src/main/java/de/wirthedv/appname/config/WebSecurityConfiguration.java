package de.wirthedv.appname.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import de.wirthedv.appname.AppProfiles;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@ConditionalOnWebApplication
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    public static final String PREAUTH_USER_HEADER = "Preauth-User";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private Environment env;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // allow request header auth by username for testing
        if (env.acceptsProfiles(AppProfiles.TEST)) {
            RequestHeaderAuthenticationFilter preAuthFilter = new RequestHeaderAuthenticationFilter();
            preAuthFilter.setPrincipalRequestHeader(PREAUTH_USER_HEADER);
            preAuthFilter.setExceptionIfHeaderMissing(false);
            preAuthFilter.setAuthenticationManager(authenticationManager());
            http.addFilter(preAuthFilter);
        }
        
        http
            //.sessionManagement()
            .headers()
                .cacheControl() // ? disables browser cache
                //.frameOptions()
            .and().csrf()
                .disable()
            .formLogin()
                .loginPage("/public/login.xhtml")
                .loginProcessingUrl("/login")
                .failureUrl("/public/login.xhtml?event=loginFailure")
                .defaultSuccessUrl("/", true) // optional
                .permitAll()
            .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/public/login.xhtml?event=logout")
                .permitAll()
            .and().authorizeRequests()
                .anyRequest().fullyAuthenticated();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // allow request header auth by username for testing
        if (env.acceptsProfiles(AppProfiles.TEST)) {
            PreAuthenticatedAuthenticationProvider preAuthenticatedProvider
                    = new PreAuthenticatedAuthenticationProvider();
            UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper
                    = new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(userDetailsService);
            preAuthenticatedProvider.setPreAuthenticatedUserDetailsService(wrapper);
            auth.authenticationProvider(preAuthenticatedProvider);
        }
            
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        auth.authenticationProvider(daoAuthenticationProvider);
        
    }
    
}
