package com.vignesh.ExpenseManager.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
@Configuration
@EnableWebSecurity
public class CustomSecurityConfiguration  {

	@Value("${expense.manager.frontend.url}")
	private String frontendURL;
	@Bean
	public InMemoryUserDetailsManager prototypeUsers() {
		UserDetails user1 = User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("USER").build();
		UserDetails user2 = User.withUsername("admin1").password(passwordEncoder().encode("admin1")).roles("USER").build();
		return new InMemoryUserDetailsManager(user1,user2);
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
		http.authorizeHttpRequests((auth) ->
        auth
        .requestMatchers(mvcMatcherBuilder.pattern("/**")).permitAll()
		.anyRequest().authenticated());
		http.httpBasic(Customizer.withDefaults());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		http.formLogin(withDefaults());
		http.csrf().disable();
//		http.headers().frameOptions().disable();
//		http.cors();
		return http.build();
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
