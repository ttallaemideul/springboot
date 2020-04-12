package io.github.ttallaemideul.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import io.github.ttallaemideul.auth.UserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserDetailService userDetailService;

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailService userDetailsService) {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(daoAuthenticationProvider(userDetailService));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.antMatchers("/admin/**").hasRole("ADMIN") //
				.antMatchers("/user/**").hasRole("USER") //
			.anyRequest() //
				.permitAll() //
			//.and().csrf().disable() //
			.and().csrf().ignoringAntMatchers("/h2-console/**")
            .and()
            .headers()
                .addHeaderWriter(
                    new XFrameOptionsHeaderWriter(
                        new WhiteListedAllowFromStrategy(Arrays.asList("localhost"))
                    )
                ).frameOptions().sameOrigin()
                // h2-console 사용을 위해서 추가
            .and()
			.formLogin() //
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/")
				.usernameParameter("login_id")
				.passwordParameter("pwd") 
				//
			.and() //
				.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				//
			.and() //
				.exceptionHandling()
				.accessDeniedPage("/access-denied")
				//
			;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring() //
			.antMatchers("/assets/**") //
			.antMatchers("/html/**") //
			.antMatchers("/favicon.ico") //
			;
	}

}
