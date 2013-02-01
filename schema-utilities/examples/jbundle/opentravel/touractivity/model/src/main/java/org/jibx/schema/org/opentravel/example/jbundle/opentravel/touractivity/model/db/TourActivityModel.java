
package org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.model.db;

import org.jbundle.model.db.*;

public interface TourActivityModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String MIN_PRICE = "MinPrice";
    public static final String MAX_PRICE = "MaxPrice";

    public static final String TOUR_ACTIVITY_PRIMARY_KEY = "TourActivityPrimary";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String TOUR_ACTIVITY_FILE = "TourActivity";
    public static final String THIN_CLASS = "org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.thin.db.TourActivity";
    public static final String THICK_CLASS = "org.jibx.schema.org.opentravel.example.jbundle.opentravel.touractivity.db.TourActivity";

}
