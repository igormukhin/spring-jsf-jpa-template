package de.wirthedv.appname.web;

import de.wirthedv.bone.spring.RequestScopedComponent;

@RequestScopedComponent("loginBean")
public class LoginBean {
    
    private static final String EVENT_LOGOUT = "logout";

    private static final String EVENT_LOGIN_FAILURE = "loginFailure";
    
    private String event;
    
    private String errorMessage;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }

    // TODO: localize messages
    public void init() {
        if (EVENT_LOGOUT.equals(event)) {
            errorMessage = "You are logged out.";
        } else if (EVENT_LOGIN_FAILURE.equals(event)) {
            // TODO: handle different login failure reasons (should be saved in the session by Spring Security)
            errorMessage = "Invalid username or password.";
        }
    }
    
}
