package com.devsumo.examples.cxf.java.userservice.totallybroken;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.devsumo.examples.cxf.java.userservice.AbstractWadlTest;

/**
 * Execute the WADL tests against the totally broken version of the service
 * Since the JAXB model is invalid and there are no grammars generated this breaks
 * two of our unit test cases; but the wadl2java test will pass because the WADL
 * is valid without grammars.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-beans.xml"})
public class UserServiceBrokenTest extends AbstractWadlTest {
	public UserServiceBrokenTest() {
		this.packageName = "com.devsumo.examples.cxf.java.userservice.totallybroken";
		this.rootUri = "/totallybroken";
		this.serviceBeanName = "totallyBrokenUserService";				
	}
}
