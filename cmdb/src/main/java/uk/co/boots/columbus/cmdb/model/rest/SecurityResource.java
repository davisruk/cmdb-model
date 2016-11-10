package uk.co.boots.columbus.cmdb.model.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.boots.columbus.cmdb.model.repository.UserRepository;
import uk.co.boots.columbus.cmdb.model.security.UserContext;
import uk.co.boots.columbus.cmdb.model.security.jwt.JwtTokenUtil;

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
	private UserDetailsService userDetailsService;
	
	@RequestMapping(value = "/authenticated", method = GET, produces = APPLICATION_JSON_VALUE)
	public boolean isAuthenticated() {
		return UserContext.getId() != null;
	}

	@RequestMapping(value = "/loggedinuser", method = GET, produces = APPLICATION_JSON_VALUE)
	public UserDetails getLoggedInUserDetails() {
		return UserContext.getUserDetails();
	}

	@RequestMapping(value = "/currentUserAuthorities", method = GET, produces = APPLICATION_JSON_VALUE)
	public Collection<? extends GrantedAuthority> getCurrentUserAuthorities(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDetails user = userDetailsService.loadUserByUsername(username);
		return user.getAuthorities();
	}
}