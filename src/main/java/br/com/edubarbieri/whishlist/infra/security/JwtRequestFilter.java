package br.com.edubarbieri.whishlist.infra.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private JWTService jwtService;

    private RepositoryUserDetailsService userDetailService;

    public JwtRequestFilter(JWTService jwtService, RepositoryUserDetailsService userDetailService) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        tokenLogin(request);
        filterChain.doFilter(request, response);
    }

    private void tokenLogin(HttpServletRequest request) {
        final var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            return;
        }
        var token = authHeader.substring(7);
        if (!jwtService.validateToken(token)) {
            return;
        }
        setAuthContext(request, token);
    }

    private void setAuthContext(HttpServletRequest request, String token) {
        String login = jwtService.extractLogin(token);
        if (login == null && SecurityContextHolder.getContext().getAuthentication() == null) {
            return;
        }
        var userDetails = userDetailService.loadUserByUsername(login);
        var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
