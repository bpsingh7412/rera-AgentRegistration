package in.gov.agentregistration.dao;

import javax.validation.Valid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.gov.agentregistration.wfo.model.AgentWfoDocModel;

@Repository
public interface AgentWfoDocDao extends CrudRepository<AgentWfoDocModel, Long> {

	AgentWfoDocModel findByAgentId(@Valid Long agentId);

}
