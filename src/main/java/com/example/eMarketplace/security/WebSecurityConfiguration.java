package com.example.eMarketplace.security;

import com.example.eMarketplace.service.UserDetailService;
import com.example.eMarketplace.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    UserDetailService userDetailService;
    BCryptPasswordEncoder encoder;
    JwtUtils jwtUtils;

    @Autowired
    public WebSecurityConfiguration(UserDetailService userDetailService, BCryptPasswordEncoder encoder, JwtUtils jwtUtils) {
        this.userDetailService = userDetailService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.GET, "/", "/*.html", "/js/*.js", "/css/*.css", "/*-photo.jpg", "/*.png")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/market", "/market/*")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/user")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/user")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterAt(new JwtAuthorizationFilter(jwtUtils), AuthorizationFilter.class)
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(encoder);
        return provider;
    }


}
