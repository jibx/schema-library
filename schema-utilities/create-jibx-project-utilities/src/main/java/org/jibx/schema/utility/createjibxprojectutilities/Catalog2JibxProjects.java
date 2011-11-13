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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.schema.org.apache.maven.maven_v4_0_0.Developer;
import org.jibx.schema.org.apache.maven.maven_v4_0_0.Model;
import org.jibx.schema.org.oasis_open.committees.entity.release._1_0.catalog.Catalog;
import org.jibx.schema.org.oasis_open.committees.entity.release._1_0.catalog.Catalog.Choice;
import org.jibx.schema.org.oasis_open.committees.entity.release._1_0.catalog.Uri;

/**
 * Runs the open-oasis catalog to schema-library plugin.
 *
 * @author                        <a href="mailto:don@tourgeek.com">Don Corley</a>
 * @goal scan-catalog
 * @execute phase="generate-sources"
 */
public class Catalog2JibxProjects extends BaseJibxProjectBuilder {

	public static final String JIBX_DOMAIN = "org.jibx";

	/**
     * The directory which contains binding files.
     * use bindingDirectory
     *
     * @parameter  expression="${catalogLocation}"
     * @required
     */
    private String catalogLocation;

	/**
     * Only build the schemas in the catalog that live in this domain.
     *
     * @parameter  expression="${targetDomain}"
     */
    private String targetDomain;

	/**
     * Main project name.
     *
     * @parameter  expression="${projectName}"
     */
    private String projectName;

	/**
     * Only build the schemas in the catalog that live in this domain.
     *
     * @parameter  expression="${projectDescription}"
     */
    private String projectDescription;

	/**
     * Developer name.
     *
     * @parameter  expression="${developerName}"
     */
    private String developerName;

	/**
     * Developer email.
     *
     * @parameter  expression="${developerEmail}"
     */
    private String developerEmail;

	/**
     * Organization name.
     *
     * @parameter  expression="${organizationName}"
     */
    private String organizationName;

	/**
     * Only build the schemas in the catalog that live in this domain.
     *
     * @parameter  expression="${organizationURL}"
     */
    private String organizationURL;

    public Catalog2JibxProjects(String catalogLocation, String targetDomain, String projectName, String projectDescription, String developerName, String developerEmail, String organizationName, String organizationURL)
    {
    	super();
    	if (catalogLocation != null) if (catalogLocation.length() > 0)
    		this.catalogLocation = catalogLocation;
    	if (targetDomain != null) if (targetDomain.length() > 0)
    		this.targetDomain =  targetDomain;
    	if (projectName != null) if (projectName.length() > 0)
    		this.projectName = projectName;
    	if (projectDescription != null) if (projectDescription.length() > 0)
    		this.projectDescription = projectDescription;
    	if (developerName != null) if (developerName.length() > 0)
    		this.developerName = developerName;
    	if (developerEmail != null) if (developerEmail.length() > 0)
    		this.developerEmail = developerEmail;
    	if (organizationName != null) if (organizationName.length() > 0)
    		this.organizationName = organizationName;
    	if (organizationURL != null) if (organizationURL.length() > 0)
    		this.organizationURL = organizationURL;
    }
    /**
     * Run.
     */
    public void execute()
    {
        Model mainProject = createParentProject(targetDomain);
        writeThisModel(mainProject.getArtifactId(), mainProject);

        Catalog catalog = getCatalog(catalogLocation);
        
        List<Choice> choices = catalog.getChoiceList();
        
        for (Choice choice : choices)
        {
        	Uri uri = choice.getUri();
        	if (uri != null)
        		if (uri.getName() != null)
        	{
        		try {
					URL url = new URL(uri.getName());
					if ((targetDomain == null) || (url.getHost().contains(targetDomain)))
					{
						String description = "Schema Library";
						if (organizationName != null)
							description += " - " + organizationName;
						description +=  " - " + uri.getUri();	// This is where the desc is?
		        		GenerateJibxProject project = new GenerateJibxProject(uri.getName(), mainProject.getArtifactId(), description, developerName, developerEmail);
		        		project.execute();						
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
        	}
        }
        
    }

    /**
     * 
     * @param targetDomain
     */
    public Model createParentProject(String targetDomain)
    {
        StringBuilder sbGroupId = new StringBuilder();

        String packageName = schemaLocationToPackage(targetDomain != null ? targetDomain :catalogLocation, sbGroupId);
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("META-INF/resources/parent/pom.xml");
        Model model = (Model)getBindingFromStream(Model.class, inStream);
        
        model.setArtifactId(packageName);
        model.setGroupId(JIBX_DOMAIN + ".schema." + sbGroupId.toString());
        if (projectName == null)
        	projectName = packageName;
        model.setName(projectName);
        if (projectDescription == null)
        	projectDescription = projectName;
        model.setDescription(projectDescription);
        
        if (organizationName != null)
        	model.getOrganization().setName(organizationName);
        if (organizationURL != null)
        	model.getOrganization().setUrl(organizationURL);
        
        Developer developer = model.getDevelopers().getDeveloperList().get(0);
        if (developerName != null)
        	developer.setName(developerName);
        if (developerEmail != null)
        	developer.setEmail(developerEmail);
        
        return model;
    }
    /**
     * Read in this catalog.
     * @param catalogLocation
     * @return
     */
    public Catalog getCatalog(String catalogLocation)
    {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(Catalog.class);
			IUnmarshallingContext unmarshaller = jc.createUnmarshallingContext();
			URL url = new URL(catalogLocation);
	        InputStream inStream = url.openStream();
	        Catalog message = (Catalog)unmarshaller.unmarshalDocument( inStream, URL_ENCODING);
			return message;
		} catch (JiBXException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
}
