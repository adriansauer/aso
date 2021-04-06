package com.py.aso.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.py.aso.entity.UserEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.UserRepository;

@Service
public class JpaUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usercode) throws UsernameNotFoundException {
		UserEntity user = this.userRepository.findByUsercodeAndEnabled(usercode, true)//
				.orElseThrow(() -> new ResourceNotFoundException("User", "usercode", usercode));

		final List<GrantedAuthority> authorities = user.getRoles().stream() //
				.map(role -> new SimpleGrantedAuthority(role.getAuthority())) //
				.collect(Collectors.toList());

		return new User( //
				user.getUsercode(), //
				user.getPassword(), //
				user.isEnabled(), //
				true, // accountNonExpired
				true, // credentialsNonExpired
				true, // accountNonLocked
				authorities //
		);
	}

}
