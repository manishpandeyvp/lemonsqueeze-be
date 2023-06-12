package com.lemonsqueeze.lemonsqueezebe.security.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lemonsqueeze.lemonsqueezebe.exception.EntityNotFoundException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// We made this class for exception handling, and have not used or extended EntityNotFoundException class because, 
// filtering is done before going to the controller, and that class could be reached from controlled, hence made this class.

public class ExceptionHandlerFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (EntityNotFoundException e) { // If data does not exist for a user in the record.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Username doesn't exist");
            response.getWriter().flush();
        } catch (JWTVerificationException e) { // If verification fails
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("JWT NOT VALID");
            response.getWriter().flush();
        } catch (RuntimeException e) { // For other generic cases
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("BAD REQUEST");
            response.getWriter().flush();
        }
    }
}
