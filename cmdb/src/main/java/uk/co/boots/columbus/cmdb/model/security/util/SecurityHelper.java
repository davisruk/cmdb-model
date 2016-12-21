package uk.co.boots.columbus.cmdb.model.security.util;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {
	public static String WRITE_SENSITIVE = "WRITE_SENSITIVE";
	public static String READ_SENSITIVE = "READ_SENSITIVE";
	
	public static boolean userCanViewSensitiveData(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	boolean allowSensitive = false;
		Collection<? extends GrantedAuthority> auths = auth.getAuthorities();
    	Optional<? extends GrantedAuthority> sensitive = auths.stream().filter(x -> x.getAuthority().equals(WRITE_SENSITIVE)).findFirst();
    	if (!sensitive.isPresent()){
    		sensitive = auths.stream().filter(x -> x.getAuthority().equals(READ_SENSITIVE)).findFirst();
    		if (sensitive.isPresent())
    			allowSensitive = true;
    	}else{
    		allowSensitive = true;
    	}
    	return allowSensitive;
	}
	
	public static boolean userCanWriteSensitiveData(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> auths = auth.getAuthorities();
    	Optional<? extends GrantedAuthority> sensitive = auths.stream().filter(x -> x.getAuthority().equals(WRITE_SENSITIVE)).findFirst();
   		return sensitive.isPresent();
	}
}
