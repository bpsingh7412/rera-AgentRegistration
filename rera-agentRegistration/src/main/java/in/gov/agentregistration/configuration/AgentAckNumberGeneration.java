package in.gov.agentregistration.configuration;

import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import in.gov.agentregistration.constants.CommonConstants;
import in.gov.agentregistration.controller.AgentRegistrationRestController;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.agentregistration.model.YearlyStatusModel;

@Service
public class AgentAckNumberGeneration {
	private static final Logger logger = LogManager.getLogger(AgentRegistrationRestController.class);

	private static final String charCode = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String generateTokenAgentRegRenewal(AgentRegistrationModel model) {

		StringBuffer sb = new StringBuffer("AX");
		if (model.getAgentType().equals(CommonConstants.INDVISUAL_REG))
			sb.append("I");
		else
			sb.append("C");
		sb.append(uniqeCode());
		long id = model.getAgentId();
		String str = "" + (id % 1000);
		while (str.length() < 3) {
			str = "0" + str;
		}
		sb.append(str);
		return sb.toString();
	}

	public static String generateAgentWorkToken(YearlyStatusModel model) {

		StringBuffer sb = new StringBuffer("AXWS");
		sb.append(uniqeCode());
		long id = model.getAgentId();
		String str = "" + (id % 1000);
		while (str.length() < 3) {
			str = "0" + str;
		}
		sb.append(str);
		return sb.toString();
	}

	public static String uniqeCode() {
		StringBuffer sb = new StringBuffer();
		long l = new Date().getTime();
		while (l > 0) {
			int x = (int) (l % 35);
			l = l / 61;
			sb.append(charCode.charAt(x));
		}
		return sb.toString();
	}

	public static String getApplicationNo(AgentRegistrationModel project) {
		logger.debug("getApplicationNo::Started::");
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append("AR/");

		/*
		 * if (project.getDistCode() != null)
		 * sb.append(project.getDistName().toUpperCase()).append("/"); if
		 * (project.getSubDistCode() != null)
		 * sb.append(project.getSubDistName().toUpperCase()).append("/"); if
		 * (project.getApprovingAuthority() != null)
		 * sb.append(project.getValidTpName().toUpperCase()).append("/");
		 */

		int yr = cal.get(Calendar.YEAR);
		int mnth = cal.get(Calendar.MONTH) + 1;
		int dt = cal.get(Calendar.DATE);
		yr = yr % 100;

		if (dt < 10) {
			sb.append("0").append(dt);
		} else {
			sb.append(dt);
		}
		if (mnth < 10) {
			sb.append("0").append(mnth);
		} else {
			sb.append(mnth);
		}
		if (yr < 10) {
			sb.append("0").append(yr);
		} else {
			sb.append(yr);
		}
		sb.append("/");

		String str = "";// + project.getId();
		while (str.length() < 6) {
			str = "0" + str;
		}
		sb.append(str);
		logger.debug("getApplicationNo::Ended::");
		return sb.toString();
	}

	public static String getAcknowledge() {
		logger.debug("getApplicationNo::Started::");
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append("AR/");

		/*
		 * if (project.getDistCode() != null)
		 * sb.append(project.getDistName().toUpperCase()).append("/"); if
		 * (project.getSubDistCode() != null)
		 * sb.append(project.getSubDistName().toUpperCase()).append("/"); if
		 * (project.getApprovingAuthority() != null)
		 * sb.append(project.getValidTpName().toUpperCase()).append("/");
		 */

		int yr = cal.get(Calendar.YEAR);
		int mnth = cal.get(Calendar.MONTH) + 1;
		int dt = cal.get(Calendar.DATE);
		yr = yr % 100;

		if (dt < 10) {
			sb.append("0").append(dt);
		} else {
			sb.append(dt);
		}
		if (mnth < 10) {
			sb.append("0").append(mnth);
		} else {
			sb.append(mnth);
		}
		if (yr < 10) {
			sb.append("0").append(yr);
		} else {
			sb.append(yr);
		}
		sb.append("/");

		// String str = "" + project.getId();
		String str = "AR/LN/";
		while (str.length() < 6) {
			str = "0" + str;
		}
		sb.append(str);
		logger.debug("getApplicationNo::Ended::");
		return sb.toString();
	}

	public static String getAcknowledgeWork() {
		logger.debug("getApplicationNo::Started::");
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append("AWS/");

		/*
		 * if (project.getDistCode() != null)
		 * sb.append(project.getDistName().toUpperCase()).append("/"); if
		 * (project.getSubDistCode() != null)
		 * sb.append(project.getSubDistName().toUpperCase()).append("/"); if
		 * (project.getApprovingAuthority() != null)
		 * sb.append(project.getValidTpName().toUpperCase()).append("/");
		 */

		int yr = cal.get(Calendar.YEAR);
		int mnth = cal.get(Calendar.MONTH) + 1;
		int dt = cal.get(Calendar.DATE);
		yr = yr % 100;

		if (dt < 10) {
			sb.append("0").append(dt);
		} else {
			sb.append(dt);
		}
		if (mnth < 10) {
			sb.append("0").append(mnth);
		} else {
			sb.append(mnth);
		}
		if (yr < 10) {
			sb.append("0").append(yr);
		} else {
			sb.append(yr);
		}
		sb.append("/");

		// String str = "" + project.getId();
		String str = "AWS/LN/";
		while (str.length() < 6) {
			str = "0" + str;
		}
		sb.append(str);
		logger.debug("getApplicationNo::Ended::");
		return sb.toString();
	}
}
