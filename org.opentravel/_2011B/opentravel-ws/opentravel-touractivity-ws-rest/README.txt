/*
  This example was copied from the cxf-jaxrs example. Thanks Apache!
  You will find extra details in the servicemix/examples/cxf-jaxrs/README.txt
  file in the servicemix distribution.
 */

CXF JAX-RS EXAMPLE
==================

Purpose
-------
Create a RESTful JAX-RS web service using CXF and expose it with the 
OSGi HTTP Service.

Prerequisites for Running the Example
-------------------------------------
1. You must have the following installed on your machine:

   - JDK 1.6 or higher
   - Maven 2.2.1 or higher
   
  For more information, see the README in the top-level examples
  directory.

2. Start ServiceMix by running the following command:

  <servicemix_home>/bin/servicemix          (on UNIX)
  <servicemix_home>\bin\servicemix          (on Windows)

(a) To run a web client:
    -------------------
Open a browser and go to the following URL:

   http://localhost:8181/cxf/touractivity/avail/cityss/2012-05-12

It should display an XML availability response message for this tour and date.

Note, if you use Safari, right click the window and select 'Show Source'.

(c) To run a command-line utility:
    -----------------------------
You can use a command-line utility, such as curl or Wget, to make 
the invocations. For example, try using curl as follows:

- Open a command prompt and change directory to
  <servicemix_home>/examples/cxf-jaxrs.
  
- Run the following curl commands:

  # Check availability and pricing
  #
  curl -X POST -T src/main/resources/org/jibx/org/opentravel/ws/avail_message.xml -H "Content-Type: text/xml" http://localhost:8181/cxf/touractivity/avail


Note: To run this under karaf:
------------------------------

uninstall --force joda-time/1.6.2
install mvn:org.jibx.config.osgi.wrapped/org.jibx.config.osgi.wrapped.joda-time/1.6.2
install mvn:joda-time/joda-time/2.0
install mvn:org.jibx.config.osgi.wrapped/org.jibx.config.osgi.wrapped.org.apache.cxf.jibx/2.4.1

features:install obr
sleep 4000
obr:addurl http://www.jibx.org/repository.xml
repository=http://www.jbundle.org:8081/nexus/content/repositories/repository
repository=file:/home/don/workspace/workspace/bin/obr
obr:addurl file:/home/don/.m2/snapshot-repository.xml

obr:deploy org.jibx.schema.org.opentravel._2011B.touractivity.ws.service
obr:deploy org.jibx.schema.org.opentravel._2011B.touractivity.ws.soap
obr:deploy org.jibx.schema.org.opentravel._2011B.touractivity.ws.rest
obr:deploy org.jibx.schema.org.opentravel._2011B.ping.ws.service
obr:deploy org.jibx.schema.org.opentravel._2011B.ping.ws.soap
obr:deploy org.jibx.schema.org.opentravel._2011B.ping.ws.rest
