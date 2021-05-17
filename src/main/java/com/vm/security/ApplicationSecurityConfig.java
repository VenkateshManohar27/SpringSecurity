package com.vm.security;

import static com.vm.security.ApplicationUserRole.ADMIN;
import static com.vm.security.ApplicationUserRole.ADMINTRAINEE;
import static com.vm.security.ApplicationUserRole.STUDENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder pe) {
		super();
		this.passwordEncoder = pe;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf()
					.disable()
					.authorizeRequests()
					.antMatchers("/", "index", "/css/*", "/js/*")
					.permitAll()
					.antMatchers("/vm/**")
					.hasRole(STUDENT
							.name())
					.anyRequest()
					.authenticated()
					.and()
					.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails venkat = User
				.builder()
					.username("venkat")
					.password(passwordEncoder
							.encode("password"))
					.roles(STUDENT
							.name())
					.build();

		UserDetails arnold = User
				.builder()
					.username("admin")
					.password(passwordEncoder
							.encode("admin"))
					.roles(ADMIN
							.name())
					.build();

		UserDetails stallone = User
				.builder()
					.username("stal")
					.password(passwordEncoder
							.encode("stal"))
					.roles(ADMINTRAINEE
							.name())
					.build();

		return new InMemoryUserDetailsManager(venkat, arnold, stallone);
	}

}
