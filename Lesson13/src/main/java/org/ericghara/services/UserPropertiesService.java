package org.ericghara.services;

import org.ericghara.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserPropertiesService {

    private final UserDetailsManager manager;

    @Autowired
    public UserPropertiesService(UserDetailsManager manager) {
        this.manager = manager;
    }

    public String getAuths(Authentication authentication) {
        StringBuilder sb = new StringBuilder();
        authentication.getAuthorities().forEach( (a) -> sb.append(a).append(", ") );
        sb.setLength(sb.length()-2);
        String name = authentication.getName();
        return String.format("Hello, %s! You have these privileges: %s.", name, sb);
    }

    public String getUsername(Authentication authentication) {
        return String.format("Username: %s.", authentication.getName() );
    }

    public void addUser(UserModel user) {
        var username = user.getUsername();
        var password = user.getPassword();
        var auths = user.getAuths();

        manager.createUser(User.withUsername(username)
                               .password(password)
                               .authorities(auths)
                               .build() );

    }
}
