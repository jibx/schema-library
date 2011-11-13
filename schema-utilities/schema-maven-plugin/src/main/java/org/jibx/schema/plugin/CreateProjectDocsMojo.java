/*
 * Copyright (c) 2004-2005, Dennis M. Sosnoski All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution. Neither the name of
 * JiBX nor the names of its contributors may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jibx.schema.plugin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.maven.archetype.mojos.CreateProjectFromArchetypeMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.schema.org.apache.maven.maven_v4_0_0.Model;
import org.jibx.schema.org.apache.maven.maven_v4_0_0.Model.Modules;
import org.apache.maven.plugin.AbstractMojo;

/**
 * Schema library documentation generator.
 *
 * @author                        <a href="mailto:don@tourgeek.com">Don Corley</a>
 * @goal scan-projects
 * @execute phase="pre-site"
 */
public class CreateProjectDocsMojo extends AbstractMojo {

    public final static String URL_ENCODING = "UTF-8";
    public final static String STRING_ENCODING = "UTF8";

    /**
     * The directory which contains main project.
     * defaults to {basedir}/../../
     *
     * @parameter  expression="${schemaProjectLocation}" default-value="${basedir}/../../"
     */
    private String schemaProjectLocation;

    /**
     * The path to place the consolidated project file.
     * Defaults to ${basedir}/../site/target/xml/projects.xml
     *
     * @parameter  expression="${catalogOut}" default-value="${basedir}/../site/target/xml/site.xml"
     */
    private String catalogOut;

    /**
     * Run
     */
    public void execute()
    	throws MojoExecutionException, MojoFailureException
    {
        try {
        	URL url = null;
            try {
				url = new URL(schemaProjectLocation + File.separator + "pom.xml");
			} catch (MalformedURLException e) {
				url = new File(schemaProjectLocation + File.separator + "pom.xml").toURL();
			}
			InputStream in = url.openStream();
			Model model = getProjectInfo(in);
			in.close();
			PrintStream printOut = getPrintStream(catalogOut);
			printOut.println("<projects>");
			
			writeThisModel(model, printOut);

			Modules modules = model.getModules();
			List<String> modulesList = modules.getModuleList();
			
			for (int i = modulesList.size() - 1; i >= 0 ;i--)
			{
				if (("site".equalsIgnoreCase(modulesList.get(i)))
						|| ("schema-library-parent".equalsIgnoreCase(modulesList.get(i)))
						|| ("schema-utilities".equalsIgnoreCase(modulesList.get(i))))
					modulesList.remove(i);
			}

			scanSubModules(modulesList, schemaProjectLocation, printOut);
			
			printOut.println("</projects>");

			printOut.flush();
			printOut.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 
     * @param modulesList
     * @param moduleLocation
     * @param printOut
     * @throws IOException
     */
	public void scanSubModules(List<String> modulesList, String moduleLocation, PrintStream printOut) throws IOException
	{
		printOut.println("<modules>");
		for (String module : modulesList)
		{
        	URL url = null;
            try {
				url = new URL(moduleLocation + File.separator + module + File.separator + "pom.xml");
			} catch (MalformedURLException e) {
				url = new File(moduleLocation + File.separator + module + File.separator + "pom.xml").toURL();
			}
			InputStream in = url.openStream();
			Model moduleModel = getProjectInfo(in);
			in.close();
			if (moduleModel == null)
				continue;
			
			printOut.println("<module>");

			writeThisModel(moduleModel, printOut);

			Modules schemaModules = moduleModel.getModules();
			if (schemaModules != null)
			{
				List<String> schemaModulesList = schemaModules.getModuleList();
				
				scanSubModules(schemaModulesList, moduleLocation + File.separator + module, printOut);
			}
			
			printOut.println("</module>");
		}
		printOut.println("</modules>");
	}
    
    /**
     * Read the template into the model.
     * @return
     */
    public Model getProjectInfo(InputStream inStream)
    {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(Model.class);
			IUnmarshallingContext unmarshaller = jc.createUnmarshallingContext();
			Model message = (Model)unmarshaller.unmarshalDocument( inStream, URL_ENCODING);
			return message;
		} catch (JiBXException e) {
			e.printStackTrace();
		}
    	return null;
    }
    /**
     * Write this model using this project name.
     * @param fileOut
     * @param model
     */
    PrintStream getPrintStream(String fileOut)
    {
    	PrintStream out = null;
    	int dirIndex = fileOut.lastIndexOf(File.separator);
    	if (dirIndex == -1)
    		dirIndex = fileOut.lastIndexOf("\\");
    	if (dirIndex != -1)
    		new File(fileOut.substring(0, dirIndex)).mkdirs();
	    File file = new File(fileOut);
	    if (file.exists())
	    	file.delete();
	    try {
	    	FileOutputStream outStream = new FileOutputStream(file);
			out = new PrintStream(outStream);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return out;
    }
    /**
     * Write this model to this outstream.
     * @param message
     * @param out
     * @return
     */
    public boolean writeThisModel(Model message, PrintStream out)
    {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(Model.class);
			IMarshallingContext marshaller = jc.createMarshallingContext();
			marshaller.setIndent(4);
			ByteArrayOutputStream outb = new ByteArrayOutputStream();
			marshaller.marshalDocument(message, URL_ENCODING, null, outb);
			String xml = outb.toString(STRING_ENCODING);
			xml = stripProcessingInstructions(xml);
			out.println(xml);
			return true;	// Success
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		return false;
    }
    public String stripProcessingInstructions(String xml)
    {
    	while (true)
    	{
    		int startInstr = xml.indexOf("<?");
    		int endInstr = xml.indexOf("?>", startInstr);
    		if ((startInstr == -1) || (endInstr == -1))
    			break;
    		if (xml.charAt(endInstr + 2) == '\n')
    			endInstr++;
    		xml = xml.substring(0, startInstr) + xml.substring(endInstr + 2);
    	}
    	return xml;
    }
}
