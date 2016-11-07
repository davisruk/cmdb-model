/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/config/SecurityConfiguration.java.p.vm
 */
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import uk.co.boots.columbus.cmdb.model.security.AjaxAuthenticationFailureHandler;
import uk.co.boots.columbus.cmdb.model.security.AjaxAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Autowired
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordEncoder(){
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
                antMatchers("/**/*.{js,html,css}").
                antMatchers("/api/environments/configdownloadall/");
    }
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http. //
        		csrf().disable(). //
                formLogin(). //
                loginProcessingUrl("/api/login"). //
                defaultSuccessUrl("/", true). //
                successHandler(ajaxAuthenticationSuccessHandler). //
                failureHandler(ajaxAuthenticationFailureHandler). //
                usernameParameter("j_username"). //
                passwordParameter("j_password"). //
                permitAll(). //
                and(). //
                logout(). //
                logoutUrl("/api/logout"). //
                logoutSuccessUrl("/"). //
                deleteCookies("JSESSIONID"). //
                permitAll(). //
                and(). //
                authorizeRequests(). //
                antMatchers("/api/authenticated").permitAll().//
                requestMatchers(CorsUtils::isCorsRequest).permitAll().
                antMatchers("/**").authenticated().
                antMatchers("/swagger-ui/index.html").hasAuthority("ROLE_ADMIN");
    }
 
*/ 
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.
				csrf().disable(). //
				formLogin(). //
				loginProcessingUrl("/api/login"). //
				defaultSuccessUrl("/", true). //
				successHandler(ajaxAuthenticationSuccessHandler). //
				failureHandler(ajaxAuthenticationFailureHandler). //
				usernameParameter("j_username"). //
				passwordParameter("j_password"). //
				permitAll(). //
				and(). //
				logout(). //
				logoutUrl("/api/logout"). //
				logoutSuccessUrl("/"). //
				deleteCookies("JSESSIONID"). //
				permitAll(). //
				and(). //
				authorizeRequests().
                requestMatchers(CorsUtils::isCorsRequest).permitAll().
                antMatchers("/api/authenticated").permitAll().//
                antMatchers("/api/**/configdownload/**").permitAll(). // temp fix for download buttons
                antMatchers("/api/**/configdownloadall/**").permitAll(). // needs addressing in prod
                antMatchers("/**").authenticated().
                antMatchers("/swagger-ui/index.html").hasAuthority("ROLE_ADMIN").
                anyRequest().authenticated().
                and().
                httpBasic().
                and().
                addFilterBefore(corsFilter().getFilter(), ChannelProcessingFilter.class);
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
/*        
        source.registerCorsConfiguration("/api/server/**", config);
        source.registerCorsConfiguration("/api/environment/**", config);
        source.registerCorsConfiguration("/api/packageInfo/**", config);
        source.registerCorsConfiguration("/api/componentConfig/**", config);
        source.registerCorsConfiguration("/api/globalconfig/**", config);
        source.registerCorsConfiguration("/api/packageType/**", config);
        source.registerCorsConfiguration("/api/environmentConfig/**", config);
        source.registerCorsConfiguration("/api/serverConfig/**", config);
        source.registerCorsConfiguration("/api/solutionComponent/**", config);
        source.registerCorsConfiguration("/api/releaseData/**", config);
        source.registerCorsConfiguration("/api/releaseDataType/**", config);
        source.registerCorsConfiguration("/api/serverType/**", config);
        source.registerCorsConfiguration("/api/release/**", config);
*/
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
     
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
