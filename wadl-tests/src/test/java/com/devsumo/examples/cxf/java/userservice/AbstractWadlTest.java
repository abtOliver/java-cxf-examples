package com.devsumo.examples.cxf.java.userservice;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.jvnet.ws.wadl.util.MessageListener;
import org.jvnet.ws.wadl2java.Wadl2Java;
import org.jvnet.ws.wadl2java.Wadl2Java.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.sun.codemodel.writer.FileCodeWriter;

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
		// Determine the HTTP URL of our service WADL
		final String wadlUrl = "http://localhost:" +
				BeanUtils.getProperty(ctx.getBean(serviceBeanName), "server.destination.engine.connector.localPort") +
				rootUri + "/?_wadl";
		
		// Request and parse the WADL into a DOM model
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document wadlDoc = docBuilder.parse(new URL(wadlUrl).openStream());
		
		// Locate the XML grammar schemas via XPath
		XPath grammarSchemaXPath = XPathFactory.newInstance().newXPath();
		NodeList grammarSchemas = (NodeList)grammarSchemaXPath.evaluate("/application/grammars/schema",
		        wadlDoc.getDocumentElement(), XPathConstants.NODESET);
		
		// Assert that we have at least 1 grammar schema present
		assertTrue("No grammar schema located in service WADL", grammarSchemas.getLength() > 0);
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
		// Determine the HTTP URL of our service WADL
		final String wadlUrl = "http://localhost:" +
				BeanUtils.getProperty(ctx.getBean(serviceBeanName), "server.destination.engine.connector.localPort") +
				rootUri + "/?_wadl";
		
		// Create a scratch directory to generate the wadl2java client in
		final String clientTargetDirectoryName = "target/test-client";
		final File clientTargetDirectory = new File(clientTargetDirectoryName);
		clientTargetDirectory.mkdirs();
		
		// Define a message listener to collect events from wadl2java.
		final List<String> errorMessages = new ArrayList<>();
		final MessageListener wadl2JavaMessageListener = new MessageListener() {

			@Override
			public void warning(String message, Throwable throwable) {}

			@Override
			public void info(String message) {}
			
			@Override
			public void error(String message, Throwable throwable) {
				System.out.println(message + ": " + throwable.toString());
				errorMessages.add(message);
			}
		};
		
		// Configure the wadl2java parameters
		Parameters wadl2JavaParams = new Parameters();
		wadl2JavaParams.setRootDir(new URI("file:" + clientTargetDirectoryName));
		wadl2JavaParams.setCustomizations(Collections.emptyList());
		wadl2JavaParams.setMessageListener(wadl2JavaMessageListener);
		wadl2JavaParams.setXjcArguments(Collections.emptyList());
		wadl2JavaParams.setPkg("test");
		wadl2JavaParams.setCodeWriter(new FileCodeWriter(clientTargetDirectory));
		
		// Execute wadl2java
		new Wadl2Java(wadl2JavaParams).process(new URI(wadlUrl));
		
		// Assert that no errors occurred
		assertTrue("Unable to generate a client for the service WADL", errorMessages.isEmpty());
	}
}
