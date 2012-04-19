package org.jibx.schema.org.opentravel._2011B.base.ws;

import org.jibx.schema.org.opentravel._2011B.base.Errors;
import org.jibx.schema.org.opentravel._2011B.base.OTAPayloadStdAttributes;
import org.jibx.schema.org.opentravel._2011B.base._Error;
import org.joda.time.DateTime;

/**
 * AbstractBaseService - opentravel.org message utilities.
 * @author don
 *
 */
public abstract class BaseService extends Object
{
	/**
	 * Constructor
	 */
	public BaseService()
	{
		super();
		init();
	}

	/**
	 * Constructor
	 */
	public void init()
	{
	}

    /**
     * Set up a default payload.
     * @return The payload
     */
    public static OTAPayloadStdAttributes createStandardPayload()
    {
        OTAPayloadStdAttributes payloadStdAttributes = new OTAPayloadStdAttributes();
        DateTime timeStamp = new DateTime();
        payloadStdAttributes.setTimeStamp(timeStamp);
        Float version = new Float(1.00);
        payloadStdAttributes.setVersion(version);
        payloadStdAttributes.setTarget(OTAPayloadStdAttributes.Target.PRODUCTION);
        return payloadStdAttributes;
    }
    /**
     * Move payload data from the request to the response.
     * @return The payload
     */
    public static void movePayloadData(OTAPayloadStdAttributes requestStdAttributes, OTAPayloadStdAttributes responseStdAttributes)
    {
    	responseStdAttributes.setAltLangID(requestStdAttributes.getAltLangID());
    	responseStdAttributes.setCorrelationID(requestStdAttributes.getCorrelationID());
    	responseStdAttributes.setEchoToken(requestStdAttributes.getEchoToken());
    	responseStdAttributes.setPrimaryLangID(requestStdAttributes.getPrimaryLangID());
    	responseStdAttributes.setRetransmissionIndicator(requestStdAttributes.getRetransmissionIndicator());
    	responseStdAttributes.setSequenceNmbr(requestStdAttributes.getSequenceNmbr());
    	if (responseStdAttributes.getTarget() == null)
    		responseStdAttributes.setTarget(requestStdAttributes.getTarget());
    	responseStdAttributes.setTargetName(requestStdAttributes.getTargetName());
    	if (responseStdAttributes.getTimeStamp() == null)
    		responseStdAttributes.setTimeStamp(requestStdAttributes.getTimeStamp());
    	responseStdAttributes.setTransactionIdentifier(requestStdAttributes.getTransactionIdentifier());
    	responseStdAttributes.setTransactionStatusCode(requestStdAttributes.getTransactionStatusCode());
    	if (responseStdAttributes.getVersion() == null)
    		responseStdAttributes.setVersion(requestStdAttributes.getVersion());
    }

    /**
     * Add this message text to the list of errors.
     * Creates a new list if errors is null.
     * @param errors
     * @param message
     * @param type
     * @return
     */
	public static Errors addError(Errors errors, String message, String type)
	{
		if (errors == null)
        	errors = new Errors();
    	_Error error = new _Error();
    	error.setString(message);
    	if (type == null)
    		type = "10";	// 10 = Required field missing
    	error.setType(type);
    	errors.addError(error);
    	return errors;
	}
}
