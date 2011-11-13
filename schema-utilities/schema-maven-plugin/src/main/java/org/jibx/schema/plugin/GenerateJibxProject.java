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
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

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

    /**
     * Run
     */
    public void execute()
    	throws MojoExecutionException, MojoFailureException
    {
    	org.jibx.schema.utility.createjibxprojectutilities.GenerateJibxProject project = new org.jibx.schema.utility.createjibxprojectutilities.GenerateJibxProject(schemaLocation, parentProjectDirectory, description, developerName, developerEmail);
    	project.execute();
    }
}
