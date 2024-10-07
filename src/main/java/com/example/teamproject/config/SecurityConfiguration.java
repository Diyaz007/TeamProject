package com.example.teamproject.config;


import com.example.teamproject.controllers.UsersController;
import com.example.teamproject.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    private final UsersService userService;
    private final UsersController usersController;
    private final AuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Autowired
    public SecurityConfiguration(UsersService userService, UsersController usersController, AuthenticationSuccessHandler customizeAuthenticationSuccessHandler) {
        this.userService = userService;
        this.usersController = usersController;
        this.customizeAuthenticationSuccessHandler = customizeAuthenticationSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService::loadUserByUsername;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","/register", "/login", "/css/**", "/js/**","/img/**","/templates/**","/logout","/get_all_companies","/images/{id}","mailto:diyaz.turganaliev@alatoo.edu.kg").permitAll()
                        .requestMatchers("/updateMainPage").hasAuthority("ADMIN")
                        .requestMatchers("/mainUser","/add_company","/save_company").authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(customizeAuthenticationSuccessHandler)
                        .failureUrl("/login?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }
}
