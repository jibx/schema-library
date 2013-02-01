/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.thin.app;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 jbundle.org. All rights reserved.
 *  
 *  @author Don Corley don@tourgeek.com
 *  @version 1.0.0.
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusEvent;
import java.util.Map;

import javax.swing.JComponent;

import org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.thin.db.TourActivity;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.remote.RemoteException;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.remote.RemoteTable;
import org.jbundle.thin.base.remote.RemoteTask;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.JScreen;
import org.jbundle.thin.base.screen.grid.JCellRemoteComboBox;
import org.jbundle.thin.base.screen.util.JFSImage;
import org.jbundle.thin.base.screen.util.JFSTextScroller;
import org.jbundle.thin.base.util.message.ThinMessageManager;


/**
 * Main Class for applet OrderEntry
 */
public class TourActivityThinScreen extends JScreen
{
    private static final long serialVersionUID = 1L;

    /**
     * OrderEntry Class Constructor.
     */
    public TourActivityThinScreen() {
        super();
    }

    /**
     * OrderEntry Class Constructor.
     */
    public TourActivityThinScreen(Object parent, Object obj) {
        this();
        this.init(parent, obj);
    }

    /**
     * The init() method is called by the AWT when an applet is first loaded or
     * reloaded. Override this method to perform whatever initialization your
     * applet needs, such as initializing data structures, loading images or
     * fonts, creating frame windows, setting the layout manager, or adding UI
     * components.
     */
    public void init(Object parent, Object obj) {

        super.init(parent, obj);
        
        ThinMessageManager.createScreenMessageListener(this.getFieldList(), this);
    }
    /**
     * Add any screen sub-panel(s) now.
     * You might want to override this to create an alternate parent screen.
     * @param parent The parent to add the new screen to.
     * @return true if success
     */
    public boolean addSubPanels(Container parent)
    {
        return super.addSubPanels(parent);
    }
    /**
     * Cleanup.
     */
    public void free()
    {
        ThinMessageManager.freeScreenMessageListeners(this);
        
        super.free();
    }

    /**
     * Build the list of fields that make up the screen.
     */
    public FieldList buildFieldList() {
        FieldList record = new TourActivity(null); // If overriding class didn't set this
        return record;
    }
    /**
     * Add the screen controls to the second column of the grid.
     * Create a default component for this fieldInfo.
     * @param fieldInfo the field to create a control for.
     * @return The component.
     */
    public JComponent createScreenComponent(Converter fieldInfo)
    {
        JComponent component = null;
        if (component == null)
            component = super.createScreenComponent(fieldInfo);
        return component;
    }
    /**
     * When a control loses focus, move the field to the data area.
     * @param e The focus event.
     */
    public void focusLost(FocusEvent e)
    {
        super.focusLost(e);
    }
    /**
     * Create a grid screen for this form.
     * @param record the (optional) record for this screen.
     * @return The new grid screen.
     */
    public JBaseScreen createGridScreen(FieldList record)
    {
        return new TourActivityThinGridScreen(this.getParentObject(), record);
    }
}
