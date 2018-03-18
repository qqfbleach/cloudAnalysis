package com.qqfall.cloud.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@EnableWebSecurity
@Controller
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("123").password("123").roles("USER").build());
		return manager;
	}
	

	 @GetMapping("/login")
	 @PostMapping("/login")
	    public String login() {
	        return "login";
	    }
	 
	 @GetMapping("/loginSucess")
	 @PostMapping("/loginSucess")
	    public String logout() {
	        return "loginSucess";
	    }
	 	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests().antMatchers("/cloud/v1/instance/").hasRole("USER")
//		.and().formLogin().loginPage("/login").permitAll();
//		.and().logout().invalidateHttpSession(true).clearAuthentication(true)
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll();
//
		 http
         .csrf().disable()
         .authorizeRequests()
         .antMatchers("/cloud/v1/instance/**").permitAll();
		 http
		 .authorizeRequests()
		 .anyRequest().authenticated()
		 .and().formLogin()
		 .loginPage("/login").defaultSuccessUrl("/#/user")
		 .permitAll();
		 
		 http.logout().invalidateHttpSession(true).clearAuthentication(true)
         .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll();
	}
}