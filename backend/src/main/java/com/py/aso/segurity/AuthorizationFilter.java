package com.py.aso.segurity;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	private JwtUtil jwtUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);

	public AuthorizationFilter( //
			final AuthenticationManager authenticationManager, final ApplicationContext applicationContext //
	) {
		super(authenticationManager);
		jwtUtil = applicationContext.getBean(JwtUtil.class);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String authorizationHeader = request.getHeader("Authorization");
		if (!hasAuthorizationToken(authorizationHeader)) {
			chain.doFilter(request, response);
			return;
		}

		final String token = authorizationHeader.substring(7);

		Claims tokenPayload = null;
		UsernamePasswordAuthenticationToken authentication = null;
		try {
			tokenPayload = jwtUtil.validateToken(token);
			final String usercode = (String) tokenPayload.get("usercode");
			final Object roles = tokenPayload.get("authorities");
			final int id = (Integer) tokenPayload.get("id");
			Collection<? extends GrantedAuthority> authorities = Arrays.asList( //
					new ObjectMapper() //
							.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)//
							.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));

			authentication = new UsernamePasswordAuthenticationToken(usercode, id, authorities);
		} catch (final JwtException | IllegalArgumentException ex) {
			LOGGER.warn("Token invalido");
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private boolean hasAuthorizationToken(final String header) {
		return (header != null && header.startsWith("Bearer "));
	}
}