/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package org.jibx.schema.org.opentravel.example.swing;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class App extends JApplet
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates new Model.
     */
    public App()
    {
        super();
    }
    /**
     * main entrypoint - starts the applet when it is run as an application
     * @param args java.lang.String[]
     */
    public static void main(String[] args)
    {
        JFrame frame;
        App biorhythm = new App();
        try {
            frame = new JFrame("Biorhythm");
            frame.addWindowListener(new AppCloser(frame, biorhythm));
        } catch (java.lang.Throwable ivjExc) {
            frame = null;
            System.out.println(ivjExc.getMessage());
            ivjExc.printStackTrace();
        }
        frame.getContentPane().add(BorderLayout.CENTER, biorhythm);
        Dimension size = biorhythm.getSize();
        if ((size == null) || ((size.getHeight() < 100) | (size.getWidth() < 100)))
            size = new Dimension(640, 400);
        frame.setSize(size);

        biorhythm.init();       // Simulate the applet calls
        frame.setTitle("Sample java application");
        biorhythm.start();

        frame.setVisible(true);
    }
    	
    /**
     * Initialize this applet.
     */
    public void init()
    {
        JPanel view = new JPanel();
        view.setLayout(new BorderLayout());
        label = new JLabel(INITIAL_TEXT);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        label.setFont(font);
        view.add(BorderLayout.CENTER, label);
        
        this.getContentPane().add(BorderLayout.CENTER, view);
        
        this.startRollingText();
    }
    /**
     * Called just before exiting.
     */
    public void destroy()
    {
    	rollingTextThread.interrupt();
        super.destroy();
    }
    
    /**
     * Setup and start the rolling text.
     * NOTE: This uses quick and dirty synchronization.
     */
    public void startRollingText()
    {        
    	for (int i = 0; i < INITIAL_TEXT.length(); i++)
    	{
    		StringBuilder sb = new StringBuilder(INITIAL_TEXT);
    		sb.insert(i + 1, "</font>");
    		sb.insert(i, "<font color=\"red\">");
    		sb.insert(0, "<html>");
    		sb.append("</html>");
    		rollingText[i] = sb.toString();
    	}

    	rollingTextThread = new Thread()
        {
    		Thread doRun = new Thread()
    		{
    			public void run()
    			{
    				label.setText(rollingText[textIndex]);
    			}
    		};
        	public void run()
        	{
        		while (true)
        		{
        			for (textIndex = 0; textIndex < rollingText.length; textIndex++)
        			{
        				try {
        					synchronized (this)
        					{
        						this.wait(150);
        					}
						} catch (InterruptedException e) {
							return;
						}
        				SwingUtilities.invokeLater(doRun);
        			}
        		}
        	};
        };
        rollingTextThread.start();
    }

    private static final String INITIAL_TEXT = "Hello World!!!!";
    private Thread rollingTextThread = null;
    private String[] rollingText = new String[INITIAL_TEXT.length()];
    private int textIndex = 0;
    private JLabel label = null;

    /**
     * AppCloser quits the application when the user closes the window.
     */
    public static class AppCloser extends WindowAdapter
    {
        JFrame frame = null;
        Applet app = null;
        /**
         * Constructor.
         */
        public AppCloser(JFrame frame, Applet app)
        {
            super();
            this.frame = frame;
            this.app = app;
        }
        /**
         * Close the window.
         */
        public void windowClosing(WindowEvent e)
        {
            app.stop();     // Simulate the applet calls
            app.destroy();
            frame.setVisible(false);
            frame.dispose();
            System.exit(0);
        }
    }

}
