package com.py.aso.segurity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.py.aso.entity.UserEntity;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

	private AuthenticationManager authManager;
	private JwtUtil tokenProvider;

	public AuthenticationFilter( //
			final AuthenticationManager authenticationManager, final ApplicationContext applicationContext //
	) {
		this.authManager = authenticationManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		tokenProvider = applicationContext.getBean(JwtUtil.class);
	}

	@Override
	public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) {
		setUsernameParameter("usercode");
		String usercode = obtainUsername(request);
		String password = obtainPassword(request);

		UserEntity user = null;
		if (usercode != null && password != null) {
			log.warn("Codigo de usuario o la contraseña incorrecto");
		} else {
			try {
				user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
				usercode = user.getUsercode();
				password = user.getPassword();
			} catch (final IOException e) {
				log.warn("Problemas al extraer el codigo de usuario o la contraseña");
			}
		}
		try {
			final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					usercode.trim(), password);
			return authManager.authenticate(authToken);
		} catch (Exception ex) {
			throw new UsernameNotFoundException("Error al crear autenticarse");
		}

	}

	@Override
	protected void successfulAuthentication( //
			final HttpServletRequest request, //
			final HttpServletResponse response, //
			final FilterChain chain, //
			final Authentication authResult) throws IOException, ServletException {

		try {
			final User user = (User) authResult.getPrincipal();
			final String token = tokenProvider.generateToken(user);
			response.addHeader("Authorization", "Bearer " + token);

			final Map<String, Object> body = new HashMap<>();
			body.put("token", token);
			body.put("user", user);
			body.put("message", "Autenticación exitosa");

			response.getWriter().write(new ObjectMapper().writeValueAsString(body));
			response.setStatus(200);
			response.setContentType("application/json");
		} catch (final Exception e) {
			log.warn("No se pudo generar el token");
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		logger.warn("Error al autenticarse");

		final Map<String, Object> body = new HashMap<>();
		body.put("message", "Autenticación fallida");

		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");

	}

}