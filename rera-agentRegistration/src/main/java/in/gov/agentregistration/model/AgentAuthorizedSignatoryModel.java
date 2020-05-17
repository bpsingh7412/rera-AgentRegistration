package in.gov.agentregistration.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity(name = "AgentAuthorizedSignatoryModel")
@Table(name = "TL_AGENT_AUTHORIZED_SIGNATORY_REG")
public class AgentAuthorizedSignatoryModel implements Serializable {
	private static final long serialVersionUID = 1463124430214748765L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAS_ID")
	private Long id;

	@Column(name = "AGENT_AUTHSIGN_FK")
	private Long agentId;

	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 * 
	 * @JoinTable(name = "PROMOTER_AUTH_ENQ_MAPPING") private List<EnquiryModel>
	 * enqiryList;
	 */

	@Column(name = "PAS_FNAME")
	private String authsignFirstName;
	@Column(name = "PAS_MNAME")
	private String authsignMiddleName;
	@Column(name = "PAS_LNAME")
	private String authsignLastName;

	@Column(name = "PAS_PAN")
	private String authsignPan;
	@Column(name = "PAS_ADDRESS")
	private String authsignAddress;

	@Column(name = "AUTHSIGN_ADDRESS2")
	private String authsignAddress2;

	@Column(name = "PAS_TALUKA_CODE")
	private String authsignTalukaCode;

	@Column(name = "PAS_TALUKA_NAME")
	private String authsignTalukaName;

	@Column(name = "PAS_DISTRICT_CODE")
	private String authsignDistrictCode;

	@Column(name = "PAS_DISTRICT_NAME")
	private String authsignDistrictName;

	@Column(name = "PAS_STATE_CODE")
	private String authsignStateCode;
	@Column(name = "PAS_STATE_NAME")
	private String authsignStateName;
	@Column(name = "PAS_PINCODE")
	private String authsignPinCode;

	@Column(name = "PAS_MOBILE")
	private String authsignMobileNumber;

	@Email
	@Column(name = "PAS_EMAILID")
	private String authsignEmailId;

	@Column(name = "PAS_ADHARNO")
	private String authsignADHARNumber;

	// document
	@Column(name = "PAS_PANID")
	private Long authsignPANColorPDId;

	@Column(name = "PAS_PANUID")
	private String authsignPanColorPDUId;

	@Column(name = "PAS_PHOTOID")
	private Long authsignPhotId;

	@Column(name = "PAS_PHOTOUID")
	private String authsignPhotUId;

	@Column(name = "PAS_ADHAR_DOCID")
	private Long authsignAdharDoId;

	@Column(name = "PAS_ADHAR_DOCUID")
	private String authsignAdharDoUId;

	@Column(name = "PAS_CERTIFICATE_UID")
	private String authsignCerificatUId;

	@Column(name = "PAS_CERTIFICATE_ID")
	private Long authsignCerificatId;

	@Column(name = "STATUS")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getAuthsignFirstName() {
		return authsignFirstName;
	}

	public void setAuthsignFirstName(String authsignFirstName) {
		this.authsignFirstName = authsignFirstName;
	}

	public String getAuthsignMiddleName() {
		return authsignMiddleName;
	}

	public void setAuthsignMiddleName(String authsignMiddleName) {
		this.authsignMiddleName = authsignMiddleName;
	}

	public String getAuthsignLastName() {
		return authsignLastName;
	}

	public void setAuthsignLastName(String authsignLastName) {
		this.authsignLastName = authsignLastName;
	}

	public String getAuthsignPan() {
		return authsignPan;
	}

	public void setAuthsignPan(String authsignPan) {
		this.authsignPan = authsignPan;
	}

	public String getAuthsignAddress() {
		return authsignAddress;
	}

	public void setAuthsignAddress(String authsignAddress) {
		this.authsignAddress = authsignAddress;
	}

	public String getAuthsignTalukaCode() {
		return authsignTalukaCode;
	}

