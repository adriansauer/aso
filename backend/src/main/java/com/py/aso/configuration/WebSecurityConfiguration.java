package com.py.aso.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.py.aso.segurity.AuthenticationFilter;
import com.py.aso.service.JpaUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JpaUserService jpaUserService;

	@Bean
	public BCryptPasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder //
				.userDetailsService(jpaUserService) //
				.passwordEncoder(passwordEncode());
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		// Configuration for h2
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/h2-console/**").permitAll();

		http.csrf().disable();
		http.headers().frameOptions().disable();

		http.addFilter(new AuthenticationFilter(authenticationManager(), getApplicationContext())) //
				.csrf().disable() //
				.exceptionHandling() //
				.and() //
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //
				.and() //
				.authorizeRequests().antMatchers("/login").permitAll() //
				.and().authorizeRequests().antMatchers("/**").permitAll();

	}

}
