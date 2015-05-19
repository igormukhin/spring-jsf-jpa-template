package de.wirthedv.appname.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties security;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //.sessionManagement()
            .headers()
                .cacheControl() // ? disables browser cache
                //.frameOptions()
            .and().csrf()
                .disable()
            .formLogin()
                .loginPage("/public/login.jsf")
                .loginProcessingUrl("/login")
                .failureUrl("/public/login.jsf?event=loginFailure")
                .defaultSuccessUrl("/", true) // optional
            .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/public/login.jsf?event=logout")
            .and().authorizeRequests()
                .anyRequest().fullyAuthenticated();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(this.bCryptPasswordEncoder());
        
        auth.inMemoryAuthentication()
            .withUser("admin").password("admin").roles("ADMIN", "USER")
            .and().withUser("user").password("user").roles("USER");
    }

}
