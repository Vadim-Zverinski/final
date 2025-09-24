package by.it_academy.service.userService;

import by.it_academy.repository.entity.UserEntity;
import by.it_academy.service.userService.api.IJwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import by.it_academy.config.properties.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class JwtService implements IJwtService {

    private final JwtProperties jwtProperties;

    public String generateToken(UserEntity user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.getUuid().toString())
                .claim("mail", user.getMail())
                .claim("role", user.getRole())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(jwtProperties.getExpiration())))
                .signWith(Keys.hmacShaKeyFor(jwtProperties
                        .getSecret()
                        .getBytes())
                        , SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
        //toDo username в UserDetails использует mail, а тут передается uuid, передалать надо
    }

    public boolean validate(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return true;
        } catch (SignatureException ex) {
            //logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            //logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            //logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            //logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            //logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

    private Claims extractAllClaims(String token) {
        JwtParserBuilder parser = Jwts.parser();
        parser.verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()));
        return parser.build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

}
