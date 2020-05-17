package in.gov.wf.tasklist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gov.agentregistration.dao.AgentRegistrationDao;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.wf.security.AuthUser;

@Service
public class ProjectTaskListService {

	@Autowired
	AgentRegistrationDao dao;

	public List<AgentSummaryModel> getProjectList(AuthUser user) {

		List<AgentRegistrationModel> pList = null;
		if (user.getUserType().equals("TPJR")) {
			pList = this.getProjectListForTPJR(user.getTeam());
		} else if (user.getUserType().equals("TPSR")) {
			pList = this.getProjectListForTPSR(user.getTeam());
		} else if (user.getUserType().equals("FIN")) {
			pList = this.getProjectListForFinUser(user.getTeam());
		} else if (user.getUserType().equals("LEGAL")) {
			pList = this.getProjectListForLegalUser(user.getTeam());
		} else if (user.getUserType().equals("TPOFFICER")) {
			pList = this.getProjectListForTpOfficer();
		} else if (user.getUserType().equals("FINCONTROLLER")) {
			pList = this.getProjectListForFinController();
		} else if (user.getUserType().equals("LEGALOFFICER")) {
			pList = this.getProjectListForLegalOfficer();
		} else if (user.getUserType().equals("MEMBERONE")) {
			pList = this.getProjectListForMemberOne();
		} else if (user.getUserType().equals("SECRETRY")) {
			pList = this.getProjectListForSecretry();
		} else if (user.getUserType().equals("CHAIRPERSON")) {
			pList = this.getProjectListForCP();
		} else if (user.getUserType().equals("SECRETRYPA")) {
			pList = this.getProjectListForSecretryPA();
		} else if (user.getUserType().equals("ENQOFFICER")) {
			pList = this.getProjectListForEnquiry();
		}

		return populat(pList);
	}

	private List<AgentRegistrationModel> getProjectListForTPJR(String team) {
		return dao.getProjectForTeamBytpStateCode("LEVELONE", "NEW", team);
	}

	private List<AgentRegistrationModel> getProjectListForTPSR(String team) {
		return dao.getProjectForTeamBytpSrStateCode("LEVELONE", "TPSR", team);
	}

	private List<AgentRegistrationModel> getProjectListForFinUser(String team) {
		return dao.getProjectForTeamByFinStateCode("LEVELONE", "NEW", team);
	}

	private List<AgentRegistrationModel> getProjectListForLegalUser(String team) {
		return dao.getProjectForTeamByLegalStateCode("LEVELONE", "NEW", team);
	}

	private List<AgentRegistrationModel> getProjectListForTpOfficer() {
		return dao.getProjectForTeamBytpStateCode("LEVELONE", "OFFC");
	}

	private List<AgentRegistrationModel> getProjectListForFinController() {
		return dao.getProjectForTeamByFinStateCode("LEVELONE", "OFFC");
	}

	private List<AgentRegistrationModel> getProjectListForLegalOfficer() {
		return dao.getProjectForTeamByLegalStateCode("LEVELONE", "OFFC");
	}

	private List<AgentRegistrationModel> getProjectListForMemberOne() {
		return dao.getProjectByStateCode("MEMBERONE");
	}

	private List<AgentRegistrationModel> getProjectListForSecretry() {
		return dao.getProjectByStateCode("SECRETRY");
	}

	private List<AgentRegistrationModel> getProjectListForCP() {
		return dao.getProjectByStateCode("CHAIRPERSON");
	}

	private List<AgentRegistrationModel> getProjectListForSecretryPA() {
		return dao.getProjectByStateCode("SECRETRYPA");
	}

	private List<AgentRegistrationModel> getProjectListForEnquiry() {
		return dao.getProjectByStateCode("ENQUIRY");
	}

	private List<AgentSummaryModel> populat(List<AgentRegistrationModel> plist) {
		List<AgentSummaryModel> projectList = new ArrayList<AgentSummaryModel>();
		if (plist != null) {
			for (AgentRegistrationModel project : plist) {
				projectList.add(new AgentSummaryModel(project));
			}
		}
		return projectList;
	}

	public List<AgentSummaryModel> getPendingWithAuthList() {
		return populat(dao.getPendingWithProjectList());
	}

}
