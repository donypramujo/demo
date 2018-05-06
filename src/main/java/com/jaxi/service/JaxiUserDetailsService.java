package com.jaxi.service;

import com.jaxi.common.JaxiUserPrincipal;
import com.jaxi.entity.User;
import com.jaxi.exception.UserNotFoundException;
import com.jaxi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JaxiUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return new JaxiUserPrincipal(user);
    }
}
