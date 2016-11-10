package uk.co.boots.columbus.cmdb.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
				antMatchers(HttpMethod.OPTIONS, "/**"). //
				antMatchers("/"). //
				antMatchers("/*.{js,html}"). //
				antMatchers("/img/**"). //
				antMatchers("/node_modules/**"). //
				antMatchers("/**/*.{js,html,css}").antMatchers("/api/environments/configdownloadall/");
	}

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http. // csrf().disable(). // formLogin(). //
	 * loginProcessingUrl("/api/login"). // defaultSuccessUrl("/", true). //
	 * successHandler(ajaxAuthenticationSuccessHandler). //
	 * failureHandler(ajaxAuthenticationFailureHandler). //
	 * usernameParameter("j_username"). // passwordParameter("j_password"). //
	 * permitAll(). // and(). // logout(). // logoutUrl("/api/logout"). //
	 * logoutSuccessUrl("/"). // deleteCookies("JSESSIONID"). // permitAll(). //
	 * and(). // authorizeRequests(). //
	 * antMatchers("/api/authenticated").permitAll().//
	 * requestMatchers(CorsUtils::isCorsRequest).permitAll().
	 * antMatchers("/**").authenticated().
	 * antMatchers("/swagger-ui/index.html").hasAuthority("ROLE_ADMIN"); }
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			csrf().disable(). // don't need this - JWT token is better
			exceptionHandling().authenticationEntryPoint(unauthorizedHandler).
			and().// don't create session
			sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS). 
			and().// permit all CORS requests
			authorizeRequests().requestMatchers(CorsUtils::isCorsRequest).permitAll().
			antMatchers("/api/authenticated").permitAll().//
			antMatchers("/api/login").permitAll().
			antMatchers("/api/**/configdownload/**").permitAll(). // temp fix for download buttons
			antMatchers("/api/**/configdownloadall/**").permitAll(). // needs addressing in prod
			antMatchers("/api/currentUserAuthorities").authenticated().//
			antMatchers("/**").authenticated().antMatchers("/swagger-ui/index.html").hasAuthority("ROLE_ADMIN")
			.anyRequest().authenticated().and().httpBasic();

		http.addFilterBefore(corsFilter().getFilter(), ChannelProcessingFilter.class);
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		http.headers().cacheControl();
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3000");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/users/**", config);
		source.registerCorsConfiguration("/users/**/roles/**", config);
		source.registerCorsConfiguration("/users/*/roles/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
/*
	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}
*/
}
