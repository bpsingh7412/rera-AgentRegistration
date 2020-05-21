package in.gov.agentregistration.services;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import in.gov.agentregistration.model.AgentDetailModel;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.agentregistration.model.ResponseModel;
import in.gov.agentregistration.model.YearlyStatusDto;

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

	public Map<String, String> getFyYearList(Calendar dateOfIncorporation, String[] cut);

	public ResponseModel findAgentListbyFilter(YearlyStatusDto dto);

}
