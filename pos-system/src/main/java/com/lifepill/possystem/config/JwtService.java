package com.lifepill.possystem.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;


/**
 * Service class for handling JWT token generation and validation.
 */
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * Generates a JWT token for the provided UserDetails.
     *
     * @param employer UserDetails object containing employer details
     * @return Generated JWT token
     */
    public String generateToken(UserDetails employer) {
        return Jwts.builder()
                .setSubject(employer.getUsername())
                .claim("authorities", populateAuthorities(employer.getAuthorities()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Retrieves the signing key for JWT token generation.
     *
     * @return Signing key
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Converts a collection of GrantedAuthority objects to a comma-separated string.
     *
     * @param authorities Collection of GrantedAuthority objects
     * @return Comma-separated string of authorities
     */
    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritiesSet = new HashSet<>();
        for(GrantedAuthority authority: authorities) {
            authoritiesSet.add(authority.getAuthority());
        }
        //CASHIER, OWNER, MANAGER, OTHER
        return String.join(",", authoritiesSet);
    }

    /**
     * Extracts all claims from the provided JWT token.
     *
     * @param token JWT token
     * @return Claims extracted from the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extracts the username from the provided JWT token.
     *
     * @param token The JWT token from which the username will be extracted.
     * @return The username extracted from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from the provided JWT token using the given resolver function.
     *
     * @param token          JWT token
     * @param claimsResolver Function to resolve the desired claim from Claims
     * @param <T>            Type of the resolved claim
     * @return Resolved claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Validates whether a JWT token is valid for the given user.
     *
     * @param token       The JWT token to be validated.
     * @param userDetails The UserDetails object representing the user.
     * @return True if the token is valid for the user, false otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
    }

}