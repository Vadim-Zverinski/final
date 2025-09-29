package by.it_academy.filter;

import by.it_academy.config.secure.SecurityProperties;

import by.it_academy.filter.config.UserDetailsImpl;
import by.it_academy.filter.config.UserDetailsServiceImpl;
import by.it_academy.repository.userRepository.entity.UserEntity;
import by.it_academy.service.JwtService;
import org.springframework.security.core.AuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();
        String method = request.getMethod();

        // --- проверяем whitelist ---
        List<String> whitelist = securityProperties.getWhitelist();
        boolean isWhitelisted = whitelist.stream().anyMatch(entry -> {
            if (entry.contains(":")) {
                String[] parts = entry.split(":", 2);
                return parts[0].equalsIgnoreCase(method) && parts[1].trim().equals(path);
            } else {
                return entry.trim().equals(path);
            }
        });

        if (isWhitelisted) {
            filterChain.doFilter(request, response);
            return;
        }

        // --- читаем Authorization header ---
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendError(response, "JWT token missing or malformed");
            return;
        }

        final String token = authHeader.substring(7);

        // --- проверяем токен ---
        if (!jwtService.validate(token)) {
            sendError(response, "JWT token invalid or expired");
            return;
        }

        // --- извлекаем username из токена ---
        String username = jwtService.extractUsername(token);

        // --- ставим Authentication в SecurityContext ---
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsService.
                    loadUserByUsername(username);
            UserEntity userEntity = userDetailsImpl.getUser();


            UserDetailsImpl userDetails = new UserDetailsImpl(userEntity);

            //System.out.println(userDetails.getUser().getRole().toString());


            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,                 // principal = UserDetailsImpl
                            null,                        // credentials
                            userDetails.getAuthorities() // роли с префиксом ROLE_
                    );

            authToken.setDetails(request); // детали запроса
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

    private void sendError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("""
                [
                  {
                    "logref": "error",
                    "message": "%s"
                  }
                ]
                """.formatted(message));
    }
}
