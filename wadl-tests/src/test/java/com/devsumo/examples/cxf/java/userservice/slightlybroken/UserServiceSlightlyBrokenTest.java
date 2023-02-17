package com.devsumo.examples.cxf.java.userservice.slightlybroken;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.devsumo.examples.cxf.java.userservice.AbstractWadlTest;

/**
 * Executes the WADL tests against the subtly broken version of the UserService
 * Both JAXB and grammar tests will pass but the wadl2java test will fail since the
 * overall WADL is broken.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-beans.xml"})
public class UserServiceSlightlyBrokenTest extends AbstractWadlTest {
	public UserServiceSlightlyBrokenTest() {
		this.packageName = "com.devsumo.examples.cxf.java.userservice.slightlybroken";
		this.rootUri = "/slightlybroken";
		this.serviceBeanName = "slightlyBrokenUserService";		
	}
}
