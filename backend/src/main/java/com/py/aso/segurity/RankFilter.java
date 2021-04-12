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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

public class RankFilter extends BasicAuthenticationFilter {

	private final String ROLE_USER = "ROLE_USER";
	private final String ROLE_COMMANDANT = "ROLE_COMMANDANT";
	private final String ROLE_SUPERUSER = "ROLE_SUPERUSER";
	private final String ROLE_VISITOR = "ROLE_VISITOR";

	private final String GET = "GET";
	private final String PUT = "PUT";
	private final String POST = "POST";
	private final String DELETE = "DELETE";

	private JwtUtil jwtUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(RankFilter.class);

	public RankFilter(final AuthenticationManager authenticationManager, final ApplicationContext applicationContext //
	) {
		super(authenticationManager);
		jwtUtil = applicationContext.getBean(JwtUtil.class);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		final String authorizationHeader = request.getHeader("Authorization");
		final String token = authorizationHeader.substring(7);

		Claims tokenPayload = null;
		try {
			tokenPayload = jwtUtil.validateToken(token);
			final String usercode = (String) tokenPayload.get("usercode");
			final int id = (Integer) tokenPayload.get("id");
			Object roles = tokenPayload.get("authorities");
			Collection<? extends GrantedAuthority> authorities = Arrays.asList( //
					new ObjectMapper() //
							.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)//
							.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));

			if (!filterByRole(request.getServletPath(), request.getMethod(), authorities, usercode, id)) {
				LOGGER.warn("El usuario " + usercode + " no posee el rango");
				SecurityContextHolder.getContext().setAuthentication(null);
			}
			chain.doFilter(request, response);
		} catch (final JwtException | IllegalArgumentException ex) {
			LOGGER.warn("Fallo al dar autorizacion en el filtro de rangos");
		}

	}

	private boolean filterByRole(final String path, final String method,
			final Collection<? extends GrantedAuthority> roles, final String usercode, final long id) {
		if (exists(roles, this.ROLE_USER)) {
			return filterByUser(path, method, usercode, id);
		} else if (exists(roles, this.ROLE_COMMANDANT)) {
			return filterByCommandant(path, method, usercode);
		} else if (exists(roles, this.ROLE_SUPERUSER)) {
			return filterBySuperUser(path, method, usercode);
		} else if (exists(roles, this.ROLE_VISITOR)) {
			return filterByVisitor(path, method, usercode);
		} else {
			LOGGER.warn("No se pudo encontrar un rol para validar");
			return false;
		}
	}

	private boolean filterByUser(final String path, final String method, final String usercode, final long id) {
		if (this.GET.equalsIgnoreCase(method)) {
			return false;
		} else if (this.POST.equalsIgnoreCase(method)) {
			return false;
		} else if (this.PUT.equalsIgnoreCase(method)) {
			return false;
		} else if (this.DELETE.equalsIgnoreCase(method)) {
			return false;
		} else {
			LOGGER.warn("Metodo utilizado en la peticion no encontrado");
			return false;
		}
	}

	private boolean filterByCommandant(final String path, final String method, final String usercode) {
		return true;
	}

	private boolean filterBySuperUser(final String path, final String method, final String usercode) {
		return true;
	}

	private boolean filterByVisitor(final String path, final String method, final String usercode) {
		return true;
	}

	private boolean exists(final Collection<? extends GrantedAuthority> roles, String rol) {
		return roles.stream().anyMatch(r -> r.toString().equalsIgnoreCase(rol));
	}

	private long getPathId(final String path) {
		return 0L;
	}

}
