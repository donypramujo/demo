package com.jaxi.config;

import com.jaxi.service.JaxiUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JaxiUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
       // auth.inMemoryAuthentication().withUser("dony").password("{noop}password").roles("user");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/merchant/**")
//                .hasAuthority("c")
//                .antMatchers("/user/**")
//                .hasAuthority("tl")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();


    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {

        PasswordEncoder defaultEncoder = new BCryptPasswordEncoder();
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        String encodingId = "bcrypt";
        encoders.put(encodingId, new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        DelegatingPasswordEncoder passworEncoder = new DelegatingPasswordEncoder(encodingId, encoders);
        passworEncoder.setDefaultPasswordEncoderForMatches(defaultEncoder);

        return passworEncoder;


    }


    public static  void main(String[] args){

        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
      String _s = encoder.encode("password");
      System.out.println(_s);

       boolean _b = encoder.matches("password",_s);
        System.out.println(_b);


    }
}
