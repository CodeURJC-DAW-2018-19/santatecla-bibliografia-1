package com.santatecla.G1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	 private static String REALM="MY_TEST_REALM";
	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	protected void configure(HttpSecurity http) throws Exception{
		
		
		
		//Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.authorizeRequests().antMatchers("/image/{id}").permitAll();
		
		
		//Private pages 
		//http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/api/book-pageable").hasAnyRole("ADMIN", "STUDENT");
		http.authorizeRequests().antMatchers("/api/theme-pageable").hasAnyRole("ADMIN", "STUDENT");
		http.authorizeRequests().antMatchers("/api/citation-pageable").hasAnyRole("ADMIN", "STUDENT");
		http.authorizeRequests().antMatchers("/api/author-pageable").hasAnyRole("ADMIN", "STUDENT");

		http.authorizeRequests().antMatchers("/api/theme/**").hasRole("STUDENT");
		http.authorizeRequests().antMatchers("/api/author/**").hasRole("ADMIN").and().httpBasic().realmName(REALM).authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/api/author/**").hasRole("STUDENT");
		http.authorizeRequests().antMatchers("/api/book/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/api/book/**").hasRole("STUDENT");
		http.authorizeRequests().antMatchers("/api/book").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/api/theme").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/api/author").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/api/author").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PATCH).hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST).hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE).hasRole("ADMIN");
		//Login form
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().failureUrl("/loginerror");
		
		//Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

		
		//CSRF
		http.csrf().disable();
	}
	

	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//Database authentication provider
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	  web.ignoring().antMatchers("/js/**");
	  web.ignoring().antMatchers("/css/**");
	  web.ignoring().antMatchers("/img/**");
	  web.ignoring().antMatchers("/favicon.ico");

	}
}
