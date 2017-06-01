package uk.co.boots.columbus.cmdb.model.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import uk.co.boots.columbus.cmdb.model.security.jwt.JwtAuthenticationEntryPoint;
import uk.co.boots.columbus.cmdb.model.security.jwt.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter();
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring(). //
				// let all CORS HTTP Option pre-flight messages go
				// our Filter will still get called though so we
				// ignore it and pass it on down the chain
				// also allow all swagger requests through
				antMatchers(HttpMethod.OPTIONS, "/**").
				antMatchers("/"). //
				antMatchers("/*.{js,html}").
				antMatchers("/v2/api-docs").
				antMatchers("/swagger-ui*").
				antMatchers("/swagger-resources*").
				antMatchers("/swagger-resources/**").
				antMatchers("/swagger-resources/configuration/security"). // token entry field
				antMatchers("/webjars/**");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			csrf().disable(). // don't need this - JWT token is better
			exceptionHandling().authenticationEntryPoint(unauthorizedHandler).
			and().// don't create session
			sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS). 
			and().
			authorizeRequests().antMatchers("/api/login").permitAll().
			// allow all login requests
			antMatchers("/api/users/**").hasRole("ADMIN").
			antMatchers("/api/roles/**").hasRole("ADMIN").
			antMatchers("/**").hasRole("USER").
			anyRequest().hasRole("USER").and().httpBasic();

		// Spring doesn't support JWT yet so we must add our Token check
		// as a Filter before the rest of the security layer kicks in 
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		// disable page caching
		http.headers().cacheControl();
	}
}
