/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.thin.app;

/**
 * OrderEntry.java:   Applet
 * Copyright © 2012 tourapp.com. All Rights Reserved.
 *  
 *  @author Don Corley don@tourgeek.com
 *  @version 1.0.0.
 */
import java.awt.Container;

import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseFrame;


/**
 * Main Class for applet OrderEntry
 */
public class ThinTourActivityApplet extends BaseApplet
{
    private static final long serialVersionUID = 1L;

    /**
     *  OrderEntry Class Constructor.
     */
    public ThinTourActivityApplet()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public ThinTourActivityApplet(String args[])
    {
        this();
        this.init(args);
    }
    /**
     * The getAppletInfo() method returns a string describing the applet's
     * author, copyright date, or miscellaneous information.
     */
    public String getAppletInfo()
    {
        return "Name: Thin Test\r\n" +
               "Author: Don Corley\r\n" +
               "E-Mail: don@tourgeek.com";
    }
    /**
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent, int options)
    {
        if ((this.getProperty(Params.SCREEN) == null) || (this.getProperty(Params.SCREEN).length() == 0))
            this.setProperty(Params.SCREEN, TourActivityThinGridScreen.class.getName());
        boolean success = super.addSubPanels(parent, options);
        return success;
    }
    /**
     *  The main() method acts as the applet's entry point when it is run
     *  as a standalone application. It is ignored if the applet is run from
     *  within an HTML page.
     */
    public static void main(String args[])
    {
        BaseApplet.main(args);
        ThinTourActivityApplet applet = (ThinTourActivityApplet)ThinTourActivityApplet.getSharedInstance();
        if (applet == null)
            applet = new ThinTourActivityApplet(args);
        new JBaseFrame("Test", applet);
    }
}
