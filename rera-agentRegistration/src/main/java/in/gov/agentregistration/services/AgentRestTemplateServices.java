package in.gov.agentregistration.services;

import java.util.Properties;

import org.springframework.web.client.RestTemplate;

import in.gov.agentregistration.configuration.OTPBean;
import in.gov.agentregistration.model.DmsModel;
import in.gov.agentregistration.model.UserAccountModel;

public class AgentRestTemplateServices {

	static Properties properties;

	public static UserAccountModel createAccount(UserAccountModel entity, String creatAccUrl) {
		// System.out.println("createAccount
		// :"+properties.getProperty("URL_CREATE_USER_ACC"));
		System.out.println("createAccount :" + creatAccUrl);
		RestTemplate restTemplate = new RestTemplate();
		// UserAccountModel addUser =
		// restTemplate.postForObject(properties.getProperty("URL_CREATE_USER_ACC"),
		// entity,UserAccountModel.class);
		UserAccountModel addUser = restTemplate.postForObject(creatAccUrl, entity, UserAccountModel.class);
		return addUser;
	}

	public static DmsModel saveDoc(DmsModel entity, String vdmsUrl) {
		System.out.println("saveDoc :URL_CREATE_VDMS");
		RestTemplate restTemplate = new RestTemplate();
		DmsModel document = restTemplate.postForObject(vdmsUrl, entity, DmsModel.class);
		return document;
	}

	public static OTPBean sendOptToUser(OTPBean otp, String otpUrl) {
		// System.out.println("createAccount
		// :"+properties.getProperty("URL_CREATE_USER_ACC"));
		RestTemplate restTemplate = new RestTemplate();
		// UserAccountModel addUser =
		// restTemplate.postForObject(properties.getProperty("URL_CREATE_USER_ACC"),
		// entity,UserAccountModel.class);
		otp = restTemplate.postForObject(otpUrl, otp, OTPBean.class);
		return otp;
	}

}
