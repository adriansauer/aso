package com.py.aso.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.py.aso.segurity.AuthenticationFilter;
import com.py.aso.segurity.AuthorizationFilter;
import com.py.aso.service.JpaUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{


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

		http.addFilter(new AuthenticationFilter(authenticationManager(), getApplicationContext())) //
				.addFilter(new AuthorizationFilter(authenticationManager(), getApplicationContext())) //
				.csrf().disable() //
				.exceptionHandling() //
				.and() //
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //
				.and() //
				.authorizeRequests().antMatchers("/login").permitAll()//
				.and()//
				.authorizeRequests().antMatchers("/h2-console/**").permitAll()//
				.and()//
				.headers().frameOptions().disable()//
				.and()//
				.authorizeRequests().antMatchers("/api/**").authenticated();
	}


}
