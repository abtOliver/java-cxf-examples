package com.devsumo.examples.cxf.java.userservice.totallybroken;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Nested value-object representing a note against a user (label and comment)
 * 
 * The presence of public properties along with getters/setters of the same name
 * breaks the JAXB context.
 */
public class UserNote {

	public String label = null;
	public String comment = null;
	
	public UserNote() {
	}

	public String getLabel() {
		return label;
	}

	public String getComment() {
		return comment;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
