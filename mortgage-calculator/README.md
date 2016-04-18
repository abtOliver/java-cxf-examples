Mortgage Calculator
===================

Code to accompany [CXF – Missing WADL method parameter element types with JSON JAX-RS services](http://www.devsumo.com/technotes/2015/01/cxf-missing-wadl-method-parameter-element-types-with-json-jax-rs-services/)

This project implements a stub mortgage payment calculator Java-first JSON REST service which accepts a set of request fields in a JSON payload. It utilises CXF's WADL generator to expose a "service contact" which tools like SoapUI can process and generate skeleton JSON request payloads from.

There is a containerised [Docker](https://hub.docker.com/r/devsumo/cxf-mortgage-calculator/) image of this code running on Tomcat 8 for anyone struggling to get the code to work locally and who wishes to compare with a working environment. If you have Docker installed you can run the code with:-

`docker run devsumo/cxf-mortgage-calculator`

And then navigate to [http://localhost:8080/mortgage-calculator/?_wadl](http://localhost:8080/mortgage-calculator/?_wadl) to see the WADL or import into SoapUI. If running in a *docker-machine* environment on Windows or OS X you will probably want to expose port 8080:-

`docker run -p 8080:8080 devsumo/cxf-mortgage-calculator`

And replace *localhost* with your Docker machine's IP. 
