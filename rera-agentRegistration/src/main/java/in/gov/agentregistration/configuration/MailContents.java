package in.gov.agentregistration.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.gov.agentregistration.constants.CommonConstants;
import in.gov.agentregistration.model.AgentRegistrationModel;

public class MailContents {
	private static final Logger logger = LogManager.getLogger(MailContents.class);

	/**************************************************************************************************************
	 * Event : project Approved successfully sent to: Promoter
	 **************************************************************************************************************/
	public static EmailNotification projectAlterationMailWorkFlow(AgentRegistrationModel model) {
		EmailNotification email = new EmailNotification();
		StringBuilder subject = new StringBuilder();
		subject.append("GujRERA Project Alteration Approved");

		email.setSubject(subject.toString());
		email.setSendTo(model.getEmailId());
		email.setProcessName(CommonConstants.AGENT_REG);
		email.setSendAsOtp("N");
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(model.getAgentFirstName()).append("\n\n");
		sb.append("Your Registration is successfully approved in GujRERA Portal. \n\n");
		sb.append("Regards: \n").append("Gujarat RERA \n\n\n");

		email.setBodyContent(sb.toString());
		return email;
	}

	/**************************************************************************************************************
	 * Event : project Register successfully sent to: Promoter
	 **************************************************************************************************************/
	public static EmailNotification projectAlterationMailPayment(AgentRegistrationModel model) {
		EmailNotification email = new EmailNotification();
		StringBuilder subject = new StringBuilder();
		subject.append("GujRERA Project Alteration payment");

		email.setSubject(subject.toString());
		email.setSendTo(model.getEmailId());
		email.setProcessName(CommonConstants.AGENT_REG);
		email.setSendAsOtp("N");
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(model.getAgentFirstName()).append("\n\n");
		sb.append("Your Registration is successfully approved in GujRERA Portal. \n\n");
		sb.append("Regards: \n").append("Gujarat RERA \n\n\n");

		email.setBodyContent(sb.toString());
		return email;
	}

}
