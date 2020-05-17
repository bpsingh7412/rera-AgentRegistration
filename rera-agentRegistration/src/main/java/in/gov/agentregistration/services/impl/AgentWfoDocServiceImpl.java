package in.gov.agentregistration.services.impl;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gov.agentregistration.dao.AgentWfoDocDao;
import in.gov.agentregistration.services.AgentWfoDocService;
import in.gov.agentregistration.wfo.model.AgentWfoDocModel;

@Service
@Transactional
public class AgentWfoDocServiceImpl implements AgentWfoDocService {

	@Autowired
	AgentWfoDocDao agentWfoDocDao;

	@Override
	public AgentWfoDocModel saveAgentEntity(@Valid AgentWfoDocModel entity) {
		// TODO Auto-generated method stub
		return agentWfoDocDao.save(entity);
	}

	@Override
	public AgentWfoDocModel findByAgentId(@Valid Long agentId) {
		// TODO Auto-generated method stub
		return agentWfoDocDao.findByAgentId(agentId);
	}

	@Override
	public AgentWfoDocModel findById(@Valid Long id) {
		// TODO Auto-generated method stub
		return agentWfoDocDao.findById(id).get();
	}
	
}
