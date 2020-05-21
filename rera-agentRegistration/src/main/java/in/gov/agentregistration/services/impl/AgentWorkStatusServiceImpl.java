package in.gov.agentregistration.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gov.agentregistration.dao.AgentWorkStatusDao;
import in.gov.agentregistration.model.AgentWorkStatusModel;
import in.gov.agentregistration.services.AgentWorkStatusService;

@Service
@Transactional
public class AgentWorkStatusServiceImpl implements AgentWorkStatusService {

	@Autowired
	AgentWorkStatusDao agentWorkStatusDao;

	@Override
	public List<AgentWorkStatusModel> saveList(List<AgentWorkStatusModel> workStatuList) {

		List<AgentWorkStatusModel> list = new ArrayList<AgentWorkStatusModel>();
		agentWorkStatusDao.saveAll(workStatuList).iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public List<AgentWorkStatusModel> findByYearlyId(Long yId) {
		return agentWorkStatusDao.findByYearlyId(yId);
	}

	@Override
	public List<AgentWorkStatusModel> findByProjectId(Long projectId) {
		return agentWorkStatusDao.findByProjectId(projectId);
	}

	@Override
	public AgentWorkStatusModel findByWorkStatusId(Long workStatusId) {
		return agentWorkStatusDao.findById(workStatusId).get();
	}

	@Override
	public AgentWorkStatusModel save(AgentWorkStatusModel model) {
		return agentWorkStatusDao.save(model);
	}
}
