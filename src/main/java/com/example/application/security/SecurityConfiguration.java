package com.example.application.security;

import com.example.application.data.UserRepository;
import com.example.application.services.UserDetailsServiceImpl;
import com.example.application.views.login.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository) {
//        return new UserDetailsServiceImpl(userRepository);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(
//                authorize -> authorize.requestMatchers(new AntPathRequestMatcher("/images/*.png")).permitAll());
//
//        http.authorizeHttpRequests(authorize -> authorize
//                .requestMatchers(new AntPathRequestMatcher("/line-awesome/**/*.svg")).permitAll());
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/images/*.png")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/line-awesome/**/*.svg")).permitAll());

        http.csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))); // Disable CSRF for H2
        http.headers(headers -> headers.frameOptions().disable()); // Allow H2 console in iframes

        super.configure(http);
        setLoginView(http, LoginView.class);
    }
}
