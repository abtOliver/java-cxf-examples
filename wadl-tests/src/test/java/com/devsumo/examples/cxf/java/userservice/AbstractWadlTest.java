package com.devsumo.examples.cxf.java.userservice;

import static org.junit.Assert.fail;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Base class containing test methods for validating a service instance's WADL files
 */
public abstract class AbstractWadlTest {
	
	/** Package name for the service under test - used for the JAXB based test */
	protected String packageName = null;
	
	/** JAX-RS root path for the service under test */
	protected String rootUri = null;
	
	/** Service bean name for the service under test */
	protected String serviceBeanName = null;
	
	/** Injected Spring context */
	@Autowired
	ApplicationContext ctx;
	
	/** 
	 * Check we can create a JAXB context for our value objects. 
	 * Will detect a totally broken JAXB representation however not the more subtle
	 * problems that yield a WADL with grammars that won't import into client tools.
	 * Also requires that a jaxb.index is correctly maintained in the value object
	 * packages and the full list of packages is kept up-to-date in the unit test.
	 */
	@Test
	public void testCanCreateJAXBContext() {
		try {
			JAXBContext.newInstance(packageName);
		} catch(JAXBException eJaxb) {
			fail("Unable to create a JAXB context for the value objects: " + eJaxb.toString());
		}
	}
	
	/** 
	 * Request the service WADL and verify there are some XML grammars present
	 * Yields similar results to the JAXB test above in that it detects a broken JAXB context.
	 * The advantage over the previous method is that we do not need to manually maintain JAXB
	 * indices and package lists to keep this test in sync with the application. 
	 */
	@Test
	public void testWadlHasGrammars() throws Exception {
		final String wadlUrl = "http://localhost:" +
				BeanUtils.getProperty(ctx.getBean(serviceBeanName), "server.destination.engine.connector.localPort") +
				rootUri + 
				"/?_wadl";
		System.out.println(wadlUrl);
		// TODO: Request WADL
		// TODO: Parse and check for grammars
	}
	
	/** 
	 * Request the service WADL and feed it into a wadl2java implementation
	 * Proves that the WADL generated for the service can be processed by a wadl2java client but
	 * since a WADL is valid without grammars it needs to be used with one of the preceding
	 * tests for completeness.
	 * It does not provide a full integration test by trying to use the generated client in any way,
	 * merely establishes that one can be generated without error.
	 */
	@Test
	public void testWadlCanGenerate() throws Exception {
		final String wadlUrl = "http://localhost:" +
				BeanUtils.getProperty(ctx.getBean(serviceBeanName), "server.destination.engine.connector.localPort") +
				rootUri + 
				"/?_wadl";
		System.out.println(wadlUrl);
		// TODO: Request WADL
		// TODO: Launch wadl2java client - check for errors
	}
}
