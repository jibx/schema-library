JiBX opentravel.org web service example
=========================

Purpose
-------
Create a web service with CXF and expose it through the OSGi HTTP Service.


Explanation
-----------
The web service is a simple JAX-WS web service called TourActivity.

The blueprint.xml file, located in the src/main/resources/META-INF/blueprint
directory:

1. Imports the configuration files needed to enable CXF and OSGi work
   together.

2. Configures the web service endpoint as follows:

  <jaxws:endpoint id="touractivity"
         implementor="org.apache.servicemix.examples.cxf.HelloWorldImpl"
         address="/soap/touractivity"/>


Prerequisites for Running the Example
-------------------------------------
1. You must have the following installed on your machine:

   - JDK 1.5 or higher
   
   - Maven 2.0.9 or higher
   
  For more information, see the README in the top-level examples
  directory.


2. Start ServiceMix by running the following command:

  <servicemix_home>/bin/servicemix          (on UNIX)
  <servicemix_home>\bin\servicemix          (on Windows)

Running the Example
-------------------
To install and run the example where you build the example bundle
yourself, complete the following steps:

1. Build the example by opening a command prompt, changing directory to
   opentravel-touractivity-service (this example) and enter the following Maven
   command:

     mvn install
   
   If all of the required OSGi bundles are available in your local
   Maven repository, the example will build very quickly. Otherwise
   it may take some time for Maven to download everything it needs.
   
   The mvn install command builds the example deployment bundle and
   copies it to your local Maven repository and to the target directory
   of this example.
     
3. Install the example by entering the following commands in
   the ServiceMix console:
   
     features:install obr
     obr:addurl http://www.jibx.org/repository.xml
     obr:deploy 'Schema Library - org.opentravel.touractivity - 2011B - opentravel.org touractivity schema'
     install mvn:org.jibx.schema.org.opentravel.ws/org.jibx.schema.org.opentravel.x2011B.touractivity.ws/1.0.3-SNAPSHOT
     (remember the Bundle ID)
     ctrl+d (stop servicemix)
     bin/servicemix (restart servicemix)
     start 222 (replace 222 with the Bundle ID displayed above) 
       
   It makes use of the ServiceMix features facility. For more
   information about the features facility, see the README.txt file
   in the examples parent directory.

To view the service WSDL, open your browser and go to the following
URL:

  http://localhost:8092/soap/touractivity?wsdl

Note, if you use Safari, right click the window and select
'Show Source'.

You can try running a client against your service by following the
instructions in the "Running a Client" section above.


Stoptouractivity and Uninstalling the Example
-------------------------------------
To stop the example, you must first know the bundle ID that ServiceMix
has assigned to it. To get the bundle ID, enter the following command
at the ServiceMix console:

  osgi:list

At the end of the listing, you should see an entry similar to the
following:

  [170] [Active     ] [Started] [  60] JiBX opentravel touractivity service engine (1.0.3.SNAPSHOT)

In this case, the bundle ID is 170.

To stop the example, enter the following command at the ServiceMix
console:

  osgi:stop <bundle_id>

For example:

  osgi:stop 170

To uninstall the example, enter one of the following commands in
the ServiceMix console:

  features:uninstall examples-cxf-osgi
 
or
 
  osgi:uninstall <bundle_id>
  

Viewing the Log Entries
-----------------------
You can view the entries in the log file in the data/log
directory of your ServiceMix installation, or by tytouractivity
the following command in the ServiceMix console:

  log:display
  
Changing the web service url
----------------------------

You can change the web service url by changing the configuration
property for the web address.

In karaf, change the httpaddress property by tytouractivity:
  config:edit org.jibx.ws.touractivity
  config:propset httpAddress http://localhost:8082/soap/touractivity
  config:update
  
You must restart the bundle for the change to take place.
 
