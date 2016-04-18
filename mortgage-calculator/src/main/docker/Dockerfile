#
# Create a Docker image for the mortgage-calculator CXF WADL demo 
# application.
#
# Following a Maven build, run a "docker build ." from the target folder
# You can then run "docker run -p 8080:8080 <image-id> to start the application
#

FROM tomcat:8-jre7

MAINTAINER DevSumo <yokozuna@devsumo.com>

ADD ${project.build.finalName}.war /usr/local/tomcat/webapps/${project.artifactId}.war
