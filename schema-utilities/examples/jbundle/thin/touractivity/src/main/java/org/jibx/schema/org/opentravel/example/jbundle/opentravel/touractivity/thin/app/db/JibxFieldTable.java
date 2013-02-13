/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.thin.app.db;

import org.jbundle.model.DBException;
import org.jbundle.model.RemoteException;
import org.jbundle.model.db.Rec;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.KeyAreaInfo;
import org.jbundle.thin.base.db.client.VectorFieldTable;
import org.jbundle.thin.base.remote.RemoteTable;


/**
 * An RemoteFieldTable is a link to this remote table.
 */
public class JibxFieldTable extends VectorFieldTable
{
    /**
     * A reference to the remote table.
     */
    protected RemoteTable m_tableRemote = null;
    /**
     * The object to synchronize calls on.
     */
    protected Object m_syncObject = null;
    /**
     * The current record position.
     */
    protected int m_iCurrentRecord = -1;
    /**
     * The number of records accessed so far.
     */
    protected int m_iRecordsAccessed = -1;
    /**
     * The bookmark of the last added record (if supported by the remote session).
     */
    protected Object m_objLastModBookmark = null;

    /**
     * Constructor.
     */
    public JibxFieldTable()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The record to handle.
     */
    public JibxFieldTable(FieldList record)
    {
        this();
        this.init(record, null, null);
    }
    /**
     * Constructor.
     * @param record The record to handle.
     * @param tableRemote The remote table.
     * @param server The remote server (only used for synchronization).
     */
    public JibxFieldTable(FieldList record, RemoteTable tableRemote, Object syncObject)
    {
        this();
        this.init(record, tableRemote, syncObject);
    }
    /**
     * Constructor.
     * @param record The record to handle.
     * @param tableRemote The remote table.
     * @param server The remote server (only used for synchronization).
     */
    public void init(Rec record, RemoteTable tableRemote, Object syncObject)
    {
        super.init(record);
    }
    /**
     * I'm done with the model, free the resources.
     */
    public void free()
    {
        try   {
            if (m_tableRemote != null)
                m_tableRemote.freeRemoteSession();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        m_tableRemote = null;
        super.free();
    }
    /**
     * Reset the current position and open the file.
     */
    public void open() throws DBException
    {
        m_objLastModBookmark = null;
        try   {
    // FROM is automatic, since the remote BaseRecord is exactly the same as this one
    // ORDER BY
            String strKeyArea = null;//this.getFieldInfo().getKeyName();
    // ASC / DESCending
            KeyAreaInfo keyArea = null;//this.getRecord().getKeyArea(-1);
            boolean bKeyOrder = Constants.ASCENDING;
            if (keyArea != null)
                bKeyOrder = keyArea.getKeyOrder();
    // Open mode
            int iOpenMode = 0;//this.getRecord().getOpenMode();
    // SELECT (fields to select - all)
            String strFields = null;
    // WHERE aaa >=
            Object objInitialKey = null;
    // WHERE aaa <=
            Object objEndKey = null;
    // WHERE XYZ
            byte[] byFilter = null;

            m_tableRemote.open(strKeyArea, iOpenMode, bKeyOrder, strFields, objInitialKey, objEndKey, byFilter);
            m_iCurrentRecord = -1;
            m_iRecordsAccessed = 0;
            super.open();
        } catch (RemoteException ex) {
            throw new DBException(ex.getMessage());
        }
    }
    /**
     * Close this table.
     * In this implementation this does nothing, because the remote open code always closes first.
     */
    public void close()
    {
        m_iCurrentRecord = -1;
        m_iRecordsAccessed = 0;
        m_objLastModBookmark = null;
        super.close();
    }
}
