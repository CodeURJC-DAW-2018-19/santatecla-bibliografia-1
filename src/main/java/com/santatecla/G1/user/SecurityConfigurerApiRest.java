package com.santatecla.G1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@Order(1)
public class SecurityConfigurerApiRest extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/api/login").permitAll();
		http.antMatcher("/api/**");	
		http.authorizeRequests().antMatchers("/api/signup").permitAll();

		http.authorizeRequests().antMatchers("/api//create-pdf/*").permitAll();
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/usuario/**").hasAnyRole("ADMIN");
		
		http.authorizeRequests().antMatchers("/api/book-pageable").hasAnyRole("ADMIN", "STUDENT");
		http.authorizeRequests().antMatchers("/api/theme-pageable").hasAnyRole("ADMIN", "STUDENT");
		http.authorizeRequests().antMatchers("/api/citation-pageable").hasAnyRole("ADMIN", "STUDENT");
		http.authorizeRequests().antMatchers("/api/author-pageable").hasAnyRole("ADMIN", "STUDENT");
		http.authorizeRequests().antMatchers("/api/theme/**").hasAnyRole("ADMIN","STUDENT");
		http.authorizeRequests().antMatchers("/api/author/**").hasAnyRole("ADMIN","STUDENT");
		http.authorizeRequests().antMatchers("/api/book/**").hasAnyRole("ADMIN","STUDENT");
		http.authorizeRequests().antMatchers("/api/book").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/api/theme").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/api/author").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PATCH).hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST).hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE).hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET).permitAll();
		
		// Other URLs can be accessed without authentication
		//http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement with ng2)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Database authentication provider
		auth.authenticationProvider(authenticationProvider);
	}
}
