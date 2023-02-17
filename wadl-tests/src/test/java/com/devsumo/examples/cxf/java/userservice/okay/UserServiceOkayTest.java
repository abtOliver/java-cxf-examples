package com.devsumo.examples.cxf.java.userservice.okay;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.devsumo.examples.cxf.java.userservice.AbstractWadlTest;

/**
 * Executes the WADL tests against the clean version of the UserService. All
 * three tests are expected to pass.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-beans.xml"})
public class UserServiceOkayTest extends AbstractWadlTest {
	public UserServiceOkayTest() {
		this.packageName = "com.devsumo.examples.cxf.java.userservice.okay";
		this.rootUri = "/okay";
		this.serviceBeanName = "okayUserService";
	}
}
