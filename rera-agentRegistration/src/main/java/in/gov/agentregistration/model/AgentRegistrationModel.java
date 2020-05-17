package in.gov.agentregistration.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import in.gov.wf.model.WFEntity;

@Entity(name = "AgentRegistrationModel")
@Table(name = "TT_AGENT_REG")
public class AgentRegistrationModel implements Serializable, WFEntity {
	private static final long serialVersionUID = 1463124430214748765L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AGNT_ID")
	private Long agentId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "AGENT_REG_ENQ_MAPPING")
	private List<EnquiryModel> enqiryList;

	@NotBlank(message = "Agent Name is required.")
	@Size(min = 1, max = 200, message = "Name should have atleast 1 and max 200 characters")
	@Column(name = "AGENT_NAME")
	private String agentName;

	@Column(name = "AGENT_FIRSTNAME")
	private String agentFirstName;

	// @NotBlank(message="Promoter type is required.")
	@Column(name = "AGENT_LNAME")
	private String agentLName;

	@Column(name = "AGENT_MNAME")
	private String agentMName;

	@NotBlank(message = "Agent type is required.")
	@Column(name = "AGENT_TYPE")
	private String agentType;

	@Column(name = "AGENT_COM_REG_NO")
	private String companyRegistrationNumber;// cin

	@Column(name = "AGENT_DATE_OF_INCORPORATION")
	private Calendar dateOfIncorporation;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "LOGIN_ID")
	private String loginId;

	@NotBlank(message = "Email is required.")
	@Email(message = "Please enter email in proper formate.")
	@Column(name = "AGENT_EMAILID")
	private String emailId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENT_ASSOCIATE_FK")
	private List<AgentAssosiateModel> assosiateList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENT_AUTHSIGN_FK")
	private List<AgentAuthorizedSignatoryModel> authorizedSignatoryList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENT_DOC_FK")
	private List<AgentDocumentModel> document;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", updatable = false)
	private Calendar createdOn;

	@Column(name = "MODIFIED_ON")
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Calendar moddifiedOn;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "AGENT_PAYMENT_FK")
	private AgentPaymentDetailModel paymentDetailsModel;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "AGENT_DETAIL_FK")
	private AgentDetailModel agentDetail;

	@Column(name = "AGENT_ACK_NO")
	private String agentAckNo;

	@Column(name = "SUBMISSION_DATE")
	private Calendar submissionDate;

	@NotBlank
	@Column(name = "AGENT_STATE_CODE")
	private String stateCode;
	@NotBlank
	@Column(name = "AGENT_STATE_NAME")
	private String stateName;

	@Column(name = "PR_PNAME", length = 25)
	private String processName;

	@Column(name = "WFOID", length = 50)
	private String wfoId;
	@Column(name = "FIN_TEAM", length = 25)
	private String finTeam;
	@Column(name = "LG_TEAM", length = 25)
	private String legalTeam;
	@Column(name = "TPSR_TEAM", length = 25)
	private String tpSrTeam;
	@Column(name = "PR_FIN_STATE", length = 25)
	private String finState;
	@Column(name = "PR_TP_STATE", length = 25)
	private String tpState;
	@Column(name = "PR_LG_STATE", length = 25)
	private String legalState;

	@Column(name = "CREATED_BY", length = 100)
	private String createdByName;
	@Column(name = "PR_LAST_UPDATE_BY")
	private Long lastUpdatedBy;
	@Column(name = "PR_LAST_UPDATE_BY_NAME", length = 100)
	private String lastUpdatedByName;
	@Column(name = "PR_TEAM", length = 25)
	private String tpTeam;

	@Column(name = "CREATED_BY_ID")
	private Long createdBy;

	@Column(name = "PR_APPROVED_ON")
	private Calendar approvedOn;

	@Column(name = "PR_APPROVED_By")
	private String approvedBy;

	@Override
	public Long getId() {

		return this.agentId;
	}

	@Override
	public Calendar getLastUpdatedOn() {
		// TODO Auto-generated method stub
		return this.moddifiedOn;
	}

	@Override
	public void setLastUpdatedOn(Calendar cal) {
		this.moddifiedOn = cal;

	}

	public AgentPaymentDetailModel getPaymentDetailsModel() {
		return paymentDetailsModel;
	}

	public void setPaymentDetailsModel(AgentPaymentDetailModel paymentDetailsModel) {
		this.paymentDetailsModel = paymentDetailsModel;
	}

	public AgentDetailModel getAgentDetail() {
		return agentDetail;
	}

	public void setAgentDetail(AgentDetailModel agentDetail) {
		this.agentDetail = agentDetail;
	}

	public List<EnquiryModel> getEnqiryList() {
		return enqiryList;
	}

	public void setEnqiryList(List<EnquiryModel> enqiryList) {
		this.enqiryList = enqiryList;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentFirstName() {
		return agentFirstName;
	}

	public void setAgentFirstName(String agentFirstName) {
		this.agentFirstName = agentFirstName;
	}

	public String getAgentLName() {
		return agentLName;
	}

	public void setAgentLName(String agentLName) {
		this.agentLName = agentLName;
	}

	public String getAgentMName() {
		return agentMName;
	}

	public void setAgentMName(String agentMName) {
		this.agentMName = agentMName;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getCompanyRegistrationNumber() {
		return companyRegistrationNumber;
	}

	public void setCompanyRegistrationNumber(String companyRegistrationNumber) {
		this.companyRegistrationNumber = companyRegistrationNumber;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Calendar getDateOfIncorporation() {
		return dateOfIncorporation;
	}

	public void setDateOfIncorporation(Calendar dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Calendar getModdifiedOn() {
		return moddifiedOn;
	}

	public void setModdifiedOn(Calendar moddifiedOn) {
		this.moddifiedOn = moddifiedOn;
	}

	public Calendar getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Calendar submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public List<AgentAssosiateModel> getAssosiateList() {
		return assosiateList;
	}

	public void setAssosiateList(List<AgentAssosiateModel> assosiateList) {
		this.assosiateList = assosiateList;
	}

	public List<AgentAuthorizedSignatoryModel> getAuthorizedSignatoryList() {
		return authorizedSignatoryList;
	}

	public void setAuthorizedSignatoryList(List<AgentAuthorizedSignatoryModel> authorizedSignatoryList) {
		this.authorizedSignatoryList = authorizedSignatoryList;
	}

	public List<AgentDocumentModel> getDocument() {
		return document;
	}

	public void setDocument(List<AgentDocumentModel> document) {
		this.document = document;
	}

	public String getAgentAckNo() {
		return agentAckNo;
	}

	public void setAgentAckNo(String agentAckNo) {
		this.agentAckNo = agentAckNo;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getWfoId() {
		return wfoId;
	}

	public void setWfoId(String wfoId) {
		this.wfoId = wfoId;
	}

	public String getFinTeam() {
		return finTeam;
	}

	public void setFinTeam(String finTeam) {
		this.finTeam = finTeam;
	}

	public String getLegalTeam() {
		return legalTeam;
	}

	public void setLegalTeam(String legalTeam) {
		this.legalTeam = legalTeam;
	}

	public String getTpSrTeam() {
		return tpSrTeam;
	}

	public void setTpSrTeam(String tpSrTeam) {
		this.tpSrTeam = tpSrTeam;
	}

	public String getFinState() {
		return finState;
	}

	public void setFinState(String finState) {
		this.finState = finState;
	}

	public String getTpState() {
		return tpState;
	}

	public void setTpState(String tpState) {
		this.tpState = tpState;
	}

	public String getLegalState() {
		return legalState;
	}

	public void setLegalState(String legalState) {
		this.legalState = legalState;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getLastUpdatedByName() {
		return lastUpdatedByName;
	}

	public void setLastUpdatedByName(String lastUpdatedByName) {
		this.lastUpdatedByName = lastUpdatedByName;
	}

	public String getTpTeam() {
		return tpTeam;
	}

	public void setTpTeam(String tpTeam) {
		this.tpTeam = tpTeam;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Calendar getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Calendar approvedOn) {
		this.approvedOn = approvedOn;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
