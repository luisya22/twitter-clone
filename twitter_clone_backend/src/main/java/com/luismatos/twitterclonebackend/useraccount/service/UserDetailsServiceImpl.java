package com.luismatos.twitterclonebackend.useraccount.service;

import com.luismatos.twitterclonebackend.useraccount.model.SecurityUser;
import com.luismatos.twitterclonebackend.useraccount.model.User;
import com.luismatos.twitterclonebackend.useraccount.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private static final Logger log = LogManager.getLogger(UserDetailsServiceImpl.class);

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty()){
            log.error("User not found in the database: {}", username);
            throw new UsernameNotFoundException("User not found in the database:" + username);
        }else{
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new SecurityUser(user.get(), authorities);
    }
}
