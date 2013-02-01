
package org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.thin.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.model.db.*;

public class TourActivity extends FieldList
    implements TourActivityModel
{
    private static final long serialVersionUID = 1L;


    public TourActivity()
    {
        super();
    }
    public TourActivity(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TOUR_ACTIVITY_FILE = "TourActivity";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TourActivity.TOUR_ACTIVITY_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.LOCAL;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, DESCRIPTION, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, MIN_PRICE, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, MAX_PRICE, 18, null, null);
        field.setDataClass(Double.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, DESCRIPTION_KEY);
        keyArea.addKeyField(DESCRIPTION, Constants.ASCENDING);
    }

}
