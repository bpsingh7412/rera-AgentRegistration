package in.gov.agentregistration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.gov.agentregistration.model.AgentRegistrationModel;

@Repository
public interface AgentRegistrationDao extends CrudRepository<AgentRegistrationModel, Long> {
	public List<AgentRegistrationModel> findByCompanyRegistrationNumber(String companyRegistrationNumber);

	public AgentRegistrationModel findByEmailId(String emailId);

	// public AgentRegistrationModel
	// findByCompanyRegistrationNumberAndPromoterType(String
	// companyRegistrationNumber,String promoterType);
	public List<AgentRegistrationModel> findByStatus(String status);

	// public AgentRegistrationModel getAgentDetailsByCinAndType(String cin, String
	// type);
	public AgentRegistrationModel findByWfoId(String wfoId);

	@Query(value = "SELECT XX.PTEAM FROM  (SELECT MAX(P.CREATED_ON) CT,P.TPSR_TEAM PTEAM FROM tt_agent_reg P "
			+ "GROUP BY P.TPSR_TEAM)XX WHERE XX.PTEAM IS NOT NULL ORDER BY XX.CT DESC", nativeQuery = true)
	List<Object> findTpSrTeamToAssign();

	@Query(value = "SELECT XX.PTEAM FROM  (SELECT MAX(P.CREATED_ON) CT,P.PR_TEAM PTEAM FROM tt_agent_reg P "
			+ "GROUP BY P.PR_TEAM)XX WHERE XX.PTEAM IS NOT NULL ORDER BY XX.CT DESC", nativeQuery = true)
	List<Object> findTeamToAssign();

	@Query(value = "SELECT XX.PTEAM FROM  (SELECT MAX(P.CREATED_ON) CT,P.FIN_TEAM PTEAM FROM tt_agent_reg P "
			+ "GROUP BY P.FIN_TEAM)XX WHERE XX.PTEAM IS NOT NULL ORDER BY XX.CT DESC", nativeQuery = true)
	List<Object> findFinTeamToAssign();

	@Query(value = "SELECT XX.PTEAM FROM  (SELECT MAX(P.CREATED_ON) CT,P.LG_TEAM PTEAM FROM tt_agent_reg P "
			+ "GROUP BY P.LG_TEAM)XX WHERE XX.PTEAM IS NOT NULL ORDER BY XX.CT DESC", nativeQuery = true)
	List<Object> findLegalTeamToAssign();

	@Query(value = " Select p from AgentRegistrationModel p where p.stateCode=?1")
	List<AgentRegistrationModel> getProjectByStateCode(String stateCode);

	@Query(value = " Select p from AgentRegistrationModel p where p.stateCode=?1 and p.finState=?2 and p.finTeam=?3")
	List<AgentRegistrationModel> getProjectForTeamByFinStateCode(String stateCode, String finState, String team);

	@Query(value = " Select p from AgentRegistrationModel p where p.stateCode=?1 and p.tpState=?2 and p.tpTeam=?3")
	List<AgentRegistrationModel> getProjectForTeamBytpStateCode(String stateCode, String tpState, String team);

	@Query(value = " Select p from AgentRegistrationModel p where p.stateCode=?1 and p.legalState=?2 and p.legalTeam=?3")
	List<AgentRegistrationModel> getProjectForTeamByLegalStateCode(String stateCode, String legalState, String team);

	@Query(value = " Select p from AgentRegistrationModel p where p.stateCode=?1 and p.finState=?2 ")
	List<AgentRegistrationModel> getProjectForTeamByFinStateCode(String stateCode, String finState);

	@Query(value = " Select p from AgentRegistrationModel p where p.stateCode=?1 and p.tpState=?2 and p.tpSrTeam=?3")
	List<AgentRegistrationModel> getProjectForTeamBytpSrStateCode(String stateCode, String tpState, String team);

	@Query(value = " Select p from AgentRegistrationModel p where p.stateCode=?1 and p.tpState=?2 ")
	List<AgentRegistrationModel> getProjectForTeamBytpStateCode(String stateCode, String tpState);

	@Query(value = " Select p from AgentRegistrationModel p where p.stateCode=?1 and p.legalState=?2")
	List<AgentRegistrationModel> getProjectForTeamByLegalStateCode(String stateCode, String legalState);

	@Query(value = " Select p from AgentRegistrationModel p where p.stateCode not in ('APPROVED','REJECTED','SAVE_AS_DRAFT') and datediff(CURDATE(),p.createdOn)>30")
	List<AgentRegistrationModel> getPendingWithProjectList();

	public List<AgentRegistrationModel> findByAgentAckNo(String no);

	public List<AgentRegistrationModel> findByEmailIdAndStatus(String emailId, String agentEnquiry);


}