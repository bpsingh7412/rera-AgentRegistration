package in.gov.agentregistration.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "AgentWorkStatusModel")
@Table(name = "TL_AGENT_WORK_STATUS")
public class AgentWorkStatusModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 18978967656L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WORK_ID")
	private Long workStatusId;

	@Column(name = "YEARLY_STATUS_WORK_FK")
	private Long yearlyId;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "WORK_STATUS_FK")
	private List<SaleUnitsDetails> unitsList;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "PROJECT_ID")
	private Long projectId;

	@Column(name = "PROJECT_NAME")
	private String projectName;

	@Column(name = "PROJECT_REG_NO")
	private String projectRegNo;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", updatable = false)
	private Calendar createdOn;

	@Column(name = "CREATED_BY")
	private Long createdBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFIED_ON")
	private Calendar lastModifiedOn;

	@Column(name = "LAST_MODIFIED_BY")
	private Long lastModifiedBy;
	
	@Column(name = "PROMOTER_STATUS")
	private String promoterStatus;

	public List<SaleUnitsDetails> getUnitsList() {
		return unitsList;
	}

	public void setUnitsList(List<SaleUnitsDetails> unitsList) {
		this.unitsList = unitsList;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getWorkStatusId() {
		return workStatusId;
	}

	public void setWorkStatusId(Long workStatusId) {
		this.workStatusId = workStatusId;
	}

	public Long getYearlyId() {
		return yearlyId;
	}

	public void setYearlyId(Long yearlyId) {
		this.yearlyId = yearlyId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectRegNo() {
		return projectRegNo;
	}

	public void setProjectRegNo(String projectRegNo) {
		this.projectRegNo = projectRegNo;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Calendar getLastModifiedOn() {
		return lastModifiedOn;
	}

	public void setLastModifiedOn(Calendar lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

	public Long getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(Long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getPromoterStatus() {
		return promoterStatus;
	}

	public void setPromoterStatus(String promoterStatus) {
		this.promoterStatus = promoterStatus;
	}

}
