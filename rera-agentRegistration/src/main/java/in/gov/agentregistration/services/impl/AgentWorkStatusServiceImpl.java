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
		// TODO Auto-generated method stub

		List<AgentWorkStatusModel> list = new ArrayList<AgentWorkStatusModel>();
		agentWorkStatusDao.saveAll(workStatuList).iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public List<AgentWorkStatusModel> findByYearlyId(Long yId) {
		// TODO Auto-generated method stub
		return agentWorkStatusDao.findByYearlyId(yId);
	}

}
