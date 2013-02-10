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
import javax.swing.ImageIcon;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.jbundle.model.db.Convert;
import org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.thin.db.TourActivity;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.grid.JCellButton;
import org.jbundle.thin.base.screen.grid.ThinTableModel;


/**
 * Main Class for applet OrderEntry.
 * Note: This extends the CalendarThinTableModel rather
 * than the ThinTableModel, so I have the
 * use of the MySelectionListener.
 */
public class TourActivityGridModel extends ThinTableModel
{
    private static final long serialVersionUID = 1L;

    public static final int ADD_BUTTON_COLUMN = 0;
    public static final int DESCRIPTION = ADD_BUTTON_COLUMN + 1;
    public static final int COLUMN_COUNT = DESCRIPTION + 1;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public TourActivityGridModel()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public TourActivityGridModel(FieldTable table)
    {
        this();
        this.init(table);
    }
    /**
     * The init() method is called by the AWT when an applet is first loaded or
     * reloaded.  Override this method to perform whatever initialization your
     * applet needs, such as initializing data structures, loading images or
     * fonts, creating frame windows, setting the layout manager, or adding UI
     * components.
     */
    public void init(FieldTable table)
    {
        super.init(table);
    }
    /**
     * Get the row count.
     */
    public int getColumnCount()
    {
        return COLUMN_COUNT;
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Convert getFieldInfo(int iIndex)
    {
        FieldList fieldList = m_table.getRecord();
        switch (iIndex)
        {
            case ADD_BUTTON_COLUMN:
                return null;
            case DESCRIPTION:
                return fieldList.getField(TourActivity.DESCRIPTION);
        }
        return super.getFieldInfo(iIndex);
    }
    /**
     * Get the column class.
     * Returns String by default, override to supply a different class.
     */
    public Class<?> getColumnClass(int iColumnIndex)
    {
        switch (iColumnIndex)
        {
            case ADD_BUTTON_COLUMN:
                return ImageIcon.class;
        }
        return super.getColumnClass(iColumnIndex);
    }
    /**
     * Get the value (this method returns the RAW data rather than Thin's String.
     */
    public Object getColumnValue(int iColumnIndex, int iEditMode)
    {
        switch (iColumnIndex) // RequestInputID
        {
            case ADD_BUTTON_COLUMN:
                return BaseApplet.getSharedInstance().loadImageIcon(Constants.FILE_ROOT + Constants.FORM, Constants.BLANK);
        }
        return super.getColumnValue(iColumnIndex, iEditMode);
    }
    /**
     * Get the cell editor for this column.
     * @param The column to get the cell editor for.
     * @return The cell editor or null to use the default.
     */
    public TableCellEditor createColumnCellEditor(int iColumnIndex)
    {
        switch (iColumnIndex)
        {
        case ADD_BUTTON_COLUMN:
            ImageIcon icon = (ImageIcon)this.getValueAt(-1, iColumnIndex);
            JCellButton button = new JCellButton(icon);
            button.setOpaque(false);
            button.setName(Constants.FORM);
            return button;
        }
        return super.createColumnCellEditor(iColumnIndex);
    }
    /**
     * Get the cell renderer for this column.
     * @param The column to get the cell renderer for.
     * @return The cell renderer or null to use the default.
     */
    public TableCellRenderer createColumnCellRenderer(int iColumnIndex)
    {
        switch (iColumnIndex)
        {
        case ADD_BUTTON_COLUMN:
            ImageIcon icon = (ImageIcon)this.getValueAt(-1, iColumnIndex);
            JCellButton button = new JCellButton(icon);
            return button;
        }
        return super.createColumnCellRenderer(iColumnIndex);
    }
    /**
     * Don't allow appending.
     */
    public boolean isAppending()
    {
        return false;
    }
    /**
     * Is this cell editable.
     * @return true unless this is a deleted record.
     */
    public boolean isCellEditable(int iRowIndex, int iColumnIndex)
    {
        if (ADD_BUTTON_COLUMN == iColumnIndex)
            return true; // Don't allow changes to deleted records
        return false;        // All my fields are note editable
    }
    /**
     * Returns the name of the column at columnIndex.
     */
    public String getColumnName(int iColumnIndex)
    {
        switch (iColumnIndex)
        {
            case ADD_BUTTON_COLUMN:
                return "+";
        }
        return super.getColumnName(iColumnIndex);
    }
}
