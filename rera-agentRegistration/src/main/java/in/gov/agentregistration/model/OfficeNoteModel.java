package in.gov.agentregistration.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "OfficeNoteModel")
@Table(name = "TL_OFFICE_NOTE")
public class OfficeNoteModel implements Serializable {

	private static final long serialVersionUID = 14323535435L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRJ_NOTE_ID")
	private Long noteId;
	@Column(name = "PRJ_ID")
	private Long projectId;
	@Column(name = "TO_DEPT", length = 45)
	private String toDepartment;
	@Column(name = "FROM_DEPT", length = 45)
	private String fromDepartment;
	@Column(name = "NOTE_CONTENT", length = 3000)
	private String noteContent;
	@Column(name = "POSTED_ON")
	private Calendar postedOn;
	@Column(name = "POSTED_BY", length = 70)
	private String postedBy;
	@Column(name = "NOTE_STATUS", length = 25)
	private String status;

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getToDepartment() {
		return toDepartment;
	}

	public void setToDepartment(String toDepartment) {
		this.toDepartment = toDepartment;
	}

	public String getFromDepartment() {
		return fromDepartment;
	}

	public void setFromDepartment(String fromDepartment) {
		this.fromDepartment = fromDepartment;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public Calendar getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Calendar postedOn) {
		this.postedOn = postedOn;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
