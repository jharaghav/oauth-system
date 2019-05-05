package com.example.springBootOauth2.service;

import com.example.springBootOauth2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.springBootOauth2.config.AuthServerConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder encoder;

//    @Autowired
//    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .map(account -> new User(account.getUsername(),
                        encoder.encode(account.getPassword()),
                        true,
                        true,
                        true,
                        true,
                        AuthorityUtils.createAuthorityList("write","read")))
                .orElseThrow(() -> new UsernameNotFoundException("username does not exist"));
    }
}
