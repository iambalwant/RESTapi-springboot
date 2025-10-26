package com.edigest.myfirstproject.config;

import com.edigest.myfirstproject.service.UserDetailServicimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SpringSecurity{

    @Autowired
    private UserDetailServicimpl userDetailServicimpl;


    public SpringSecurity(UserDetailServicimpl userDetailServicimpl) {
        this.userDetailServicimpl = userDetailServicimpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for simplicity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/journal/**", "/user/**").authenticated()  // allow public endpoints
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()// secure all others

                )
                .httpBasic(Customizer.withDefaults()); // default login form

        return http.build();
    }

//    AuthenticationConfiguration internally does exactly this behind the scenes:
//
//    AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder();
//     builder.userDetailsService(userDetailsService)
//            .passwordEncoder(passwordEncoder);
//     return builder.build();

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
