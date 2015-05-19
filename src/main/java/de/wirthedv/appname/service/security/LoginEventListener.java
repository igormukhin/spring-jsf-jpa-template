package de.wirthedv.appname.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginEventListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginEventListener.class);
    
    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
        logger.info("User {} logged in", userDetails.getUsername());
    }
    
}
