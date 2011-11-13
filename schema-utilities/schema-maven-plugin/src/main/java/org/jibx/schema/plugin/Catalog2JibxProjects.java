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
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

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
     * Only build the schemas in the catalog that live in this domain.
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
     * Only build the schemas in the catalog that live in this domain.
     *
     * @parameter  expression="${developerName}"
     */
    private String developerName;

	/**
     * Only build the schemas in the catalog that live in this domain.
     *
     * @parameter  expression="${developerEmail}"
     */
    private String developerEmail;

	/**
     * Only build the schemas in the catalog that live in this domain.
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

    /**
     * Run.
     */
    public void execute()
    	throws MojoExecutionException, MojoFailureException
    {
    	org.jibx.schema.utility.createjibxprojectutilities.Catalog2JibxProjects project = new org.jibx.schema.utility.createjibxprojectutilities.Catalog2JibxProjects(catalogLocation, targetDomain, projectName, projectDescription, developerName, developerEmail, organizationName, organizationURL);
    	project.execute();
    }
}
