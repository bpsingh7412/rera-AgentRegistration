package in.gov.agentregistration.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "SaleUnitsDetails")
@Table(name = "TL_SALE_UNITS_DETAILS")
public class SaleUnitsDetails implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 18978967656L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FLAT_NO")
	private Long flatNo;

	@Column(name = "WORK_STATUS_FK")
	private Long workStatusId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", updatable = false)
	private Calendar createdOn;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITID_ON")
	private Calendar submitedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(Long flatNo) {
		this.flatNo = flatNo;
	}

	public Long getWorkStatusId() {
		return workStatusId;
	}

	public void setWorkStatusId(Long workStatusId) {
		this.workStatusId = workStatusId;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Calendar getSubmitedOn() {
		return submitedOn;
	}

	public void setSubmitedOn(Calendar submitedOn) {
		this.submitedOn = submitedOn;
	}
	
	
}
