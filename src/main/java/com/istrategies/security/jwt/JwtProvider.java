package com.istrategies.security.jwt;

import com.istrategies.security.entity.MainUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String genereteToken(Authentication auth){
        MainUser mainUser = (MainUser) auth.getPrincipal();

        return Jwts.builder()
                .setSubject(mainUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expiration*1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException malformedJwtException){
            log.error("Incorrectly formed token: "+malformedJwtException.getMessage());
        }catch (UnsupportedJwtException unsupportedJwtException){
            log.error("Token not supported: "+unsupportedJwtException.getMessage());
        }catch (ExpiredJwtException expiredJwtException){
            log.error("Token expired: "+expiredJwtException.getMessage());
        }catch (IllegalArgumentException illegalArgumentException){
            log.error("Empty token: "+illegalArgumentException.getMessage());
        }catch (SignatureException signatureException){
            log.error("Failure with signature: "+signatureException.getMessage());
        }

        return false;
    }
}
