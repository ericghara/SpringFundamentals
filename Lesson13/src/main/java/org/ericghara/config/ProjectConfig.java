package org.ericghara.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    /*
    UserDetails - represents the user
    UserDetailsService -> UserDetailsManager - contract that is used to retrieve the users and load them (file, db, webservice, socket etc)
    PasswordEncoder - hashes the authentic password and compares the hash of input passwords to authentic
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // deprecated but OK for testing
        // example alternative BCryptPasswordEncoder
    }

    @Bean
    public UserDetailsManager userDetailsService() {
        var manager = new InMemoryUserDetailsManager(); // user details manager extends UserDetailsService interface

        manager.createUser(User.withUsername("Roy")
                               .password("12345")
                               .authorities("ROLE_ADMIN", "write", "read")
                               .build());
        manager.createUser(User.withUsername("Rena")
                               .password("12345")
                               .authorities("ROLE_MANAGER","read")
                               .build() );
        manager.createUser(User.withUsername("Homer")
                               .password("12345")
                               .authorities("ROLE_USER","read")
                               .build() );
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable(); // Use for testing but for production need to hande csrf tokens
        /*
        // Some example authorization matchers
        http.authorizeRequests().anyRequest().permitAll(); // all request types authenticated
        http.authorizeRequests().anyRequest(); // all request types permitted w/o auth
        http.authorizeRequests()
             .antMatchers(HttpMethod.GET)
             .authenticated()
             .anyRequest()
             .permitAll();  // only secure get class, others don't require username or password

        http.authorizeRequests() // GET methods require ADMIN role, POST require MANAGER, all others permitted without auth
                .antMatchers(HttpMethod.GET).access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST).access("hasAnyRole('ROLE_MANAGER')")
                .anyRequest().permitAll();
        */


        http.authorizeRequests()
            .antMatchers("/getAuths")
            .access("hasAnyRole('ADMIN','MANAGER')")
            .antMatchers(HttpMethod.POST,"/addUser")
            .access("hasRole('ADMIN')")
                .antMatchers("/getUsername")
                .authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }


}
