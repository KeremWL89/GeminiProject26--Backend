package com.Backend.Spring.Security.JWT;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService{

   // @Value("${jwt.secret}")
    private String secretKey;

   // @Value("${jwt.expiration}")
    private long jwtExpiration;

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T>T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token){
        return extractClaim(token , Claims::getExpiration );
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }





}
