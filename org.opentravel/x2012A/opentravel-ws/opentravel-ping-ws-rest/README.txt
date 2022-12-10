/*
  This example was copied from the cxf-jaxrs example. Thanks Apache!

  You will find extra details in the servicemix/examples/cxf-jaxrs/README.txt
  file in the servicemix distribution.
 */

Running a Client
----------------

You can browse WSDL at:

http://localhost:8181/cxf/ping?_wadl&_type=xml

You can see the services listing at http://localhost:8181/cxf.

You can make invocations on the web service in several ways, including
using a web client, using a Java client and using a command-line
utility such a curl or Wget. See below for more details.

(a) To run a web client:
    -------------------
Open a browser and go to the following URL:

   http://localhost:8181/cxf/ping/ping/Hello

It should display an XML representation echo of the message 'Hello'.

Note, if you use Safari, right click the window and select 'Show Source'.

(c) To run a command-line utility:
    -----------------------------
You can use a command-line utility, such as curl or Wget, to make 
the invocations. For example, try using curl as follows:

- Open a command prompt and change directory to
  <servicemix_home>/examples/cxf-jaxrs.
  
- Run the following curl commands:

  # Send a PingRQ xml message
  #
  curl -X POST -T src/main/resources/org/jibx/org/opentravel/ws/new_message.xml -H "Content-Type: text/xml" http://localhost:8181/cxf/ping/ping
  # Try running this twice. You should get an error the second time

  # Retrieve the ping response to 'Hello'
  #
  curl http://localhost:8181/cxf/ping/ping/Hello

Changing /cxf servlet alias
---------------------------
b. Use shell config commands, for example:

     config:edit org.apache.cxf.osgi   
     config:propset org.apache.cxf.servlet.context /super
     config:update

Note: To run this under karaf:
------------------------------
  
uninstall --force joda-time/1.6.2
install mvn:org.jibx.config.osgi.wrapped/org.jibx.config.osgi.wrapped.joda-time/1.6.2
install mvn:joda-time/joda-time/2.0
install mvn:org.jibx/jibx-run/1.2.4-SNAPSHOT
install mvn:org.jibx.schema.config/org.jibx.config.databinding/1.0.3-SNAPSHOT
install mvn:org.jibx.schema.org.opentravel.x2012A/org.jibx.schema.org.opentravel.x2012A.ping/1.0.3-SNAPSHOT
install mvn:org.jibx.schema.org.opentravel.x2012A/org.jibx.schema.org.opentravel.x2012A.base/1.0.3-SNAPSHOT
install mvn:org.jibx.schema.org.opentravel.ws/org.jibx.schema.org.opentravel.x2012A.ping.ws.service/1.0.3-SNAPSHOT
install mvn:org.jibx.schema.org.opentravel.ws/org.jibx.schema.org.opentravel.x2012A.ping.ws.rest/1.0.3-SNAPSHOT
install mvn:org.jibx.schema.org.opentravel.ws/org.jibx.schema.org.opentravel.x2012A.ping.ws.soap/1.0.3-SNAPSHOT
