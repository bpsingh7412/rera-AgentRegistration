package in.gov.agentregistration.services;

import java.util.List;

import in.gov.agentregistration.model.YearlyStatusModel;

public interface YearlyStatusService {
	public YearlyStatusModel save(YearlyStatusModel yStatus);

	public List<YearlyStatusModel> findByAgentId(Long agentId);

	public YearlyStatusModel getYearlyStatusById(Long yId);
}
