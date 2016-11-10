package uk.co.boots.columbus.cmdb.model.security.jwt.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uk.co.boots.columbus.cmdb.model.repository.UserRepository;
import uk.co.boots.columbus.cmdb.model.security.jwt.JwtAuthenticationRequest;
import uk.co.boots.columbus.cmdb.model.security.jwt.JwtTokenUtil;
import uk.co.boots.columbus.cmdb.model.security.jwt.service.JwtAuthenticationResponse;

@RestController
@RequestMapping("/api")
public class SecurityResource {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	UserRepository userRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value = "/loggedinuser", method = GET, produces = APPLICATION_JSON_VALUE)
	public UserDetails getLoggedInUserDetails(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		if (token != null){
			return jwtTokenUtil.getUserDetailsFromToken(token);
		}
		return null;

	}

	@RequestMapping(value = "/currentUserAuthorities", method = GET, produces = APPLICATION_JSON_VALUE)
	public Collection<? extends GrantedAuthority> getCurrentUserAuthorities(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		if (token != null) {
			UserDetails user = jwtTokenUtil.getUserDetailsFromToken(token);
			return user.getAuthorities();
		}
		return null;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			Device device) throws AuthenticationException {

		// Perform the security
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));
		// this is unlikely to have an effect as we are STATELESS
		// the authentication will only be valid for the REQUEST_SCOPE
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-security so we can generate token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails, device);

		// Return the token
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	@RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserDetails user = (UserDetails) userDetailsService.loadUserByUsername(username);
		// temp fix until LastPasswordResetDate is added to user
		// if (jwtTokenUtil.canTokenBeRefreshed(token,
		// user.getLastPasswordResetDate())) {
		if (jwtTokenUtil.canTokenBeRefreshed(token, new Date(System.currentTimeMillis()))) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
}