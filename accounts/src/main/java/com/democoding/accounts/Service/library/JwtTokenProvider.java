package com.democoding.accounts.Service.library;



import com.democoding.accounts.Dto.GenerateTokenRequest;
import com.democoding.accounts.Dto.JwtResponse;
import com.democoding.accounts.Entity.Users;
import com.democoding.accounts.Exception.ResourceNotAcceptableException;
import com.democoding.accounts.Repository.UsersRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    @Autowired
    UsersRepository userRepository;

    public JwtResponse generateToken(Users users) {
        GenerateTokenRequest request = GenerateTokenRequest.builder().username(users.getUsername()).password(users.getPassword()).build();

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        Claims claims = Jwts.claims().setIssuer(request.getUsername()).setIssuedAt(currentDate).setExpiration(expireDate);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact());
        jwtResponse.setExpiredAt(expireDate);

        return jwtResponse;
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();
        return claims.getIssuer();
    }

    public String getJWTfromRequest(String request) {
        if (StringUtils.hasText(request) && request.startsWith("Bearer ")) {
            return request.substring(7, request.length());
        }
        return null;
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new ResourceNotAcceptableException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new ResourceNotAcceptableException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new ResourceNotAcceptableException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotAcceptableException("JWT claims string is empty.");
        }
    }
}
