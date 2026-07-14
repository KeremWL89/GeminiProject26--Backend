package com.Backend.Spring.Security.JWT;


import com.Backend.Spring.Model.Entity.UserPrincipal;
import com.Backend.Spring.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService{

    public byte[] secretKey;

    public JwtService(){

            try{
                KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
                SecretKey sK = keyGenerator.generateKey();
                // System.out.println(sK.getEncoded().toString() + "JWT SECRET CREATED");
                secretKey= sK.getEncoded();

            }catch (NoSuchAlgorithmException e){
                throw new RuntimeException(e);
            }

    }



    public String generateToken(String username){
        Map<String , Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 *60 *30))
                .signWith(getKey())
                .compact();
    }


    private Key getKey(){

        return Keys.hmacShaKeyFor(secretKey);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims :: getSubject );
    }


    public boolean validateToken(String token , UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T>T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }


    private Date extractExpiration(String token){
        return extractClaim(token , Claims::getExpiration );
    }



}




/*
*       expired
*    // @Value("${jwt.secret}")
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

*
*
* it shouldnt be generated everytime  i cant use when i want to validate ofc
*
*
public byte[] secretKeyGenerator(){

    byte[] secretKey;

    try{
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey sK = keyGenerator.generateKey();
        System.out.println(sK.getEncoded().toString());
        secretKey = sK.getEncoded();

    }catch (NoSuchAlgorithmException e){
        throw new RuntimeException(e);
    }

    return secretKey;
}

*
*
*
* */
