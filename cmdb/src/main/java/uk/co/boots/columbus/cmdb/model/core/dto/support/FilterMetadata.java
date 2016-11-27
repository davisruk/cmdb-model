package uk.co.boots.columbus.cmdb.model.core.dto.support;

import org.springframework.data.domain.ExampleMatcher;

public class FilterMetadata {
	public String value;
	public String matchMode;
	
	public ExampleMatcher.GenericPropertyMatcher getMatcher(ExampleMatcher.GenericPropertyMatcher match){
		if (matchMode.equals("contains"))
			return match.contains();
		else if (matchMode.equals("endswith"))
			return match.endsWith();
		else if (matchMode.equals("startswith"))
			return match.startsWith();
		return match.exact();
	}
}
