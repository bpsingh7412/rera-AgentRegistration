package in.gov.agentregistration.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;


public class AgentWorkStatusDto {

	private Long workStatusId;

	private Long yearlyId;

	private Date startDate;
	
	private Date endDate;

	private List<SaleUnitsDetails> unitsList;

	private String status;

	private Long projectId;

	private String projectName;

	private String projectRegNo;

	private Calendar createdOn;

	private Long createdBy;
	
	private String promoterStatus;
	
	private String agentName;
	
	private String agentType;

	private String companyRegistrationNumber;
	
	private Calendar dateOfIncorporation;
	
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

	public List<SaleUnitsDetails> getUnitsList() {
		return unitsList;
	}

	public void setUnitsList(List<SaleUnitsDetails> unitsList) {
		this.unitsList = unitsList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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

	public String getPromoterStatus() {
		return promoterStatus;
	}

	public void setPromoterStatus(String promoterStatus) {
		this.promoterStatus = promoterStatus;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
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

	public Calendar getDateOfIncorporation() {
		return dateOfIncorporation;
	}

	public void setDateOfIncorporation(Calendar dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
	}

	
}
