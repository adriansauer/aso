package com.py.aso.segurity;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.py.aso.properties.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * 
 * @author Victor Ciceia Informacion de apoyo para creacion de de la clase de la
 *         pagina
 *         https://blog.softtek.com/en/token-based-api-authentication-with-spring-and-jwt
 */
@Component
public class JwtUtil {

	@Autowired
	private JwtProperties jwtProperties;

	public String generateToken(final User user) throws JsonProcessingException {
		final String secretKey = jwtProperties.getKey();

		final Claims claims = Jwts.claims();
		final Collection<? extends GrantedAuthority> roles = user.getAuthorities();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
		claims.put("usercode", user.getUsername());

		return Jwts.builder()//
				.setSubject(user.getUsername()) //
				.signWith(getSigningKey(secretKey)) //
				.setIssuedAt(new Date(System.currentTimeMillis())) //
				.setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration())) //
				.setClaims(claims) //
				.compact();
	}

	public Claims validateToken(final String token) {
		final String secretKey = jwtProperties.getKey();
		return Jwts.parser() //
				.setSigningKey(secretKey.getBytes()) //
				.parseClaimsJws(token).getBody();
	}

	private Key getSigningKey(final String secretKey) {
		byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}