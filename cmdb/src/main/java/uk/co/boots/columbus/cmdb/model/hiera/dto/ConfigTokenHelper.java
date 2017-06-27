package uk.co.boots.columbus.cmdb.model.hiera.dto;

public class ConfigTokenHelper {

	private static String getStringWithCorrectCase (String original, String token, String replace){
		// []-toUpper
		// {} - asIs
		// <> - toLower

		String retVal = original;

		if (original.contains("{" + token + "}"))
			retVal = retVal.replaceAll("\\{" + token + "\\}", replace);
		if (original.contains("[" + token + "]"))
			retVal = retVal.replaceAll("\\[" + token + "\\]", replace.toUpperCase());
		if (original.contains("<" + token + ">"))
			retVal = retVal.replaceAll("\\<" + token + "\\>", replace.toLowerCase());
		return retVal;
	}
	public static CoreConfigDTO replaceTags (CoreConfigDTO dto, String token, String replaceValue){
		dto.setHieraAddress(getStringWithCorrectCase(dto.getHieraAddress(), token, replaceValue));
		dto.setValue(getStringWithCorrectCase(dto.getValue(), token, replaceValue));		
		return dto;
	}

}
