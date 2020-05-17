package in.gov.agentregistration.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "EnquiryModel")
@Table(name = "TL_ENQUIRY_REG")
public class EnquiryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENQ_ID")
	private Long enqId;

	@Column(name = "AUTHORITY_REMARK", length = 2000)
	private String AuthRemark;

	@Column(name = "USER_REMARK", length = 2000)
	private String userRemark;

	@Column(name = "STATUS")
	private String status;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVITY_START_DATE", updatable = false)
	private Calendar activityStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVITY_END_DATE")
	private Calendar activityEndDate;

	@Column(name = "AUTHORITY_ID")
	private Long authorityId;

	@Column(name = "AUTHORITY_TYPE")
	private String authorityType;

	public String getAuthRemark() {
		return AuthRemark;
	}

	public void setAuthRemark(String authRemark) {
		AuthRemark = authRemark;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public Calendar getActivityStartDate() {
		return activityStartDate;
	}

	public void setActivityStartDate(Calendar activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	public Calendar getActivityEndDate() {
		return activityEndDate;
	}

	public void setActivityEndDate(Calendar activityEndDate) {
		this.activityEndDate = activityEndDate;
	}

	public Long getEnqId() {
		return enqId;
	}

	public void setEnqId(Long enqId) {
		this.enqId = enqId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
