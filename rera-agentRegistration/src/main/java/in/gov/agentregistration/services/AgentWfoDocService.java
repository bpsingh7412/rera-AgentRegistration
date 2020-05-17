package in.gov.agentregistration.services;

import javax.validation.Valid;

import in.gov.agentregistration.wfo.model.AgentWfoDocModel;

public interface AgentWfoDocService {
    AgentWfoDocModel saveAgentEntity(@Valid AgentWfoDocModel entity);
    AgentWfoDocModel findByAgentId(@Valid Long agentId);
	AgentWfoDocModel findById(@Valid Long id);

}
