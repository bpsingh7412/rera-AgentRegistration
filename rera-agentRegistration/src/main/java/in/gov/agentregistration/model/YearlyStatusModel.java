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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "YearlyStatusModel")
@Table(name = "TL_AGENT_YEARLY_STATUS")
public class YearlyStatusModel implements Serializable {
    
	/**
	 * 
	 */

	private static final long serialVersionUID = 765576661L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "YR_ID")
	private Long yearlyId;

	@Column(name = "AGENT_ID")
	private Long agentId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "YEARLY_STATUS_WORK_FK")
	private List<AgentWorkStatusModel> agentWorkList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "YEARLY_STATUS_DEF_FK")
	private List<DefaulterStatusModel> defaulerList;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PAYMENT_FK")
	private AgentWorkPaymentDetailsModel payment;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "ACT_END_DATE")
	private Date actualEndDate;

	@Column(name = "DEFAULT_FLAG")
	private String defaulterFlag;

	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "DISPLAY_NAME")
	private String displayName;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "IS_ACTIVE")
	private Integer isActive;

	@Column(name = "WS_ACK_NO")
	private String WorkStatusAckNo;

	@Column(name = "CREATED_BY")
	private Long createdBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", updatable = false)
	private Calendar createdOn;

	@Column(name = "LAST_MODIFIED_BY")
	private Long lastModifiedBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFIED_ON")
	private Calendar lastModifiedOn;

	public String getWorkStatusAckNo() {
		return WorkStatusAckNo;
	}

	public void setWorkStatusAckNo(String workStatusAckNo) {
		WorkStatusAckNo = workStatusAckNo;
	}

	public Long getYearlyId() {
		return yearlyId;
	}

	public void setYearlyId(Long yearlyId) {
		this.yearlyId = yearlyId;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public List<AgentWorkStatusModel> getAgentWorkList() {
		return agentWorkList;
	}

	public void setAgentWorkList(List<AgentWorkStatusModel> agentWorkList) {
		this.agentWorkList = agentWorkList;
	}

	public List<DefaulterStatusModel> getDefaulerList() {
		return defaulerList;
	}

	public void setDefaulerList(List<DefaulterStatusModel> defaulerList) {
		this.defaulerList = defaulerList;
	}

	public AgentWorkPaymentDetailsModel getPayment() {
		return payment;
	}

	public void setPayment(AgentWorkPaymentDetailsModel payment) {
		this.payment = payment;
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

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public String getDefaulterFlag() {
		return defaulterFlag;
	}

	public void setDefaulterFlag(String defaulterFlag) {
		this.defaulterFlag = defaulterFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
