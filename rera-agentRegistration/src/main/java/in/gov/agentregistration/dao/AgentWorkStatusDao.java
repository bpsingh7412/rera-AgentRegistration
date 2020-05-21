package in.gov.agentregistration.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.gov.agentregistration.model.AgentWorkStatusModel;

@Repository
public interface AgentWorkStatusDao extends CrudRepository<AgentWorkStatusModel, Long> {
	List<AgentWorkStatusModel> findByYearlyId(Long yId);
	List<AgentWorkStatusModel> findByProjectId(Long projectId);
}
