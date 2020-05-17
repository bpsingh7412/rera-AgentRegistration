package in.gov.agentregistration.model;

public class EnquiryTrxModel {
	private Long agentId;
	private String status;
	private String AuthRemark;
	private Long authorityId;
	private String authorityType;

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuthRemark() {
		return AuthRemark;
	}

	public void setAuthRemark(String authRemark) {
		AuthRemark = authRemark;
	}

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}

}
