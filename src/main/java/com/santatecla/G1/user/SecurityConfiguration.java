package com.santatecla.G1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	
	protected void configure(HttpSecurity http) throws Exception{
		
		
		//Public pages
		http.authorizeRequests().antMatchers("/index").permitAll();
		http.authorizeRequests().antMatchers("/author").permitAll();
		http.authorizeRequests().antMatchers("/author/{id}").permitAll();
		http.authorizeRequests().antMatchers("/book").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginmodal").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		
		
		//Private pages 
		http.authorizeRequests().anyRequest().authenticated();
		
		//Login form
		http.formLogin().loginPage("/login");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().failureUrl("/loginerror");
		
		//Logout
		http.logout().logoutSuccessUrl("/");
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
	  web.ignoring().antMatchers("/js/*");
	  web.ignoring().antMatchers("/css/*");
	  web.ignoring().antMatchers("/img/*");
	}
}
