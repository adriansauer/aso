package com.py.aso.segurity;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.py.aso.dto.detail.UserDetailDTO;
import com.py.aso.properties.JwtProperties;
import com.py.aso.service.UserService;

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

	@Autowired
	private UserService userService;

	public String generateToken(final User user) throws Exception {
		final String secretKey = jwtProperties.getKey();
		final UserDetailDTO userDetailDTO = this.userService.findByUsercode(user.getUsername());
		final Claims claims = Jwts.claims();
		final Collection<? extends GrantedAuthority> roles = user.getAuthorities();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
		claims.put("usercode", user.getUsername());
		claims.put("id", userDetailDTO.getId());

		return Jwts.builder()//
				.setClaims(claims) //
				.setSubject(user.getUsername()) //
				.signWith(getSigningKey(secretKey)) //
				.setIssuedAt(new Date(System.currentTimeMillis())) //
				.setExpiration(Date.from(Instant.now().plusMillis(jwtProperties.getExpiration()))) //
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