	public void setAuthsignTalukaCode(String authsignTalukaCode) {
		this.authsignTalukaCode = authsignTalukaCode;
	}

	public String getAuthsignTalukaName() {
		return authsignTalukaName;
	}

	public void setAuthsignTalukaName(String authsignTalukaName) {
		this.authsignTalukaName = authsignTalukaName;
	}

	public String getAuthsignDistrictCode() {
		return authsignDistrictCode;
	}

	public void setAuthsignDistrictCode(String authsignDistrictCode) {
		this.authsignDistrictCode = authsignDistrictCode;
	}

	public String getAuthsignDistrictName() {
		return authsignDistrictName;
	}

	public void setAuthsignDistrictName(String authsignDistrictName) {
		this.authsignDistrictName = authsignDistrictName;
	}

	public String getAuthsignStateCode() {
		return authsignStateCode;
	}

	public void setAuthsignStateCode(String authsignStateCode) {
		this.authsignStateCode = authsignStateCode;
	}

	public String getAuthsignStateName() {
		return authsignStateName;
	}

	public void setAuthsignStateName(String authsignStateName) {
		this.authsignStateName = authsignStateName;
	}

	public String getAuthsignPinCode() {
		return authsignPinCode;
	}

	public void setAuthsignPinCode(String authsignPinCode) {
		this.authsignPinCode = authsignPinCode;
	}

	public String getAuthsignMobileNumber() {
		return authsignMobileNumber;
	}

	public void setAuthsignMobileNumber(String authsignMobileNumber) {
		this.authsignMobileNumber = authsignMobileNumber;
	}

	public String getAuthsignEmailId() {
		return authsignEmailId;
	}

	public void setAuthsignEmailId(String authsignEmailId) {
		this.authsignEmailId = authsignEmailId;
	}

	public String getAuthsignADHARNumber() {
		return authsignADHARNumber;
	}

	public void setAuthsignADHARNumber(String authsignADHARNumber) {
		this.authsignADHARNumber = authsignADHARNumber;
	}

	public Long getAuthsignPANColorPDId() {
		return authsignPANColorPDId;
	}

	public void setAuthsignPANColorPDId(Long authsignPANColorPDId) {
		this.authsignPANColorPDId = authsignPANColorPDId;
	}

	public String getAuthsignPanColorPDUId() {
		return authsignPanColorPDUId;
	}

	public void setAuthsignPanColorPDUId(String authsignPanColorPDUId) {
		this.authsignPanColorPDUId = authsignPanColorPDUId;
	}

	public Long getAuthsignPhotId() {
		return authsignPhotId;
	}

	public void setAuthsignPhotId(Long authsignPhotId) {
		this.authsignPhotId = authsignPhotId;
	}

	public String getAuthsignPhotUId() {
		return authsignPhotUId;
	}

	public void setAuthsignPhotUId(String authsignPhotUId) {
		this.authsignPhotUId = authsignPhotUId;
	}

	public Long getAuthsignAdharDoId() {
		return authsignAdharDoId;
	}

	public void setAuthsignAdharDoId(Long authsignAdharDoId) {
		this.authsignAdharDoId = authsignAdharDoId;
	}

	public String getAuthsignAdharDoUId() {
		return authsignAdharDoUId;
	}

	public void setAuthsignAdharDoUId(String authsignAdharDoUId) {
		this.authsignAdharDoUId = authsignAdharDoUId;
	}

	public String getAuthsignCerificatUId() {
		return authsignCerificatUId;
	}

	public void setAuthsignCerificatUId(String authsignCerificatUId) {
		this.authsignCerificatUId = authsignCerificatUId;
	}

	public Long getAuthsignCerificatId() {
		return authsignCerificatId;
	}

	public void setAuthsignCerificatId(Long authsignCerificatId) {
		this.authsignCerificatId = authsignCerificatId;
	}

	public String getAuthsignAddress2() {
		return authsignAddress2;
	}

	public void setAuthsignAddress2(String authsignAddress2) {
		this.authsignAddress2 = authsignAddress2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
