package com.Backend.Spring.Security.Config;

import com.Backend.Spring.Security.JWT.JwtFilter;
import com.Backend.Spring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration          //this is a config class look up when starting
@EnableWebSecurity      // i have security rules so rather than applying default , apply mine
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{

        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/auth/Login", "/api/v1/users/Register").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // before unameFilter use JWTfilter
                .build();


    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userService);

        //here will be encoding processes.
        //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        //validate with encryption v2
        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
         return authenticationConfiguration.getAuthenticationManager();
    }



}







        /* this is a note  SECURITYFILTERCHAIN
        http.csrf(customizer -> customizer.disable());  // csrf disabled.

        //every request has to be validated
        //where you can alter your request to be authenticated
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        //default spring sec login form
        // we have stateless session management so we should disable this
        http.formLogin(Customizer.withDefaults());

        //when you want to access from another client like postman or fe client
        http.httpBasic(Customizer.withDefaults());

        // in order to handle csrf we need to have a stateless session policy
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
        */
