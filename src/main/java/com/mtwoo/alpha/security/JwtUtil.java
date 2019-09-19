package com.mtwoo.alpha.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${app.jwtSecret}")
    private String key;
    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String provideToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + jwtExpirationInMs);

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userPrincipal.getUsername());
        claims.put("authorization", userPrincipal.getAuthorities().stream().map(a -> ((GrantedAuthority) a).toString()).collect(Collectors.joining("*")));

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public boolean validateToken(String jwt){
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public Long getUserIdFromToken(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
}
