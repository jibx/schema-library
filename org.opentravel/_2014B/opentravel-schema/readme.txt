jibx-ota-osgi is a project to simplify working with opentravel.org messages.

For a quick demonstration of what this project does, build a sample project by typing:
(The backslashes just break up this really long command line)

mvn archetype:generate -DarchetypeGroupId=org.jibx.schema.org.opentravel.osgi \
-DarchetypeArtifactId=jibx-ota-osgi-archetype -DarchetypeVersion=1.2.3 \
-DgroupId=com.company.ota -DartifactId=my-artifact -Dversion=1.0.0 \
-Dpackage=com.company.ota -Dtravel-segment=hotel -DinteractiveMode=false

Then, build and run your new project:
cd my-artifact    ('dir my-artifact' for windows users)
mvn package
mvn exec:java

If you are using a IDE such as eclipse, the source code and documentation are automatically loaded also.

Now take a look at the source code in your new project. This sample program creates a hotel message using
normal java objects. It then converts the objects to an xml message and displays it. Next, this xml message
is converted back to a java object and some data is extracted and displayed.

The cool things about this project are:
1. It's easy to get started (no downloading schema, binding, etc)
2. Only the code you need will be included in your war/distribution file. (Maven takes care of this)
3. Only the code you use will be loaded by java (If you use osgi. If not, it still works fine)

So if your code creates a HotelAvailablility message, the hotel, profile, air, vehicle, and base schemas will be
included in your war file (just in case). The rest of the opentravel schema is not needed, so it is not included.
Only the parts of the schema that will be used are loaded in your runtime. When you decide to start creating
messages for rail, only the required modules will be added to your project.

-----

Usually, you will not need to build or access this project directly, just include the required artifact in your
pom.xml file (like the preceding example) and starting writing code.

------

Internal note: To deploy these modules to the maven repository, do the following:

ssh -t username,jibx@shell.sourceforge.net create
(Fix user permissions)
mvn -DperformRelease install
mvn deploy

-------

TODO:

My todo for the JiBX project:

1. Test mvn deploy
2. Change to current jibx poms:
	- Remove repository entries
        - Change to OSGi packaging
	- Include JiBX source and javadocs in dist
	- Change to sonatype deploy model
3. Create JiBX/WS maven plugin
4. Add service interfaces to jibx-ota-osgi project (and create wsdl files)
