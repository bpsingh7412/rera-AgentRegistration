package in.gov.agentregistration.model;

import java.util.Calendar;
import java.util.Date;

public class YearlyStatusDto {

	private Long agentId;
	private String agentRegNo;
	private String agentName;
	private String regType;
	private String yearName;
	private String yearlyStatus;
	private String promoterStatus;
	private String isDefaulter;
	private String defaulterId;
	private Date startDate;
	private Date endDate;
	private Calendar submissionDate;
	private Integer startWith;
	private Integer dataSize;
	private String sortBy;
	
	
public	YearlyStatusDto(){}

public Long getAgentId() {
	return agentId;
}

public void setAgentId(Long agentId) {
	this.agentId = agentId;
}

public String getAgentRegNo() {
	return agentRegNo;
}

public void setAgentRegNo(String agentRegNo) {
	this.agentRegNo = agentRegNo;
}

public String getAgentName() {
	return agentName;
}

public void setAgentName(String agentName) {
	this.agentName = agentName;
}

public String getRegType() {
	return regType;
}

public void setRegType(String regType) {
	this.regType = regType;
}

public String getYearName() {
	return yearName;
}

public void setYearName(String yearName) {
	this.yearName = yearName;
}

public String getYearlyStatus() {
	return yearlyStatus;
}

public void setYearlyStatus(String yearlyStatus) {
	this.yearlyStatus = yearlyStatus;
}

public String getPromoterStatus() {
	return promoterStatus;
}

public void setPromoterStatus(String promoterStatus) {
	this.promoterStatus = promoterStatus;
}

public String getIsDefaulter() {
	return isDefaulter;
}

public void setIsDefaulter(String isDefaulter) {
	this.isDefaulter = isDefaulter;
}

public Integer getStartWith() {
	return startWith;
}

public void setStartWith(Integer startWith) {
	this.startWith = startWith;
}

public Integer getDataSize() {
	return dataSize;
}

public void setDataSize(Integer dataSize) {
	this.dataSize = dataSize;
}

public String getSortBy() {
	return sortBy;
}

public void setSortBy(String sortBy) {
	this.sortBy = sortBy;
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

public String getDefaulterId() {
	return defaulterId;
}

public void setDefaulterId(String defaulterId) {
	this.defaulterId = defaulterId;
}

public Calendar getSubmissionDate() {
	return submissionDate;
}

public void setSubmissionDate(Calendar submissionDate) {
	this.submissionDate = submissionDate;
}

/*
 * public YearlyStatusDto(YearlyStatusModel ff){
 * this.financialYear=ff.getFinancialYear(); this.formFiveId=ff.getFormFiveId();
 * this.projectFormFiveFk=ff.getProjectFormFiveFk();
 * this.promoterStatus=ff.getPromoterStatus();
 * this.formFiveName=ff.getFormFiveName();
 * this.formFiveYear=ff.getFormFiveYear(); this.status=ff.getStatus();
 * this.finYearId=ff.getFinYearId(); }
 */
	

	


}
