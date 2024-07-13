package com.example.demo.auth;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtill {
	private String SECRET_KEY="hgvbnjugfc";
public String getUserName(String token) {
return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
} 
public boolean getExpiretion(String token) {
return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration().before(new Date());
} 
public Boolean validateToken(String token, String username) {
    final String tokenUsername = getUserName(token);
    return (username.equals(tokenUsername) && !getExpiretion(token));
}
public String generateToken(String subject) {
return Jwts.builder().setSubject(subject)
		.setIssuedAt( new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
		.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
} 
}
