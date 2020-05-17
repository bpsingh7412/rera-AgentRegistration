package in.gov.agentregistration.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.agentregistration.model.YearlyStatusModel;

@Repository
public interface YearlyStatusDao extends CrudRepository<YearlyStatusModel, Long> {

	List<YearlyStatusModel> findByAgentId(Long agentId);

}
