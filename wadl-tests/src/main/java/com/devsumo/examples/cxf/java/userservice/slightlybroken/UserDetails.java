package com.devsumo.examples.cxf.java.userservice.slightlybroken;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

/** 
 * Simple value-object representing an identifier, full name and set of notes for
 * a user.
 */
@XmlRootElement(namespace="http://devsumo.com/examples/cxf/java/userservice")
public class UserDetails {

	private String userName = null;
	private String fullName = null;
	private Set<UserNote> notes = null;
	
	public UserDetails() {
	}

	public String getUserName() {
		return userName;
	}

	public String getFullName() {
		return fullName;
	}
	
	public Set<UserNote> getNotes() {
		return notes;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public void setNotes(Set<UserNote> notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
}
