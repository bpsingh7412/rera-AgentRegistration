package in.gov.agentregistration.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.agentregistration.model.AgentWorkPaymentDetailsModel;

@Repository
public interface AgentWorkPaymentDetailsDao extends CrudRepository<AgentWorkPaymentDetailsModel, Long> {

}
