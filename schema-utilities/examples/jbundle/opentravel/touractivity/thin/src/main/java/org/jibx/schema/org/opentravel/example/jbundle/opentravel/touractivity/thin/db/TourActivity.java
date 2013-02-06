
package org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.thin.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.model.db.*;

public class TourActivity extends FieldList
    implements TourActivityModel
{
    private static final long serialVersionUID = 1L;

    protected Date targetDate = null;

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
        field = new FieldInfo(this, PRICE, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, AVAILABILITY_DAYS, 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, START_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, END_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
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
    /**
     * SetTargetDate Method.
     */
    public Object setTargetDate(Date date)
    {
        targetDate = date;
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(TARGET_DATE, targetDate);
        try {
            return this.handleRemoteCommand(SET_FILTER, properties);
        } catch (Exception ex) {
            return null;    // Ignore
        }
    }

}
