package in.gov.agentregistration.services;

import java.util.List;

import in.gov.agentregistration.model.AgentWorkStatusModel;

public interface AgentWorkStatusService {
	public List<AgentWorkStatusModel> saveList(List<AgentWorkStatusModel> workStatuList);

	public List<AgentWorkStatusModel> findByYearlyId(Long yId);
}
