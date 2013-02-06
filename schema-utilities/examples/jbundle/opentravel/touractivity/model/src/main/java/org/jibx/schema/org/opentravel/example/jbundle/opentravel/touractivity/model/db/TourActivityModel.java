
package org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.model.db;

import java.util.*;
import org.jbundle.model.db.*;

public interface TourActivityModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String PRICE = "Price";
    public static final String AVAILABILITY_DAYS = "AvailabilityDays";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";

    public static final String TOUR_ACTIVITY_PRIMARY_KEY = "TourActivityPrimary";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String MAINT_SCREEN_CLASS = "org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.screen.TourActivityScreen";
    public static final String GRID_SCREEN_CLASS = "org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.screen.TourActivityGridScreen";
    public static final String TARGET_DATE = "targetDate";
    public static final String SET_FILTER = "setFilter";

    public static final String TOUR_ACTIVITY_FILE = "TourActivity";
    public static final String THIN_CLASS = "org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.thin.db.TourActivity";
    public static final String THICK_CLASS = "org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.db.TourActivity";
    /**
     * SetTargetDate Method.
     */
    public Object setTargetDate(Date date);

}
