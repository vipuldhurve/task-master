package com.example.taskMaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Disable CSRF
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                                // Allow access to H2 console
//                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                                // Authenticate other requests
                                .anyRequest().authenticated()
                )
//                Form login for browser
//                .formLogin(Customizer.withDefaults())
//                Form login for postman
                .httpBasic(Customizer.withDefaults())
                // Allow frames from the same origin
                .headers(headers -> headers
                        .addHeaderWriter(new CustomFrameOptionsHeaderWriter()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("kiran")
//                .password("k@123")
//                .roles("USER")
//                .build();
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("harsh")
//                .password("h@123")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

}
