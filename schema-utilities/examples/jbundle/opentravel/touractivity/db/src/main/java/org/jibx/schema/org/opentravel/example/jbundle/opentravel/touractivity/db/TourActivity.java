
package org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.db;

import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import java.util.*;
import org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.model.db.*;

/**
 *  TourActivity - Tour Activity record.
 */
public class TourActivity extends VirtualRecord
     implements TourActivityModel
{
    private static final long serialVersionUID = 1L;

    protected Date targetDate = null;
    /**
     * Default constructor.
     */
    public TourActivity()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourActivity(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(TOUR_ACTIVITY_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(MAINT_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
            field = new StringField(this, DESCRIPTION, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new CurrencyField(this, PRICE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new ShortField(this, AVAILABILITY_DAYS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, DESCRIPTION_KEY);
            keyArea.addKeyField(DESCRIPTION, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
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
    /**
     * Do a remote command.
     * @param strCommand The command
     * @param properties The properties for the command
     * @return The return value or Boolean.FALSE if not handled.
     */
    public Object doRemoteCommand(String strCommand, Map<String, Object> properties)
    {
        if (SET_FILTER.equalsIgnoreCase(strCommand))
            if (properties != null)
                if (properties.get(TARGET_DATE) instanceof Date)
            {
                targetDate = (Date)properties.get(TARGET_DATE);
                return Boolean.TRUE;
            }
        return super.doRemoteCommand(strCommand, properties);
    }

}
