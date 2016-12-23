package com.proxiad.extranet.core.model.param;

/**
 * Declaration of all parameters
 * 
 * @author Mihail Merkov
 */
public enum ParamName {
	/** Images folder destination path **/
	IMAGES_SOURCE_PATH("IMAGES_SOURCE_PATH"),

	/** Folder name where will be deployed all profile pictures **/
	BASE_PROFILE_IMAGES_FOLDER_NAME("BASE_PROFILE_IMAGES_FOLDER_NAME");
	
	private String paramName;
	
	private ParamName(final String paramName) {
		this.paramName = paramName;
	}
	
	public String getValue() {
		return paramName;
	}
}
