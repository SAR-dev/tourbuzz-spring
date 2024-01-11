package com.backend.api.configuration;

import com.backend.api.Constants;
import com.backend.api.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthService authService;
    private static final String DEFAULT_PASSWORD = "";
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = new ArrayList<>();
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String accessToken = request.getHeader(Constants.ACCESS_HEADER_NAME);

        if (!Objects.isNull(accessToken)) {
            try {
                String email = authService.getEmailFromAuthToken(accessToken);
                if (StringUtils.isNotEmpty(email) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    UserDetails userDetails = new User(email, DEFAULT_PASSWORD, DEFAULT_AUTHORITIES);
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    context.setAuthentication(authToken);
                    SecurityContextHolder.setContext(context);
                }
            } catch (Exception ignored) {}
        }
        filterChain.doFilter(request, response);
    }
}