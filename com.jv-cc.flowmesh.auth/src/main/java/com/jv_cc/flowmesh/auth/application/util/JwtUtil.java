package com.jv_cc.flowmesh.auth.application.util;

import com.jv_cc.flowmesh.auth.domain.model.UserRoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    private final Long ACCESS_TOKEN_EXPIRATION_TIME;
    private final Long REFRESH_TOKEN_EXPIRATION_TIME;
    private final SecretKey SECRET_KEY;
    private final String USERID = "user_id";
    private final String USERROLE = "user_role";
    private final String ISSUER = "auth";

    public JwtUtil(
            @Value("${service.jwt.secret-key}") String secretKey,
            @Value("${service.jwt.access-expiration}") Long accessExpiration,
            @Value("${service.jwt.refresh-expiration}") Long refreshExpiration
    ) {
        this.SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.ACCESS_TOKEN_EXPIRATION_TIME = accessExpiration;
        this.REFRESH_TOKEN_EXPIRATION_TIME = refreshExpiration;
    }

    private Claims getClaimValueFromToken(String token) {
        token = token.substring(JwtHeader.VALUE_BEARER_PREFIX.length());
        return Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
    }

    public String getUserIdFromToken(String token) {
        return getClaimValueFromToken(token).get(USERID, String.class);
    }

    public String getUserRoleFromToken(String token) {
        return getClaimValueFromToken(token).get(USERROLE, String.class);
    }

    public LocalDateTime getIssuedAtFromToken(String token) {
        Claims claims = getClaimValueFromToken(token);
        Date issuedAt = claims.getIssuedAt();

        return issuedAt.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public boolean validateToken(String token) {
        try {
            token = token.substring(JwtHeader.VALUE_BEARER_PREFIX.length());

            Claims payload = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            log.debug("JWT claims string: {}", payload);

            String issuer = payload.getIssuer();
            if (issuer == null || !issuer.equals(ISSUER)) {
                log.error("Invalid issuer");
                return false;
            }
            log.debug("Validated token issuer");

            String userId = payload.get(USERID, String.class);
            Long userIdOrigin = userId != null ? Long.valueOf(userId) : null;
            if (userIdOrigin != null && userIdOrigin <= 0) {
                log.error("Invalid userId");
                return false;
            }
            log.debug("Validated token userId");

            String userRole = payload.get(USERROLE, String.class);
            UserRoleEnum userRoleOrigin = userRole != null ? UserRoleEnum.valueOf(userRole) : null;
            if (userRoleOrigin == null){
                log.error("Invalid userRole");
                return false;
            }
            log.debug("Validated token userRole");

            Date expiration = payload.getExpiration();
            if (expiration == null || expiration.before(new Date())) {
                log.error("Invalid expiration");
                return false;
            }
            log.debug("Validated token expiration");

            return true;
        } catch (ExpiredJwtException e) {
            log.error("ExpiredJwtException, Token has expired: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("MalformedJwtException, Malformed token: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Exception, Invalid token: {}", e.getMessage());
        }

        return false;
    }

    public String generateRefreshToken(Long userId, UserRoleEnum userRole) {
        return JwtHeader.VALUE_BEARER_PREFIX + Jwts.builder()
                .claim(USERID, String.valueOf(userId))
                .claim(USERROLE, String.valueOf(userRole))
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String generateAccessToken(Long userId, UserRoleEnum userRole) {
        return JwtHeader.VALUE_BEARER_PREFIX + Jwts.builder()
                .claim(USERID, String.valueOf(userId))
                .claim(USERROLE, String.valueOf(userRole))
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static class JwtHeader {
        public final static String KEY_ACCESS_TOKEN = "Authorization";
        public final static String KEY_REFRESH_TOKEN = "X-Refresh-Token";
        public final static String KEY_USER_ID = "X-User-Id";
        public final static String KEY_USER_ROLE = "X-User-Role";
        public final static String VALUE_BEARER_PREFIX = "Bearer ";
    }

}
