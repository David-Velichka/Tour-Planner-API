package com.tourplanner.config;

import com.tourplanner.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthHeaderFilter extends OncePerRequestFilter {

    private static final String USER_ID_HEADER = "X-User-Id";

    private final AuthService authService;

    public AuthHeaderFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        if (path == null) {
            return true;
        }

        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        if (!path.startsWith("/api/")) {
            return true;
        }

        return isAuthPath(path) || isSystemStatusPath(path);
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String headerValue = request.getHeader(USER_ID_HEADER);
        if (headerValue == null || headerValue.isBlank()) {
            writeUnauthorized(response, "Missing X-User-Id header.");
            return;
        }

        Long userId = parseUserId(headerValue);
        if (userId == null) {
            writeUnauthorized(response, "Invalid X-User-Id header.");
            return;
        }

        if (!authService.userExists(userId)) {
            writeUnauthorized(response, "Unknown user.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private Long parseUserId(String headerValue) {
        try {
            return Long.parseLong(headerValue);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private boolean isAuthPath(String path) {
        return path.equals("/api/auth") || path.startsWith("/api/auth/");
    }

    private boolean isSystemStatusPath(String path) {
        return path.equals("/api/system-status") || path.startsWith("/api/system-status/");
    }

    private void writeUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write("{\"message\":\"" + message + "\"}");
    }
}
