/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.thin.app;

/**
 * JScreen.java:    Applet
 *  Copyright � 1997 jbundle.org. All rights reserved.
 *  
 *  @author Don Corley don@tourgeek.com
 *  @version 1.0.0.
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.JComponent;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.AbstractThinTableModel;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.grid.JGridScreen;
import org.jbundle.thin.base.screen.grid.JGridScreenToolbar;
import org.jbundle.thin.base.screen.util.cal.JCalendarDualField;
import org.jbundle.thin.base.util.message.ThinMessageManager;
import org.jbundle.util.jcalendarbutton.JCalendarPopup;
import org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.thin.db.TourActivity;


/**
 * A Basic data entry screen.
 * This screen is made of a panel with a GridBagLayout. Labels in the first column, aligned right.
 * Data fields in the second column aligned left.
 */
public class TourActivityThinGridScreen extends JGridScreen
    implements PropertyChangeListener
{
    private static final long serialVersionUID = 1L;

    /**
     *  JScreen Class Constructor.
     */
    public TourActivityThinGridScreen()
    {
        super();
    }
    /**
     *  JScreen Class Constructor.
     * @param parent Typically, you pass the BaseApplet as the parent.
     * @param record and the record or GridTableModel as the parent.
     */
    public TourActivityThinGridScreen(Object parent, Object record)
    {
        this();
        this.init(parent, record);
    }
    /**
     * Initialize this class.
     * @param parent Typically, you pass the BaseApplet as the parent.
     * @param record and the record or GridTableModel as the parent.
     */
    public void init(Object parent, Object record)
    {
        super.init(parent, record);
        
        ThinMessageManager.createScreenMessageListener(this.getFieldList(), this);
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
     * Add the toolbars?
     * @return The newly created toolbar or null.
     */
    public JComponent createToolbar()
    {
        addPropertyChangeListener(JCalendarPopup.DATE_PARAM, this);
        
        return new JGridScreenToolbar(this, null)
        {
            private static final long serialVersionUID = 1L;

            /**
             * Add the buttons to this window.
             * Override this to include buttons other than the default buttons.
             */
            public void addButtons()
            {
                super.addButtons();
//                this.addButton(Constants.BACK);
                FieldInfo field = new FieldInfo(null, "Date", Constants.DEFAULT_FIELD_LENGTH, null, null)
                {
                    public int setData(Object vpData, boolean bDisplayOption, int iMoveMode)
                    {
                        int error = super.setData(vpData, bDisplayOption, iMoveMode);
                        System.out.println("----------- " + vpData);
                        return error;
                    }
                };
                field.setDataClass(Date.class);
                JCalendarDualField component = new JCalendarDualField(field);

                field.addComponent(component);
                component.setConverter(field);
                
                this.add(component);
            }            
        };
        
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        
    }
    /**
     * Build the list of fields that make up the screen.
     * Override this to create a new record.
     * @return The fieldlist for this screen.
     */
    public FieldList buildFieldList()
    {
        return new TourActivity(null);   // If overriding class didn't set this
    }
    /**
     * Build the list of fields that make up the screen.
     * Override this to create a new record.
     * @return The fieldlist for this screen.
     */
    public AbstractThinTableModel createGridModel(FieldList record)
    {
        return new TourActivityGridModel(record.getTable());
    }
    /**
     * Create a grid screen for this form.
     * @param record the (optional) record for this screen.
     * @return The new grid screen.
     */
    public JBaseScreen createMaintScreen(FieldList record)
    {
        return new TourActivityThinScreen(this.getParentObject(), record);
    }
}
