package in.gov.agentregistration.wfo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "AgentOtherStateDtlModel")
@Table(name = "TL_AGENT_OTH_STATE_DTL")
public class AgentOtherStateDtlModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	
	@Column(name="AGENT_WFO_DOC_FK")
	private Long agentWfoDocIdFk;
	
	@Column(name="RERA_REG_NO")
	private String reraRegistrationNo;
	
	@Column(name="OTHER_STATE_NAME")
	private String otherStateName;
	
	@Column(name="OTHER_STATE_CODE")
	private String otherStateCode;
	
	@Column(name="OTHER_DISTRICT_NAME")
	private String otherDistrictName;
	
	@Column(name="OTHER_DISTRICT_CODE")
	private String otherDistrictCode;
	
	@Column(name="OTHER_TALUKA_NAME")
	private String otherTalukaName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAgentWfoDocIdFk() {
		return agentWfoDocIdFk;
	}

	public void setAgentWfoDocIdFk(Long agentWfoDocIdFk) {
		this.agentWfoDocIdFk = agentWfoDocIdFk;
	}

	public String getReraRegistrationNo() {
		return reraRegistrationNo;
	}

	public void setReraRegistrationNo(String reraRegistrationNo) {
		this.reraRegistrationNo = reraRegistrationNo;
	}

	public String getOtherStateName() {
		return otherStateName;
	}

	public void setOtherStateName(String otherStateName) {
		this.otherStateName = otherStateName;
	}

	public String getOtherStateCode() {
		return otherStateCode;
	}

	public void setOtherStateCode(String otherStateCode) {
		this.otherStateCode = otherStateCode;
	}

	public String getOtherDistrictName() {
		return otherDistrictName;
	}

	public void setOtherDistrictName(String otherDistrictName) {
		this.otherDistrictName = otherDistrictName;
	}

	public String getOtherDistrictCode() {
		return otherDistrictCode;
	}

	public void setOtherDistrictCode(String otherDistrictCode) {
		this.otherDistrictCode = otherDistrictCode;
	}

	public String getOtherTalukaName() {
		return otherTalukaName;
	}

	public void setOtherTalukaName(String otherTalukaName) {
		this.otherTalukaName = otherTalukaName;
	}
	
}
