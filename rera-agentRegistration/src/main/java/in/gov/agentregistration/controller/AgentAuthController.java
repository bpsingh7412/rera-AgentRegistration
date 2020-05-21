package in.gov.agentregistration.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import in.gov.agentregistration.exception.ResourceNotFoundException;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.agentregistration.model.DmsModel;
import in.gov.agentregistration.model.EnquiryModel;
import in.gov.agentregistration.model.EnquiryTrxModel;
import in.gov.agentregistration.model.ResponseModel;
import in.gov.agentregistration.model.UserAccountModel;
import in.gov.agentregistration.security.AuthUser;
import in.gov.agentregistration.services.AgentRegistrationService;
import in.gov.agentregistration.services.DmsService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/agent_reg/secure/agent")
public class AgentAuthController {
	private static final Logger logger = LogManager.getLogger(AgentAuthController.class);
	@Autowired
	AgentRegistrationService services;
	@Autowired
	private Environment env;
	@Autowired
	DmsModel dmsModel;
	@Autowired
	DmsService dmsService;
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	HttpSession session;

	@Autowired
	UserAccountModel userAccountDetailsModel;

	
	@GetMapping("/dtl-by-login")
	public ResponseEntity<?> getAgentDetailByToken(HttpServletRequest req) throws ResourceNotFoundException {
		AuthUser user = AuthUser.getLoggedInUser(req);
		AgentRegistrationModel l = services.getAgentDetailsById(user.getProfileId());
		Optional.ofNullable(l).orElseThrow(() -> new ResourceAccessException("Not Found"));
		ResponseModel rs = new ResponseModel();
		rs.setData(l);
		rs.setMessage("Data found");
		rs.setStatus("200");
		return ResponseEntity.ok(rs);
	}
	
	@GetMapping("/dtl-fy-list")
	public ResponseEntity<?> getAgentFyList(HttpServletRequest req) throws ResourceNotFoundException {
		AuthUser user = AuthUser.getLoggedInUser(req);
		AgentRegistrationModel l = services.getAgentDetailsById(user.getProfileId());
		String [] cut = {"01","10"};
		Map<String, String> map = services.getFyYearList(l.getDateOfIncorporation(),cut);
		ResponseModel rs = new ResponseModel();
		rs.setData(map);
		rs.setAgenId(user.getProfileId());
		rs.setId(l.getAgentId());
		rs.setMessage("Data found");
		rs.setStatus("200");
		return ResponseEntity.ok(rs);
	}
	
	
	@PostMapping("/create-agent-reg-eqr")
	public ResponseEntity<?> createAgentRegEqr(@RequestBody EnquiryTrxModel eqr) throws ResourceNotFoundException {
		ResponseModel rs = new ResponseModel();
		EnquiryModel em = new EnquiryModel();
		Optional.ofNullable(eqr).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
		AgentRegistrationModel agent = services.getAgentDetailsById(eqr.getAgentId());

		em.setAuthorityId(eqr.getAuthorityId());
		em.setAuthorityType(eqr.getAuthorityType());
		em.setStatus(eqr.getStatus());
		em.setAuthRemark(eqr.getAuthRemark());
		agent.getEnqiryList().add(em);
		agent.setStatus(eqr.getStatus());
		agent = services.saveAgentRegistrationDetails(agent);

		if (agent != null) {
			rs.setAgenId(null);
			rs.setData(agent);
			rs.setMessage("AGENT-UPDATED");
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setMessage("AGENT-NOT-UPDATED");
			rs.setData(agent);
			rs.setStatus("400");
		}
		return ResponseEntity.ok(rs);

	}

	@GetMapping("/get-agent-by-regNo-or-ack/{no}")
	public ResponseEntity<?> getAgentDetailById(@PathVariable(value = "no") String no) throws ResourceNotFoundException {
		ResponseModel rs = new ResponseModel();
		List<AgentRegistrationModel> agent = null;
		agent = services.findByCompanyRegistrationNumber(no);
		 if(agent.size()==0) {
		   agent = services.findByAgentAckNo(no);
		 }
		Optional.ofNullable(agent).orElseThrow(() -> new ResourceAccessException("Not Found"));
		if (agent != null) {
			rs.setAgenId(null);
			rs.setData(agent);
			rs.setMessage("AGENT-UPDATED");
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setMessage("AGENT-NOT-UPDATED");
			rs.setData(agent);
			rs.setStatus("400");
		}
		return ResponseEntity.ok(rs);

	}

}
