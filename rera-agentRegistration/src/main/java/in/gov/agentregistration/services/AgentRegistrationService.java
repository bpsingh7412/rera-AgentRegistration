package in.gov.agentregistration.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import in.gov.agentregistration.exception.ResourceNotFoundException;
import in.gov.agentregistration.model.AgentDetailModel;
import in.gov.agentregistration.model.AgentPaymentDetailModel;
import in.gov.agentregistration.model.AgentRegistrationModel;

public interface AgentRegistrationService {

	public AgentRegistrationModel saveAgentRegistrationDetails(AgentRegistrationModel entitiy);

	public List<AgentRegistrationModel> getAllAgentRegistration();

	public AgentRegistrationModel getAgentDetailsById(Long id);

	public AgentRegistrationModel getAgentDetailsById(String emailId);

	public AgentRegistrationModel getAgentDetailsByEmailId(String emailId);

	public boolean isAgentRegistrationExist(AgentRegistrationModel entitiy);

	public AgentRegistrationModel getAgentDetailsByCinAndType(String cin, String type);

	public AgentDetailModel getAgentDetailsByPanNo(String panNo);

	public AgentRegistrationModel updateAgentRegistration(AgentRegistrationModel Model);

	public List<AgentRegistrationModel> findByStatus(String pendingWithAuthority);

	public List<AgentRegistrationModel> findByCompanyRegistrationNumber(String no);

	public List<AgentRegistrationModel> findByAgentAckNo(String no);

	public List<AgentRegistrationModel> findByEmailIdAndStatus(String no, String agentEnquiry);

}
