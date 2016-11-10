package uk.co.boots.columbus.cmdb.model.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String authToken = request.getHeader(this.tokenHeader);
		UserDetails userDeets = jwtTokenUtil.getUserDetailsFromToken(authToken);
		logger.info("request url is: " + request.getRequestURL());
		logger.info("checking authentication for user " + (userDeets == null ? "none" : userDeets.getUsername()));
		if (userDeets != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// It is not necessary to load the user details from the database.
			// We have stored the credentials in the token.
			// For simple validation it is sufficient to just check the token
			// integrity
			// i.e. did we issue it and has it expired
			// Here we don't need to check the user details but the
			// utility copes with the fact we may use it after getting the
			// details from a database rather than the token so we have to send
			// them
			if (jwtTokenUtil.validateToken(authToken, userDeets)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDeets,
						null, userDeets.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("authenticated user " + userDeets.getUsername() + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}
}