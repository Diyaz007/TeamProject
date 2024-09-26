package com.example.teamproject.config;

import com.example.teamproject.enums.Roles;
import com.example.teamproject.services.UsersService;
import com.example.teamproject.entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.sendRedirect("/mainUser");
    }
}