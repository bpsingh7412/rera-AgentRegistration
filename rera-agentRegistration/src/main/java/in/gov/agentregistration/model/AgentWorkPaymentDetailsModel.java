package in.gov.agentregistration.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "AgentWorkPaymentDetailsModel")
@Table(name = "TL_AGENT_WORK_PAYMENT_DETAILS")
public class AgentWorkPaymentDetailsModel implements Serializable {

	private static final long serialVersionUID = -8616903762245074717L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PPY_ID")
	private Long paymentId;

	@Column(name = "PAYMENT_FK")
	private Long yStatusId;

	@Column(name = "PPY_PAYMENT_AMOUNT", columnDefinition = "Double default '0'")
	private Double amount;

	@Column(name = "PPY_PENALTY_AMOUNT", columnDefinition = "Double default '0'")
	private Double penaltyAmount;

	@Column(name = "PPY_TOTAL_AMOUNT", columnDefinition = "Double default '0'")
	private Double totalProjectAmount;

	@Column(name = "PPY_SBI_REFNO")
	private String sbiReferanceNo;

	@Column(name = "TRANSACTION_NO")
	private String transactionNo;

	@Column(name = "TOKEN_ON")
	private String tokenNo;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATED_ON")
	private Calendar createdOn;

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getyStatusId() {
		return yStatusId;
	}

	public void setyStatusId(Long yStatusId) {
		this.yStatusId = yStatusId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPenaltyAmount() {
		return penaltyAmount;
	}

	public void setPenaltyAmount(Double penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}

	public Double getTotalProjectAmount() {
		return totalProjectAmount;
	}

	public void setTotalProjectAmount(Double totalProjectAmount) {
		this.totalProjectAmount = totalProjectAmount;
	}

	public String getSbiReferanceNo() {
		return sbiReferanceNo;
	}

	public void setSbiReferanceNo(String sbiReferanceNo) {
		this.sbiReferanceNo = sbiReferanceNo;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

}
