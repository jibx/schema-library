package org.jibx.schema.utility.createjibxproject;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URLDecoder;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.jibx.schema.utility.createjibxprojectutilities.BaseJibxProjectBuilder;
import org.jibx.schema.utility.createjibxprojectutilities.Catalog2JibxProjects;
import org.jibx.schema.utility.createjibxprojectutilities.GenerateJibxProject;

public class CreateSchemaProject extends JApplet 
	implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final boolean DEBUG = false;
    public final static String URL_ENCODING = "UTF-8";

    static public String DEVELOPER_NAME = "developerName";
	static public String DEVELOPER_EMAIL = "developerEmail";

	static public String SCHEMA_LOCATION = "schemaLocation";
	static public String PARENT_PROJECT_DIRECTORY = "parentProjectDirectory";
	static public String DESCRIPTION = "description";
	
	static public String CATALOG_LOCATION = "catalogLocation";
	static public String TARGET_DOMAIN = "targetDomain";
	static public String PROJECT_NAME = "projectName";
	static public String PROJECT_DESCRIPTION = "projectDescription";
	static public String ORGANIZATION_NAME = "organizationName";
	static public String ORGANIZATION_URL = "organizationURL";

	/**
	 * App properties
	 */
	protected Properties m_properties = null;

	private JFrame frame;
	private JTextField textFieldSchemaName;
	private JTextField textFieldDeveloperName;
	private JTextField textFieldDeveloperEmail;
	private JTextField textFieldParentProject;
	private JTextField textFieldCatalogLocation;
	private JTextField textFieldDeveloperName2;
	private JTextField textFieldDeveloperEmail2;
	private JTextField textFieldOrganizationName;
	private JTextField textFieldProjectName;
	private JTextField textFieldProjectDescription;
	private JTextField textFieldTargetDomain;
	private JTextField textFieldOrganizationURL;
	
	private JProgressBar progressBar;
	private JTextField textFieldSchemaLocation;
	private JLabel lblStatus;

	/*
	 * Main method.
	 */
    public static void main(String[] args)
	{
        try {
        	Properties properties = parseArgs(null, args);
        	
			if (properties.size() == 0)
			{
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							CreateSchemaProject program = new CreateSchemaProject();
							program.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			else
			{
				CreateSchemaProject program = new CreateSchemaProject();
				program.setProperties(properties);
				new Thread(program).start();
			}
		} catch (Throwable t) {
			System.out.println("uncaught exception: " + t);
			t.printStackTrace();
		}
    }

	/**
	 * Create the application.
	 */
	public CreateSchemaProject() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 424);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		JPanel singleschema = new JPanel();
		tabbedPane.addTab("Single schema", null, singleschema, null);
		singleschema.setLayout(new BoxLayout(singleschema, BoxLayout.Y_AXIS));
		
		JLabel lblSchemaLocation = new JLabel("Schema location");
		singleschema.add(lblSchemaLocation);
		
		textFieldSchemaLocation = new JTextField();
		singleschema.add(textFieldSchemaLocation);
		textFieldSchemaLocation.setColumns(10);
		
		JLabel lblSchemaName = new JLabel("Schema name");
		singleschema.add(lblSchemaName);
		
		textFieldSchemaName = new JTextField();
		singleschema.add(textFieldSchemaName);
		textFieldSchemaName.setColumns(10);
		
		JLabel lblDeveloperName = new JLabel("Developer name");
		singleschema.add(lblDeveloperName);
		
		textFieldDeveloperName = new JTextField();
		singleschema.add(textFieldDeveloperName);
		textFieldDeveloperName.setColumns(10);
		
		JLabel lblDeveloperEmail = new JLabel("Developer email");
		singleschema.add(lblDeveloperEmail);
		
		textFieldDeveloperEmail = new JTextField();
		singleschema.add(textFieldDeveloperEmail);
		textFieldDeveloperEmail.setColumns(10);
		
		JLabel lblParentProject = new JLabel("Parent project");
		singleschema.add(lblParentProject);
		
		textFieldParentProject = new JTextField();
		singleschema.add(textFieldParentProject);
		textFieldParentProject.setColumns(10);
		
		JButton btnCreateJibxProject = new JButton("Create Jibx Project from Schema");
		btnCreateJibxProject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createSchemaProject();
			}
		});
		singleschema.add(btnCreateJibxProject);
		
		singleschema.add(Box.createVerticalStrut(100));

		JPanel multipleschema = new JPanel();
		tabbedPane.addTab("Multiple schema", null, multipleschema, null);
		multipleschema.setLayout(new BoxLayout(multipleschema, BoxLayout.Y_AXIS));
		
		JLabel lblCatalogLocation = new JLabel("Catalog location");
		multipleschema.add(lblCatalogLocation);
		
		textFieldCatalogLocation = new JTextField();
		multipleschema.add(textFieldCatalogLocation);
		textFieldCatalogLocation.setColumns(10);
		
		JLabel lblDeveloperName_1 = new JLabel("Developer name");
		multipleschema.add(lblDeveloperName_1);
		
		textFieldDeveloperName2 = new JTextField();
		multipleschema.add(textFieldDeveloperName2);
		textFieldDeveloperName2.setColumns(10);
		
		JLabel lblDeveloperEmail_1 = new JLabel("Developer email");
		multipleschema.add(lblDeveloperEmail_1);
		
		textFieldDeveloperEmail2 = new JTextField();
		multipleschema.add(textFieldDeveloperEmail2);
		textFieldDeveloperEmail2.setColumns(10);
		
		JLabel lblOrganizationName = new JLabel("Organization name");
		multipleschema.add(lblOrganizationName);
		
		textFieldOrganizationName = new JTextField();
		multipleschema.add(textFieldOrganizationName);
		textFieldOrganizationName.setColumns(10);
		
		JLabel lblOrganizationUrl = new JLabel("Organization URL");
		multipleschema.add(lblOrganizationUrl);
		
		textFieldOrganizationURL = new JTextField();
		multipleschema.add(textFieldOrganizationURL);
		textFieldOrganizationURL.setColumns(10);
		
		JLabel lblProjectName = new JLabel("Project name");
		multipleschema.add(lblProjectName);
		
		textFieldProjectName = new JTextField();
		multipleschema.add(textFieldProjectName);
		textFieldProjectName.setColumns(10);
		
		JLabel lblProjectDescription = new JLabel("Project description");
		multipleschema.add(lblProjectDescription);
		
		textFieldProjectDescription = new JTextField();
		multipleschema.add(textFieldProjectDescription);
		textFieldProjectDescription.setColumns(10);
		
		JLabel lblTargetDomain = new JLabel("Target domain");
		multipleschema.add(lblTargetDomain);
		
		textFieldTargetDomain = new JTextField();
		multipleschema.add(textFieldTargetDomain);
		textFieldTargetDomain.setColumns(10);
		
		JButton btnGenerateProjectFrom = new JButton("Generate projects from catalog");
		btnGenerateProjectFrom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createCatalogProject();
			}
		});
		multipleschema.add(btnGenerateProjectFrom);
		
		progressBar = new JProgressBar();
		frame.getContentPane().add(progressBar);
		
		lblStatus = new JLabel("Press button to create project");
		lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblStatus.setAlignmentY(0.0f);
		frame.getContentPane().add(lblStatus);
	}

	void createSchemaProject()
	{
		setProperty(SCHEMA_LOCATION, textFieldSchemaLocation.getText());
		setProperty(PARENT_PROJECT_DIRECTORY, textFieldParentProject.getText());
		setProperty(DESCRIPTION, this.textFieldSchemaName.getText());
		setProperty(DEVELOPER_NAME, textFieldDeveloperEmail.getText());
		setProperty(DEVELOPER_EMAIL, textFieldDeveloperName.getText());
		new Thread(this).start();
	}

	void createCatalogProject()
	{
		setProperty(CATALOG_LOCATION, this.textFieldCatalogLocation.getText());
		setProperty(TARGET_DOMAIN, this.textFieldTargetDomain.getText());
		setProperty(PROJECT_NAME, this.textFieldProjectName.getText());
		setProperty(PROJECT_DESCRIPTION, this.textFieldProjectDescription.getText());
		setProperty(ORGANIZATION_NAME, this.textFieldOrganizationName.getText());
		setProperty(ORGANIZATION_URL, this.textFieldOrganizationURL.getText());
		setProperty(DEVELOPER_NAME, textFieldDeveloperEmail2.getText());
		setProperty(DEVELOPER_EMAIL, textFieldDeveloperName2.getText());
		new Thread(this).start();
	}

    public void run() {
		progressBar.setIndeterminate(true);
		lblStatus.setText("Running...");
		
		String developerName = getProperty(DEVELOPER_NAME);
		String developerEmail = getProperty(DEVELOPER_EMAIL);

		String schemaLocation = getProperty(SCHEMA_LOCATION);
		String parentProjectDirectory = getProperty(PARENT_PROJECT_DIRECTORY);
		String description = getProperty(DESCRIPTION);
		
	    String catalogLocation = getProperty(CATALOG_LOCATION);
	    String targetDomain = getProperty(TARGET_DOMAIN);
	    String projectName = getProperty(PROJECT_NAME);
	    String projectDescription = getProperty(PROJECT_DESCRIPTION);
	    String organizationName = getProperty(ORGANIZATION_NAME);
	    String organizationURL = getProperty(ORGANIZATION_URL);
	    
	    BaseJibxProjectBuilder project = null;
	    if (catalogLocation != null)
	    	project = new Catalog2JibxProjects(catalogLocation, targetDomain, projectName, projectDescription, developerName, developerEmail, organizationName, organizationURL);
	    else
	    	project = new GenerateJibxProject(schemaLocation, parentProjectDirectory, description, developerName, developerEmail);
	    
		project.execute();	
		progressBar.setIndeterminate(false);
		lblStatus.setText("Done!");
	}
    /**
     * Parse this URL formatted string into properties.
     * @properties The properties object to add the params to.
     * @args The arguments to parse (each formatted as key=value).
     */
    public static Properties parseArgs(Properties properties, String[] args)
    {
    	if (properties == null)
    		properties = new Properties();
        if (args == null)
            return properties;
        for (int i = 0; i < args.length; i++)
        	addParam(properties, args[i], false);
        return properties;
    }
    /**
     * Parse the param line and add it to this properties object.
     * (ie., key=value).
     * @properties The properties object to add this params to.
     * @param strParam param line in the format param=value
     */
    public static void addParam(Properties properties, String strParams, boolean bDecodeString)
    {
        int iIndex = strParams.indexOf('=');
        int iEndIndex = strParams.length();
        if (iIndex != -1)
        {
            String strParam = strParams.substring(0, iIndex);
            String strValue = strParams.substring(iIndex + 1, iEndIndex);
            if (bDecodeString)
            {
                try {
                    strParam = URLDecoder.decode(strParam, URL_ENCODING);
                    strValue = URLDecoder.decode(strValue, URL_ENCODING);
                } catch (java.io.UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
            properties.put(strParam, strValue);
        }
    }

	public void setProperties(Properties properties)
	{
		m_properties = properties;
	}
	/**
	 * Get property.
	 * @param key
	 * @return
	 */
    public String getProperty(String key)
    {
    	if (m_properties == null)
    		return null;
    	return m_properties.getProperty(key);
    }

	/**
	 * Set property.
	 * @param key
	 * @return
	 */
    public void setProperty(String key, String value)
    {
    	if (m_properties == null)
    		m_properties = new Properties();
    	m_properties.setProperty(key, value);
    }
	
}
