package in.gov.agentregistration.wfo.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "AgentWfoDocModel")
@Table(name = "TL_AGENT_WFO_DOC")
public class AgentWfoDocModel implements Serializable {
	private static final long serialVersionUID = 1463124430214748765L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "AGENT_ID")
	private Long agentId;
	
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="AGENT_WFO_DOC_FK")
	private List<AgentOtherStateDtlModel> agentOtherStateList;
	
	@Column(name = "FIRST_FY_YEAR")
	private Date firstFYYear;
	
	@Column(name = "THIRD_FY_YEAR")
	private Date thirdFYYear;
	
	@Column(name = "STATE_NAME")
	private String stateName;
	
	@Column(name = "STATE_CODE")
	private String stateCode;
	
	@Column(name = "DISTRICT_NAME")
	private String districtName;
	
	@Column(name = "DISTRICT_CODE")
	private String districtCode;
	
	@Column(name = "TALUKA_NAME")
	private String talukaName;
	
	@Column(name = "ITR_RETURN_DOC_YEAR1_ID")
	private Long itrReturnDocYear1Id;
	
	@Column(name = "ITR_RETURN_DOC_YEAR1_UID")
	private String itrReturnDocYear1UId;
	
	@Column(name = "ITR_RETURN_DOC_YEAR2_ID")
	private Long itrReturnDocYear2Id;
	
	@Column(name = "ITR_RETURN_DOC_YEAR2_UID")
	private String itrReturnDocYear2UId;
	
	@Column(name = "ITR_RETURN_DOC_YEAR3_ID")
	private Long itrReturnDocYear3Id;
	
	@Column(name = "ITR_RETURN_DOC_YEAR3_UID")
	private String itrReturnDocYear3UId;
  
    @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", updatable = false)
	private Calendar createdOn;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON")
	private Calendar updatedOn;

	@Column(name = "STATUS")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public List<AgentOtherStateDtlModel> getAgentOtherStateList() {
		return agentOtherStateList;
	}

	public void setAgentOtherStateList(List<AgentOtherStateDtlModel> agentOtherStateList) {
		this.agentOtherStateList = agentOtherStateList;
	}

	public Date getFirstFYYear() {
		return firstFYYear;
	}

	public void setFirstFYYear(Date firstFYYear) {
		this.firstFYYear = firstFYYear;
	}

	public Date getThirdFYYear() {
		return thirdFYYear;
	}

	public void setThirdFYYear(Date thirdFYYear) {
		this.thirdFYYear = thirdFYYear;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getTalukaName() {
		return talukaName;
	}

	public void setTalukaName(String talukaName) {
		this.talukaName = talukaName;
	}

	public Long getItrReturnDocYear1Id() {
		return itrReturnDocYear1Id;
	}

	public void setItrReturnDocYear1Id(Long itrReturnDocYear1Id) {
		this.itrReturnDocYear1Id = itrReturnDocYear1Id;
	}

	public String getItrReturnDocYear1UId() {
		return itrReturnDocYear1UId;
	}

	public void setItrReturnDocYear1UId(String itrReturnDocYear1UId) {
		this.itrReturnDocYear1UId = itrReturnDocYear1UId;
	}

	public Long getItrReturnDocYear2Id() {
		return itrReturnDocYear2Id;
	}

	public void setItrReturnDocYear2Id(Long itrReturnDocYear2Id) {
		this.itrReturnDocYear2Id = itrReturnDocYear2Id;
	}

	public String getItrReturnDocYear2UId() {
		return itrReturnDocYear2UId;
	}

	public void setItrReturnDocYear2UId(String itrReturnDocYear2UId) {
		this.itrReturnDocYear2UId = itrReturnDocYear2UId;
	}

	public Long getItrReturnDocYear3Id() {
		return itrReturnDocYear3Id;
	}

	public void setItrReturnDocYear3Id(Long itrReturnDocYear3Id) {
		this.itrReturnDocYear3Id = itrReturnDocYear3Id;
	}

	public String getItrReturnDocYear3UId() {
		return itrReturnDocYear3UId;
	}

	public void setItrReturnDocYear3UId(String itrReturnDocYear3UId) {
		this.itrReturnDocYear3UId = itrReturnDocYear3UId;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Calendar getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Calendar updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
  
}
