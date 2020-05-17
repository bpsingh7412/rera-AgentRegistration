package in.gov.agentregistration.constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonConstants {

	private static final Logger logger = LogManager.getLogger(CommonConstants.class);

	// public static final String PROMOTER_AUTH = "PROMOTER_AUTH";
	public static final String REGISTERED = "REGISTERED";
	public static final String PENDING = "PENDING";
	public static final String EXTERNAL_USER_CATEGORY = "EXTERNAL";
	public static final String CITIZEN = "CITIZEN";
	public static final String AGENT_EXISTS = "Agent is already exists.";
	public static final String AGENT_REG = "AGENT_REG";
	public static final String COMPLETED = "COMPLETED";

	public static final String ePAY_SENT = "ePAY_SENT";
	public static final String ePAY_ABORT = "ABORT";
	public static final String ePAY_BOOKED = "BOOKED";
	public static final String ePAY_CANCELLED = "CANCELLED";
	public static final String ePAY_CLOSED = "CLOSED";
	public static final String ePAY_EXPIRE = "EXPIRED";
	public static final String ePAY_FAIL = "FAIL";
	public static final String ePAY_NoRecordsFound = "No records found";
	public static final String ePAY_PENDING = "PENDING";
	public static final String ePAY_REFUND = "REFUND";
	public static final String ePAY_REJECT = "REJECT";
	public static final String ePAY_SUCCESS = "SUCCESS";
	public static final String ePAY_ERROR_MSG = "Transaction Failed by GujRERA.";
	public static final Double AGENT_COST = 10000.00d;
	public static final String INDVISUAL_REG = "Individual/Propritorship";
	public static final String FIRM_COMP_REG = "FIRM_COMP_REG";

	public static final String AGENT_ENQUIRY = "AGENT_ENQUIRY";
	public static final String ENQUIRY = "ENQUIRY";
	
	public static final String PENDING_WITH_AUTHORITY = "PENDING_WITH_AUTHORITY";
	public static final String APPROVED = "APPROVED";
	public static final String ACTIVE = "ACTIVE";
	public static final String IN_ACTIVE = "IN_ACTIVE";
	public static final String AGENT_WORK_STATUS = "AGENT_WORK_STATUS";

}
