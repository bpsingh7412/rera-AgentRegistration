package in.gov.wf.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ActivityLog")
@Table(name = "TT_ACTIVITY_LOG")
public class ActivityLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "WFOID", length = 50)
	private String wfoId;
	@Column(name = "STATE_CODE", length = 30)
	private String stateCode;
	@Column(name = "STATE_NAME", length = 50)
	private String stateName;
	@Column(name = "CH_STATE_CODE", length = 30)
	private String chStateCode;
	@Column(name = "DEPT_NAME", length = 30)
	private String deptName;
	@Column(name = "TEAM", length = 30)
	private String team;
	@Column(name = "OUTCOME", length = 30)
	private String outcome;
	@Column(name = "NEXT_CH_STATE_CODE", length = 30)
	private String nextChStateCode;
	@Column(name = "NEXT_STATE_CODE", length = 30)
	private String nextStateCode;
	@Column(name = "NEXT_STATE_NAME", length = 50)
	private String nextStateName;
	@Column(name = "ACTIVITY_ON")
	private Calendar activityOn;
	@Column(name = "ACTIVITY_BY")
	private Long activityBy;
	@Column(name = "ACTIVITY_BY_NAME", length = 80)
	private String activityByName;
	@Column(name = "REMARKS", length = 500)
	private String remarks;
	@Column(name = "REASON", length = 50)
	private String reason;

	public String getNextChStateCode() {
		return nextChStateCode;
	}

	public void setNextChStateCode(String nextChStateCode) {
		this.nextChStateCode = nextChStateCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWfoId() {
		return wfoId;
	}

	public void setWfoId(String wfoId) {
		this.wfoId = wfoId;
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

	public String getChStateCode() {
		return chStateCode;
	}

	public void setChStateCode(String chStateCode) {
		this.chStateCode = chStateCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getNextStateCode() {
		return nextStateCode;
	}

	public void setNextStateCode(String nextStateCode) {
		this.nextStateCode = nextStateCode;
	}

	public String getNextStateName() {
		return nextStateName;
	}

	public void setNextStateName(String nextStateName) {
		this.nextStateName = nextStateName;
	}

	public Calendar getActivityOn() {
		return activityOn;
	}

	public void setActivityOn(Calendar activityOn) {
		this.activityOn = activityOn;
	}

	public Long getActivityBy() {
		return activityBy;
	}

	public void setActivityBy(Long activityBy) {
		this.activityBy = activityBy;
	}

	public String getActivityByName() {
		return activityByName;
	}

	public void setActivityByName(String activityByName) {
		this.activityByName = activityByName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
