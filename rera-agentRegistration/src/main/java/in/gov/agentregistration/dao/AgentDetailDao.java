package in.gov.agentregistration.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.gov.agentregistration.model.AgentDetailModel;

@Repository
public interface AgentDetailDao extends CrudRepository<AgentDetailModel, Long> {
	// public List<AgentRegistrationModel> findByCompanyRegistrationNumber(String
	// companyRegistrationNumber);
	// public AgentDetailModel findByMobileNo(String mobileNo);
	public AgentDetailModel findByPanNo(String panNo);

	public AgentDetailModel findByAdharNo(String adharNo);

}