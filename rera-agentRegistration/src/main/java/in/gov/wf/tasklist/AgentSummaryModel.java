package in.gov.wf.tasklist;

import java.util.Calendar;

import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.wf.util.DateUtil;

public class AgentSummaryModel {

	private Long agentId;
	private String agentFName;
	private String agentMName;
	private String agentLName;
	private String stateName;
	private String status;
	private String submissionDate;
	private String wfoId;
	private String ackNo;
	private String team;
	private Calendar lastModifiedOn;

	public AgentSummaryModel() {

	}

	public AgentSummaryModel(AgentRegistrationModel agentModel) {
		this.agentId = agentModel.getId();
		this.wfoId = agentModel.getWfoId();
		this.ackNo = agentModel.getAgentAckNo();
		this.agentFName = agentModel.getAgentFirstName();
		this.agentMName = agentModel.getAgentMName();
		this.agentLName = agentModel.getAgentLName();
		this.submissionDate = DateUtil.getDateStr(agentModel.getSubmissionDate());
		this.stateName = agentModel.getStateName();
		this.status = agentModel.getStateCode();
		this.team = agentModel.getTpTeam();
		this.lastModifiedOn = agentModel.getModdifiedOn();
	}

	public String getWfoId() {
		return wfoId;
	}

	public void setWfoId(String wfoId) {
		this.wfoId = wfoId;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getAgentFName() {
		return agentFName;
	}

	public void setAgentFName(String agentFName) {
		this.agentFName = agentFName;
	}

	public String getAgentMName() {
		return agentMName;
	}

	public void setAgentMName(String agentMName) {
		this.agentMName = agentMName;
	}

	public String getAgentLName() {
		return agentLName;
	}

	public void setAgentLName(String agentLName) {
		this.agentLName = agentLName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getAckNo() {
		return ackNo;
	}

	public void setAckNo(String ackNo) {
		this.ackNo = ackNo;
	}

	public Calendar getLastModifiedOn() {
		return lastModifiedOn;
	}

	public void setLastModifiedOn(Calendar lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

}
