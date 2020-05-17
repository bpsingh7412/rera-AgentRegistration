package in.gov.agentregistration.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "AgentDocumentModel")
@Table(name = "TL_AGENT_DOC")
public class AgentDocumentModel implements Serializable {
	private static final long serialVersionUID = 1463124430214748765L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AGC_DOC_ID")
	private Long id;

	@Column(name = "AGENT_DOC_FK")
	private Long agentId;

	@Column(name = "REGISTRATION_CERTIFICATE_ID")
	private Long registrationCertificateId;

	@Column(name = "REGISTRATION_CERTIFICATE_UID")
	private String registrationCertificateUId;

	@Column(name = "FILE2_ID")
	private Long fileId;

	@Column(name = "FILE2_UID")
	private String fileUId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRegistrationCertificateId() {
		return registrationCertificateId;
	}

	public void setRegistrationCertificateId(Long registrationCertificateId) {
		this.registrationCertificateId = registrationCertificateId;
	}

	public String getRegistrationCertificateUId() {
		return registrationCertificateUId;
	}

	public void setRegistrationCertificateUId(String registrationCertificateUId) {
		this.registrationCertificateUId = registrationCertificateUId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileUId() {
		return fileUId;
	}

	public void setFileUId(String fileUId) {
		this.fileUId = fileUId;
	}

}
