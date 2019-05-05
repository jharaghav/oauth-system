package com.example.springBootOauth2.config;

import jdk.nashorn.internal.parser.TokenStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    TokenStore tokenStore;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //super.configure(clients);
        clients.inMemory()
                .withClient("client")
                .secret(encoder().encode("secret"))
                .authorizedGrantTypes("password")
                .scopes("all")
        .and().withClient("resource-server")
        .secret(encoder.encode("secret"))
        .authorizedGrantTypes("password")
        .scopes("all")
        .and().withClient("client-service")
        .secret(encoder.encode("secret"))
        .authorizedGrantTypes("client_credentials")
        .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //super.configure(security);
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
       // super.configure(endpoints);
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager);
    }

        @Bean
        PasswordEncoder encoder()
        {
            return  new BCryptPasswordEncoder();
        }

    @Bean
    TokenStore tokenStore()
    {
        return new InMemoryTokenStore();
    }
}
