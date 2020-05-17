package in.gov.agentregistration.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "DefaulterStatusModel")
@Table(name = "TL_AGENT_HY_DEFAULTER_STATUS")
public class DefaulterStatusModel implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 8979818798L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEFAULT_ID")
	private Long defaulterId;

	@Column(name = "YEARLY_STATUS_DEF_FK")
	private Long yStatus;

	@Column(name = "DEFAULT_NO")
	private String defaulterNo;

	@Column(name = "DEFAULTED_DATE")
	private Date defaulterDate;

	@Column(name = "RE_OPEN_DATE")
	private Date reOpenDate;

	@Column(name = "EXTEND_DATE")
	private Date extendDate;

	@Column(name = "PENALITY_AMT")
	private Double reOpenPenalityAmt;

	@Column(name = "REOPEN_BY")
	private Long reOpenBy;

	@Column(name = "REMARKS")
	private String remarks;

	public Long getDefaulterId() {
		return defaulterId;
	}

	public void setDefaulterId(Long defaulterId) {
		this.defaulterId = defaulterId;
	}

	public String getDefaulterNo() {
		return defaulterNo;
	}

	public void setDefaulterNo(String defaulterNo) {
		this.defaulterNo = defaulterNo;
	}

	public Date getDefaulterDate() {
		return defaulterDate;
	}

	public void setDefaulterDate(Date defaulterDate) {
		this.defaulterDate = defaulterDate;
	}

	public Date getReOpenDate() {
		return reOpenDate;
	}

	public void setReOpenDate(Date reOpenDate) {
		this.reOpenDate = reOpenDate;
	}

	public Date getExtendDate() {
		return extendDate;
	}

	public void setExtendDate(Date extendDate) {
		this.extendDate = extendDate;
	}

	public Double getReOpenPenalityAmt() {
		return reOpenPenalityAmt;
	}

	public void setReOpenPenalityAmt(Double reOpenPenalityAmt) {
		this.reOpenPenalityAmt = reOpenPenalityAmt;
	}

	public Long getReOpenBy() {
		return reOpenBy;
	}

	public void setReOpenBy(Long reOpenBy) {
		this.reOpenBy = reOpenBy;
	}

	public Long getyStatus() {
		return yStatus;
	}

	public void setyStatus(Long yStatus) {
		this.yStatus = yStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
