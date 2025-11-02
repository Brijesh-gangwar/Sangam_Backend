package com.example.jwt_project.config;



import com.example.jwt_project.service.JWTokenService;
import com.example.jwt_project.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JWTokenService jwTokenService;

    @Autowired
    ApplicationContext context;

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authheader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        try {
            if (authheader != null && authheader.startsWith("Bearer ")) {
                token = authheader.substring(7).trim();
                logger.debug("Authorization header found, token length={}", token.length());

                username = jwTokenService.extractusername(token);
                logger.debug("Extracted username from token: {}", username);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);

                if (jwTokenService.validatetoken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    logger.debug("Authentication set for user: {}", username);
                } else {
                    logger.debug("Token validation failed for user: {}", username);
                }
            }
        } catch (Exception ex) {
            logger.warn("Failed to process JWT authentication: {}", ex.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
