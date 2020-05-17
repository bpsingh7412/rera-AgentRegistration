package in.gov.agentregistration.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.gov.agentregistration.constants.CommonConstants;
import in.gov.agentregistration.model.AgentRegistrationModel;

public class SmsContents {
	private static final Logger logger = LogManager.getLogger(SmsContents.class);

	/**********************************************************************************************
	 * Event : Project Submit Successfully sent to: Promoter
	 **********************************************************************************************/

	public static SmsNotification smsNotificationForPaymentSuccess(AgentRegistrationModel m) {
		logger.debug("smsNotificationForPaymentSuccess::Start");
		SmsNotification sms = new SmsNotification();
		sms.setMobileNo(m.getAgentDetail().getMobileNo());
		sms.setProcessName(CommonConstants.AGENT_REG);
		sms.setSendAsOtp("N");
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(m.getAgentFirstName()).append("\n\n");
		sb.append("your registration payment completed successfully. \n\n");
		sb.append("Regards: \n").append("Gujarat RERA \n\n\n");
		sms.setMessage(sb.toString());
		logger.debug("smsNotificationForPaymentSuccess::end");
		return sms;
	}

	/**********************************************************************************************
	 * Event : Project payment is pending sent to: Promoter
	 **********************************************************************************************/
	public static SmsNotification smsNotificationForPaymentPending(AgentRegistrationModel m) {
		logger.debug("smsNotificationForPaymentPending::Start");
		SmsNotification sms = new SmsNotification();
		sms.setMobileNo(m.getAgentDetail().getMobileNo());
		sms.setProcessName(CommonConstants.AGENT_REG);
		sms.setSendAsOtp("N");
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(m.getAgentFirstName()).append("\n\n");
		sb.append("your registration payment pending. \n\n");
		sb.append("Regards: \n").append("Gujarat RERA \n\n\n");
		sms.setMessage(sb.toString());
		logger.debug("smsNotificationForPaymentPending::end");
		return sms;
	}

	/**********************************************************************************************
	 * Event : Project Approved Successfully sent to: Promoter
	 **********************************************************************************************/
	public static SmsNotification smsNotificationWfApproved(AgentRegistrationModel m) {
		logger.debug("smsNotificationWfApproved::Start");
		SmsNotification sms = new SmsNotification();
		sms.setMobileNo(m.getAgentDetail().getMobileNo());
		sms.setProcessName(CommonConstants.AGENT_REG);
		sms.setSendAsOtp("N");
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(m.getAgentFirstName()).append("\n\n");
		sb.append("your registrtaion approved Successfully. \n\n");
		sb.append("Regards: \n").append("Gujarat RERA \n\n\n");
		sms.setMessage(sb.toString());
		logger.debug("smsNotificationWfApproved::end");
		return sms;
	}

}
