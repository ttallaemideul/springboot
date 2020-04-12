package io.github.ttallaemideul.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import io.github.ttallaemideul.auth.UserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailService userDetailService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.antMatchers("/admin/**").hasRole("ADMIN") //
				.antMatchers("/user/**").hasRole("USER") //
			.anyRequest().permitAll()
			//.and().csrf().disable() //
			.and().csrf().ignoringAntMatchers("/h2-console/**")
            .and().headers() //
                .addHeaderWriter(
                    new XFrameOptionsHeaderWriter(
                        new WhiteListedAllowFromStrategy(Arrays.asList("localhost"))
                    )
                ).frameOptions().sameOrigin() //h2-console 사용을 위해서 추가
            .and().formLogin() //
				.loginPage("/auth/login")
				.failureUrl("/auth/login?error=true")
				.defaultSuccessUrl("/user")
				.usernameParameter("login_id")
				.passwordParameter("pwd") //
			.and().logout() //
				.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
	            .logoutSuccessUrl("/auth/login?logout=true")
				.invalidateHttpSession(true) //
			.and().exceptionHandling() //
				.accessDeniedPage("/auth/access-denied")
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
