package com.devsumo.examples.cxf.java.userservice.slightlybroken;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Nested value-object representing a note against a user as a label/comment pair.
 * 
 * The presence of an <code>XmlRootElement</code> annotation with the same namespace
 * as the parent object yields a valid JAXB context but an invalid WADL from CXF.
 */
@XmlRootElement(namespace="http://devsumo.com/examples/cxf/java/userservice")
public class UserNote {

	private String label = null;
	private String comment = null;
	
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
