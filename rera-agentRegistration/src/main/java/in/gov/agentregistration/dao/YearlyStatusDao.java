package in.gov.agentregistration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.agentregistration.model.YearlyStatusModel;

@Repository
public interface YearlyStatusDao extends CrudRepository<YearlyStatusModel, Long> {

	List<YearlyStatusModel> findByAgentId(Long agentId);
	
	@Query(value = "SELECT a.* from tl_agent_yearly_status a where a.status like ?1 and a.status !='IN_ACTIVE'", nativeQuery = true)
	List<YearlyStatusModel> getAllAgentFilter(String status);

}
