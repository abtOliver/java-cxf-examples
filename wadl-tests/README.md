WADL Tests
==========

Code to accompany [CXF - Validating JAX-RS WADL files in unit tests](http://www.devsumo.com/technotes/2015/09/cxf-validating-jax-rs-wadl-files-in-unit-tests)

This project contains three examples of a simple *UserService* which accepts basic user registration details in a JSON payload. All three versions successfully accept a hand-crafted JSON payload, however two of them illustrate problems in the JAXB model which yield a WADL that cannot successfully be imported into client generation tools.

#com.devsumo.examples.cxf.java.userservice.okay
This version of the service is fully working and generates a WADL which will fully import into tools like SoapUI.

If deployed to TomCat the WADL is located at [http://localhost:8080/wadl-tests/okay/?_wadl](http://localhost:8080/wadl-tests/okay/?_wadl) and the service can be interactively tested with the following curl command:-

`curl -w "\n%{http_code}\n" -T src/test/resources/okay.json -H "Content-Type: application/json" http://localhost:8080/wadl-tests/okay/users` 

#com.devsumo.examples.cxf.java.userservice.slightlybroken
This version of the service does not fundamentally break the JAXB rules and generates a WADL which appears valid but which will not import into client tools. It demonstrates that a simple test case to instantiate the JAXB model for the service misses some potential issues.

If deployed to TomCat the WADL is located at [http://localhost:8080/wadl-tests/slightlybroken/?_wadl](http://localhost:8080/wadl-tests/slightlybroken/?_wadl) and the service can be interactively tested with the following curl command:-

`curl -w "\n%{http_code}\n" -T src/test/resources/notokay.json -H "Content-Type: application/json" http://localhost:8080/wadl-tests/slightlybroken/users`

#com.devsumo.examples.cxf.java.userservice.totallybroken
This version of the service breaks JAXB rules so will generate a WADL with no grammar information.

If deployed to TomCat the WADL is located at [http://localhost:8080/wadl-tests/totallybroken/?_wadl](http://localhost:8080/wadl-tests/totallybroken/?_wadl) and the service can be interactively tested with the following curl command:-

`curl -w "\n%{http_code}\n" -T src/test/resources/notokay.json -H "Content-Type: application/json" http://localhost:8080/wadl-tests/totallybroken/users`

# Building and running
This project requires Java 8 and Maven 3. **A full Maven build is expected to fail** as the example unit tests to perform basic validation on the auto-generated WADLs are intended to pick up the issues with the broken versions of the service.

It yields a WAR file which can be deployed to a J2EE web application container to interactively test the service.

