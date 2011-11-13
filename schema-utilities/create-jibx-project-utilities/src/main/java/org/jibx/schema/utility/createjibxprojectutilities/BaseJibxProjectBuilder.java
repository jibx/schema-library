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

package org.jibx.schema.utility.createjibxprojectutilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.schema.org.apache.maven.maven_v4_0_0.Model;

/**
 * Runs the open-oasis catalog to schema-library plugin.
 *
 * @author                        <a href="mailto:don@tourgeek.com">Don Corley</a>
 */
public abstract class BaseJibxProjectBuilder extends Object { //CreateProjectFromArchetypeMojo {

    public final static String URL_ENCODING = "UTF-8";
    public final static String STRING_ENCODING = "UTF8";

    public BaseJibxProjectBuilder()
    {
    	super();
    }

    /**
     * Run
     */
    public void execute()
    {
    	
    }
    
    /**
     * Calculate the groupId and artifactId.
     * @param schemaLocation
     * @param sbGroupId
     * @return
     */
    public String schemaLocationToPackage(String schemaLocation, StringBuilder sbGroupId)
    {
    	String host = schemaLocation;
    	String pathRoot = "";
    	try {
			URL url = new URL(schemaLocation);
			host = url.getHost();
			pathRoot = url.getPath();
		} catch (MalformedURLException e) {
			// Ignore
		}
    	StringBuilder packageName = new StringBuilder();
		String[] domains = host.split("\\.");
		for (int i = domains.length - 1; i >= 0; i--) {
			if (i == 0)
				if ("www".equalsIgnoreCase(domains[i]))
					break;
			if (packageName.length() > 0)
				packageName.append('.');
			if (Character.isDigit(domains[i].charAt(0)))
				packageName.append('_');
			packageName.append(stringToPackageName(domains[i]));
		}
		sbGroupId.append(packageName);	// Artifact Id

		if ((pathRoot.endsWith(".xsd"))
			|| (pathRoot.endsWith(".xml"))
			|| (pathRoot.endsWith(".html")))
				pathRoot = pathRoot.substring(0, pathRoot.lastIndexOf('.'));
		pathRoot = pathRoot.replace('.', '_');	// Usually version numbers
		String[] paths = pathRoot.split("/");
		for (String path : paths) {
			if (path.length() == 0)
				continue;
			if (Character.isDigit(path.charAt(0)))
				path = "_" + path;
			path = stringToPackageName(path);
			if (packageName.length() > 0)
				packageName.append('.');
			packageName.append(path);
		}
    	return packageName.toString();
    }
    /**
     * Make this string a valid package name.
     * @param packageName
     * @return
     */
    public String stringToPackageName(String packageName)
    {
		for (int i = 0; i < packageName.length(); i++)
		{
			char pathChar = packageName.charAt(i);
			if (pathChar != '_')
				if (!Character.isLetterOrDigit(pathChar))
					packageName = packageName.replace(pathChar, '_');
		}
    	return packageName;
    }
    /**
     * Read the template into the model.
     * @return
     */
    public Object getBindingFromStream(Class<?> bindingClass, InputStream inStream)
    {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(bindingClass);
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
     * @param artifactId
     * @param model
     */
    public void writeThisModel(String artifactId, Model model)
    {
	    File dir = new File(artifactId);
	    dir.mkdirs();
	    File file = new File(dir, "pom.xml");
	    if (file.exists())
	    	file.delete();
	    try {
			OutputStream out = new FileOutputStream(file);
	
			writeThisModel(model, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    /**
     * Write this model to this outstream.
     * @param message
     * @param out
     * @return
     */
    public boolean writeThisModel(Model message, OutputStream out)
    {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(Model.class);
			IMarshallingContext marshaller = jc.createMarshallingContext();
			marshaller.setIndent(4);
//			ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.marshalDocument(message, URL_ENCODING, null, out);
//			String xml = out.toString(STRING_ENCODING);
			return true;	// Success
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		return false;
    }

}
