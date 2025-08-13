package com.example.internship_portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager UserDetailsManager = new JdbcUserDetailsManager(dataSource);

        UserDetailsManager.setUsersByUsernameQuery("select username, password, is_active from users where username=?");

        UserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT u.username, r.role " +
                        "FROM roles r " +
                        "JOIN users u ON r.user_id = u.id " +
                        "WHERE u.username=?"
        );

        return UserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(configurer ->
//                configurer
//                        .requestMatchers(HttpMethod.DELETE, "/api/employees").hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
//        );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
