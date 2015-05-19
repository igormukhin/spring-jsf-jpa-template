package de.wirthedv.appname.service.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private InMemoryUserDetailsManager sampleService;
    
    public UserDetailsServiceImpl() {
        List<UserDetails> users = new ArrayList<>();
        users.add(new User("admin", "admin", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
        users.add(new User("user", "user", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));
        sampleService = new InMemoryUserDetailsManager(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return sampleService.loadUserByUsername(username);
    }

}
