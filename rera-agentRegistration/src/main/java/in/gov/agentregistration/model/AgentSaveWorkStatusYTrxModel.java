package in.gov.agentregistration.model;

import java.util.List;

public class AgentSaveWorkStatusYTrxModel {
	private Long yearId;
	private String remark;
	private List<AgentWorkStatusModel> agentWorkStatusList;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getYearId() {
		return yearId;
	}

	public void setYearId(Long yearId) {
		this.yearId = yearId;
	}

	public List<AgentWorkStatusModel> getAgentWorkStatusList() {
		return agentWorkStatusList;
	}

	public void setAgentWorkStatusList(List<AgentWorkStatusModel> agentWorkStatusList) {
		this.agentWorkStatusList = agentWorkStatusList;
	}

}
