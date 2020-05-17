package in.gov.agentregistration.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.gov.agentregistration.model.AgentPaymentDetailModel;

@Repository
public interface AgentPaymentDetailDao extends CrudRepository<AgentPaymentDetailModel, Long> {

}
