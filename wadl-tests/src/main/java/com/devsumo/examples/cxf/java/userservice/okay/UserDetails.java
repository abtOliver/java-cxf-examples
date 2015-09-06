package com.devsumo.examples.cxf.java.userservice.okay;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Simple value-object containing the identifier and full name of a user
 */
@XmlRootElement(namespace="http://devsumo.com/examples/cxf/java/userservice")
public class UserDetails {

	private String userName = null;
	private String fullName = null;
	
	public UserDetails() {
	}

	public String getUserName() {
		return userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
}
