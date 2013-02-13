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

import java.awt.Container;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import org.jbundle.model.DBException;
import org.jbundle.model.db.Convert;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.db.mem.base.PTable;
import org.jbundle.thin.base.db.mem.base.PhysicalDatabaseParent;
import org.jbundle.thin.base.db.model.ThinPhysicalDatabase;
import org.jbundle.thin.base.screen.AbstractThinTableModel;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.ThinApplication;
import org.jbundle.thin.base.screen.grid.JCellButton;
import org.jbundle.thin.base.screen.grid.JGridScreen;
import org.jbundle.thin.base.screen.grid.JGridScreenToolbar;
import org.jbundle.thin.base.screen.util.cal.JCalendarDualField;
import org.jbundle.thin.base.util.Application;
import org.jbundle.thin.base.util.message.ThinMessageManager;
import org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.thin.db.TourActivity;


/**
 * A Basic data entry screen.
 * This screen is made of a panel with a GridBagLayout. Labels in the first column, aligned right.
 * Data fields in the second column aligned left.
 */
public class TourActivityThinGridScreen extends JGridScreen
implements FocusListener
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
                this.add(new JLabel("Target date"));
                MyCalendarDualField component = new MyCalendarDualField(null);
                this.add(component);
                component.addFocusListener((TourActivityThinGridScreen)getToolbarParent());
            }            
        };
        
    }
    class MyCalendarDualField extends JCalendarDualField
    {
        private static final long serialVersionUID = 1L;
        public MyCalendarDualField(FieldInfo field)
        {
            super(field);
        }
        public void init(Convert converter, boolean bAddCalendarButton, boolean bAddTimeButton)
        {
            if (converter == null)
            {
                converter = new FieldInfo(null, "Date", Constants.DEFAULT_FIELD_LENGTH, null, null)
                {
                    private static final long serialVersionUID = 1L;

                    public int setData(Object vpData, boolean bDisplayOption, int iMoveMode)
                    {
                        Date oldDate = null;
                        if (this.getData() instanceof Date)
                            oldDate = (Date)this.getData();
                        int error = super.setData(vpData, bDisplayOption, iMoveMode);
                        if (((this.getData() != null) && (!this.getData().equals(oldDate)))
                            || ((this.getData() == null) && (oldDate != null)))
                        {
                            TourActivity record = (TourActivity)getFieldList();
                            record.setTargetDate((Date)this.getData());
                            record.getTable().close();
                            getGridModel().resetTheModel();
                        }
                        return error;
                    }
                };
                ((FieldInfo)converter).setDataClass(Date.class);
            }
            super.init(converter, bAddCalendarButton, bAddTimeButton);
        }
    };
    @Override
    public void focusGained(FocusEvent e) {
    }
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getComponent() instanceof JTextField)
            if (e.getComponent().getParent() instanceof MyCalendarDualField)
        {
            Convert converter = ((MyCalendarDualField)e.getComponent().getParent()).getConverter();
            if (converter != null)
                if (converter.getField() != null)
                    converter.getField().setString(((JTextField)e.getComponent()).getText(), Constants.DISPLAY, Constants.SCREEN_MOVE);
        }
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
    /**
     * Add any applet sub-panel(s) now.
     */
    public boolean addSubPanels(Container parent, int options)
    {
/*        parent.setLayout(new BoxLayout(parent, BoxLayout.X_AXIS));
        FieldList record = new TestTable(this);

//+     this.linkNewRemoteTable(null, record, true);
//------------------------
        Application app = this.getApplication();
        PhysicalDatabaseParent dbOwner = (PhysicalDatabaseParent)((ThinApplication)app).getPDatabaseParent(BaseApplet.mapDBParentProperties, true);
        PTable pTable = dbOwner.getPDatabase(record.getDatabaseName(), ThinPhysicalDatabase.NET_TYPE, true).getPTable(record, true, true);
//------------------------
        
        FieldTable table = record.getTable();
        try   {
            table.addNew();
//          record.getFieldInfo("ID").setString("1");
            record.getField("TestName").setString("no 1");
            table.add(record);
            table.addNew();
            record.getField("TestName").setString("no 2");
            table.add(record);
            table.close();
//          while (table.hasNext())
            {
//              table.next();
//System.out.println("Test/103--==================" + record.toString() + "==========");
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
//------------------------

        model = new TestGridModel(record.getTable());
        thinscreen = new JTable(model);
        model.setGridScreen(thinscreen, false);
            TableColumn newColumn = thinscreen.getColumnModel().getColumn(0);
            ImageIcon icon = (ImageIcon)model.getValueAt(0, 0);
            newColumn.setPreferredWidth(20);    // Yeah I know, but I know the width and I don't want to have to wait to load the icon.
            JCellButton button = new JCellButton(icon);
            newColumn.setCellEditor(button);
            button.addActionListener(this);
            button = new JCellButton(icon);
            newColumn.setCellRenderer(button);
        JScrollPane scrollpane = new JScrollPane(thinscreen);
        parent.add(scrollpane);*/
        return true;
    }
}
