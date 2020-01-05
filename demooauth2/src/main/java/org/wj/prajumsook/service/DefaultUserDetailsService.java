package org.wj.prajumsook.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String defaultUserName = "wj@prajumsook.com";
        String password = encoder.encode("passw0rd");
        if(!defaultUserName.equalsIgnoreCase(userName)) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDetails userDetails = User.builder()
                .username(userName)
                .password(password)
                .authorities(Collections.emptyList())
                .build();

        return userDetails;
    }
}
