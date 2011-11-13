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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jibx.schema.org.apache.maven.maven_v4_0_0.Developer;
import org.jibx.schema.org.apache.maven.maven_v4_0_0.Model;
import org.jibx.schema.org.apache.maven.maven_v4_0_0.Model.Developers;
import org.jibx.schema.org.apache.maven.maven_v4_0_0.Model.Modules;
import org.jibx.schema.org.apache.maven.maven_v4_0_0.Plugin;
import org.jibx.schema.org.apache.maven.maven_v4_0_0.Plugin.Configuration;
import org.w3c.dom.Element;

/**
 * Auto-build a JiBX binding project given the schema URL.
 *
 * @author                        <a href="mailto:don@tourgeek.com">Don Corley</a>
 * @goal generate
 * @execute phase="generate-sources"
 */
public class GenerateJibxProject extends BaseJibxProjectBuilder {

	public static final String JIBX_DOMAIN = "org.jibx";
    /**
     * The directory which contains binding files.
     * use bindingDirectory
     *
     * @parameter  expression="${schemaLocation}"
     * @required
     */
    private String schemaLocation;

    /**
     * The Parent Project Directory.
     *
     * @parameter  expression="${parentProjectDirectory}"
     */
    private String parentProjectDirectory;

    /**
     * The directory which contains binding files.
     * use bindingDirectory
     *
     * @parameter  expression="${description}"
     */
    private String description;

    /**
     * Your email.
     *
     * @parameter  expression="${developerEmail}"
     */
    private String developerEmail;

    /**
     * Your name.
     *
     * @parameter  expression="${developerName}"
     */
    private String developerName;

    /**
     * Don't use this, version will default to the correct jibx version.
     *
     * @parameter  expression="${version}"
     */
    private String version;

    public GenerateJibxProject(String schemaLocation, String parentProjectDirectory, String description, String developerName, String developerEmail)
    {
    	this();
    	if (schemaLocation != null) if (schemaLocation.length() > 0)
    		this.schemaLocation = schemaLocation;
    	if (parentProjectDirectory != null) if (parentProjectDirectory.length() > 0)
    		this.parentProjectDirectory = parentProjectDirectory;
    	if (description != null) if (description.length() > 0)
    		this.description = description;
    	if (developerName != null) if (developerName.length() > 0)
    		this.developerName = developerName;
    	if (developerEmail != null) if (developerEmail.length() > 0)
    		this.developerEmail = developerEmail;
    }

    public GenerateJibxProject()
    {
    	super();
    }

    /**
     * Run
     */
    public void execute()
    {
        // Too bad all the params are private, I would love to do:
        // super.execute();
        
        StringBuilder sbGroupId = new StringBuilder();
        String packageName = schemaLocationToPackage(schemaLocation, sbGroupId);
        
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("META-INF/resources/pom.xml");
        Model model = (Model)getBindingFromStream(Model.class, inStream);
        
        // Now you have the template, customize it, then write it out.
        String groupId = JIBX_DOMAIN + ".schema." + sbGroupId.toString();
        String artifactId = JIBX_DOMAIN + ".schema." + packageName;
        customizeModel(groupId, artifactId, model);
        
        writeThisModel(parentProjectDirectory == null ? artifactId : parentProjectDirectory + '/' + artifactId, model);
        if (parentProjectDirectory != null)
        	addModuleToProject(parentProjectDirectory, artifactId);
    }
    /**
     * Set the params in the model.
     * @param groupId
     * @param artifactId
     * @param model
     */
    void customizeModel(String groupId, String artifactId, Model model)
    {
	    model.setGroupId(groupId);
	    model.setArtifactId(artifactId);
	    if (version != null)
	    	model.setVersion(version);
	    model.setName(artifactId);		// For now
	    if (description != null)
	    	model.setDescription(description);
	    if ((developerEmail != null) || (developerName != null))
	    {
		    Developers developers = new Developers();
		    model.setDevelopers(developers);
		    List<Developer> developerList = new ArrayList<Developer>();
		    developers.setDeveloperList(developerList);
		    Developer developer = new Developer();
		    developerList.add(developer);
		    if (developerEmail != null)
		    	developer.setEmail(developerEmail);
		    if (developerName != null)
		    	developer.setName(developerName);
	    }
	    
	    List<Plugin> plugins = model.getBuild().getPlugins().getPluginList();
	    for (Plugin plugin : plugins)
	    {
	    	if ("jibx-maven-plugin".equals(plugin.getArtifactId()))
	    	{
	    		Configuration configuration = plugin.getConfiguration();
	    		List<Element> elements = configuration.getAnyList();
	    		for (int index = 0; index < elements.size(); index ++)
	    		{
	    			if (elements.get(index) instanceof Element)
	    			{
	    				Element element = (Element)elements.get(index);
	        			if ("schemaLocation".equals(element.getTagName()))
	        				element.setTextContent(schemaLocation);
	    			}
	    		}
	    	}
	    }
    }
    /**
     * 
     * @param parentProjectDirectory
     * @param artifactId
     */
    public void addModuleToProject(String parentProjectDirectory, String artifactId)
    {
    	try {
			InputStream inStream = new FileInputStream(parentProjectDirectory + File.separator + "pom.xml");
			Model model = (Model)getBindingFromStream(Model.class, inStream);
			
			Modules modules = model.getModules();
			if (modules == null)
				model.setModules(modules = new Modules());
			List<String> moduleList = modules.getModuleList();
			if (moduleList == null)
				modules.setModuleList(moduleList = new ArrayList<String>());
			for (String module : moduleList)
			{
				if (artifactId.equalsIgnoreCase(module))
					return;
			}
			moduleList.add(artifactId);
			
			writeThisModel(parentProjectDirectory, model);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}
