package in.gov.agentregistration.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import in.gov.wf.model.WFEntity;

@Entity(name = "AgentDetailModel")
@Table(name = "TT_AGENT_DETAIL_REG")
public class AgentDetailModel implements Serializable {
	private static final long serialVersionUID = 1463124430214748765L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DETAIL_ID")
	private Long detailId;

	@Column(name = "AGENT_DETAIL_FK")
	private Long agentId;

	@Column(name = "TALUKA_NAME")
	private String talukaName;

	@Column(name = "OTHER_TALUKA_NAME")
	private String otherTalukaName;

	@Column(name = "AGENT_FATHER_NAME")
	private String fatherName;

	@Column(name = "AGENT_OCCUPATION")
	private String occupation;

	@NotBlank(message = "Address is required.")
	@Column(name = "AGENT_ADDRESS")
	private String address;

	@Column(name = "AGENT_ADDRESS2")
	private String address2;

	@Column(name = "REGISTRATION_CERTIFICATE_ID")
	private Long registrationCertificateId;

	@Column(name = "REGISTRATION_CERTIFICATE_UID")
	private String registrationCertificateUId;

	@Column(name = "AUTH_SIGN_CERIFICATE_ID")
	private Long authsignCerificateId;

	@Column(name = "AUTH_SIGN_CERIFICATE_UID")
	private String authsignCerificateUId;

	@Column(name = "UPLOAD_AADHARCARD_COLOR_PDF")
	private String UploadAadharCardcolorPDF;

	@Column(name = "AUTH_SIGN_PANCOLOR_PDF_ID")
	private Long authsignPANColorPDFId;

	@Column(name = "AUTH_SIGN_PANCOLOR_PDF_UID")
	private String authsignPANColorPDFUId;

	@Column(name = "AGENT_ADHAAR_No")
	private String adharNo;

	@Column(name = "PHOTOGRAPH")
	private String Photograph;

	@Column(name = "AGENT_FAX_NO")
	private String faxNo;

	@Column(name = "AGENT_LAND_LINE_NO_1")
	private String landLine1;

	@Column(name = "AGENT_LAND_LINE_NO_2")
	private String landLine2;

	@Column(name = "AGENT_OTHER")
	private String other;

	@NotBlank(message = "Pan Number is required.")
	@Size(max = 10, message = "Pan card should have 10 characters")
	@Column(name = "AGENT_PAN_NO")
	private String panNo;

	@NotBlank(message = "Pan Number is required.")
	@Size(max = 10, message = "Mobile number should have 10 digit")
	@Column(name = "AGENT_MOBILE_NO")
	private String mobileNo;
	@Column(name = "AGENT_PIN_CODE")
	private String pinCode;
	@Column(name = "AGENT_TELEPHONE_NO")
	private String telephoneNo;

	@NotBlank
	@Column(name = "AGENT_DISTRICT_CODE")
	private String districtCode;
	@NotBlank
	@Column(name = "AGENT_DISTRICT_NAME")
	private String districtName;

	@Column(name = "AGENT_COM_REG_CERTIFICATE_DOC_ID")
	private Long companyRegCertificateDocId;

	@Column(name = "AGENT_COM_REG_CERTIFICATE_DOC_UID")
	private String companyRegCertificateDocUId;

	@Column(name = "AGENT_IND_PHOTO_DOC")
	private Long individualPhotoDocId;

	@Column(name = "AGENT_IND_PHOTO_DOC_UID")
	private String individualPhotoDocUId;

	@Column(name = "AGENT_PAN_DOC_ID")
	private Long panDocId;

	@Column(name = "AGENT_PAN_DOC_UID")
	private String panDocUId;

	@Column(name = "PAS_PHOTO_ID")
	private Long authsignPhotoId;

	@Column(name = "PAS_PHOTOU_ID")
	private String authsignPhotoUId;

	@Column(name = "PAS_PAN_ID")
	private Long authsignPANColorPDId;

	@Column(name = "PAS_PAN_UID")
	private String authsignPanColorPDUId;

	@Column(name = "PAS_PHOTID")
	private Long authsignPhotId;

	@Column(name = "PAS_PHOTUID")
	private String authsignPhotUId;

	@Column(name = "PAS_ADHAR_DCID")
	private Long authsignAdharDoId;

	@Column(name = "PAS_ADHAR_DOUID")
	private String authsignAdharDoUId;

	@Column(name = "PAS_CERTIFICAE_UID")
	private String authsignCerificatUId;

	@Column(name = "PAS_CERTIFICAE_ID")
	private Long authsignCerificatId;
	/* Additional Field Added */

	@Column(name = "ENT_DEV_GROUPNAME")
	private String entities_developerGroupName;

	@Column(name = "ENT_DEV_GROUP_CERT")
	private Long entities_developerGroupCertificate;

	@Column(name = "ENTS_DEV_GRP_CERT_UID")
	private String entities_developerGroupCertificateUID;

	@Column(name = "ENT_WEBSITEURL")
	private String entities_websiteUrl;

	@Column(name = "ENT_PRM_ENTERPRISE")
	private String entities_agentEnterprise;

	@Column(name = "ENT_AGENT_ADDRESS")
	private String entities_agent_Address;

	@Column(name = "ENT_STATENAME")
	private String entities_stateName;

	@Column(name = "ENT_STATECODE")
	private String entities_stateCode;

	@Column(name = "ENT_DISTRICTCODE")
	private String entities_districtCode;

	@Column(name = "ENT_DISTRICTNAME")
	private String entities_districtName;

	@Column(name = "ENT_TALUKANAME")
	private String entities_talukaName;

	@Column(name = "ENT_PINCODE")
	private String entities_pincode;

	@Column(name = "ENT_GROUPHISTORY")
	private String entities_groupHistory;

	@Column(name = "ENT_EXP_GROUP_ENTITY")
	private String entities_experienceGroupEntity;

	@Column(name = "ENT_EXP_STATE")
	private String entities_experienceInState;

	@Column(name = "ENT_EXP_OTHER_STATE")
	private String entities_experienceInOtherState;

	@Column(name = "ENT_TOTALPROJECTS")
	private String entities_totalProjects;

	@Column(name = "ENT_TOTAL_AREA_CONSTED")
	private String entities_totalAreaConstructed;

	@Column(name = "ENT_NO_PRJECTS_COMPED")
	private String entities_noOfProjectsCompleted;

	@Column(name = "ENTITIES_AREACONSTRUCTED")
	private String entities_areaConstructed;

	@Column(name = "ENTITIES_ONGOINGPROJECTS")
	private String entities_ongoingProjects;

	@Column(name = "ENTITIES_CONSTRUCTED")
	private String entities_proposedAreaConstructed;

	@Column(name = "ENTITIES_FACEBOOKLINK")
	private String entities_facebookLink;

	@Column(name = "ENTITIES_TWITTERHANDLE")
	private String entities_twitterHandle;

	@Column(name = "ENTITIES_YOUTUBELINK")
	private String entities_youTubeLink;

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getTalukaName() {
		return talukaName;
	}

	public void setTalukaName(String talukaName) {
		this.talukaName = talukaName;
	}

	public String getOtherTalukaName() {
		return otherTalukaName;
	}

	public void setOtherTalukaName(String otherTalukaName) {
		this.otherTalukaName = otherTalukaName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
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

	public Long getAuthsignCerificateId() {
		return authsignCerificateId;
	}

	public void setAuthsignCerificateId(Long authsignCerificateId) {
		this.authsignCerificateId = authsignCerificateId;
	}

	public String getAuthsignCerificateUId() {
		return authsignCerificateUId;
	}

	public void setAuthsignCerificateUId(String authsignCerificateUId) {
		this.authsignCerificateUId = authsignCerificateUId;
	}

	public String getUploadAadharCardcolorPDF() {
		return UploadAadharCardcolorPDF;
	}

	public void setUploadAadharCardcolorPDF(String uploadAadharCardcolorPDF) {
		UploadAadharCardcolorPDF = uploadAadharCardcolorPDF;
	}

	public Long getAuthsignPANColorPDFId() {
		return authsignPANColorPDFId;
	}

	public void setAuthsignPANColorPDFId(Long authsignPANColorPDFId) {
		this.authsignPANColorPDFId = authsignPANColorPDFId;
	}

	public String getAuthsignPANColorPDFUId() {
		return authsignPANColorPDFUId;
	}

	public void setAuthsignPANColorPDFUId(String authsignPANColorPDFUId) {
		this.authsignPANColorPDFUId = authsignPANColorPDFUId;
	}

	public String getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}

	public String getPhotograph() {
		return Photograph;
	}

	public void setPhotograph(String photograph) {
		Photograph = photograph;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getLandLine1() {
		return landLine1;
	}

	public void setLandLine1(String landLine1) {
		this.landLine1 = landLine1;
	}

	public String getLandLine2() {
		return landLine2;
	}

	public void setLandLine2(String landLine2) {
		this.landLine2 = landLine2;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Long getCompanyRegCertificateDocId() {
		return companyRegCertificateDocId;
	}

	public void setCompanyRegCertificateDocId(Long companyRegCertificateDocId) {
		this.companyRegCertificateDocId = companyRegCertificateDocId;
	}

	public String getCompanyRegCertificateDocUId() {
		return companyRegCertificateDocUId;
	}

	public void setCompanyRegCertificateDocUId(String companyRegCertificateDocUId) {
		this.companyRegCertificateDocUId = companyRegCertificateDocUId;
	}

	public Long getIndividualPhotoDocId() {
		return individualPhotoDocId;
	}

	public void setIndividualPhotoDocId(Long individualPhotoDocId) {
		this.individualPhotoDocId = individualPhotoDocId;
	}

	public String getIndividualPhotoDocUId() {
		return individualPhotoDocUId;
	}

	public void setIndividualPhotoDocUId(String individualPhotoDocUId) {
		this.individualPhotoDocUId = individualPhotoDocUId;
	}

	public Long getPanDocId() {
		return panDocId;
	}

	public void setPanDocId(Long panDocId) {
		this.panDocId = panDocId;
	}

	public String getPanDocUId() {
		return panDocUId;
	}

	public void setPanDocUId(String panDocUId) {
		this.panDocUId = panDocUId;
	}

	public Long getAuthsignPhotoId() {
		return authsignPhotoId;
	}

	public void setAuthsignPhotoId(Long authsignPhotoId) {
		this.authsignPhotoId = authsignPhotoId;
	}

	public String getAuthsignPhotoUId() {
		return authsignPhotoUId;
	}

	public void setAuthsignPhotoUId(String authsignPhotoUId) {
		this.authsignPhotoUId = authsignPhotoUId;
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

	public String getEntities_developerGroupName() {
		return entities_developerGroupName;
	}

	public void setEntities_developerGroupName(String entities_developerGroupName) {
		this.entities_developerGroupName = entities_developerGroupName;
	}

	public Long getEntities_developerGroupCertificate() {
		return entities_developerGroupCertificate;
	}

	public void setEntities_developerGroupCertificate(Long entities_developerGroupCertificate) {
		this.entities_developerGroupCertificate = entities_developerGroupCertificate;
	}

	public String getEntities_developerGroupCertificateUID() {
		return entities_developerGroupCertificateUID;
	}

	public void setEntities_developerGroupCertificateUID(String entities_developerGroupCertificateUID) {
		this.entities_developerGroupCertificateUID = entities_developerGroupCertificateUID;
	}

	public String getEntities_websiteUrl() {
		return entities_websiteUrl;
	}

	public void setEntities_websiteUrl(String entities_websiteUrl) {
		this.entities_websiteUrl = entities_websiteUrl;
	}

	public String getEntities_agentEnterprise() {
		return entities_agentEnterprise;
	}

	public void setEntities_agentEnterprise(String entities_agentEnterprise) {
		this.entities_agentEnterprise = entities_agentEnterprise;
	}

	public String getEntities_agent_Address() {
		return entities_agent_Address;
	}

	public void setEntities_agent_Address(String entities_agent_Address) {
		this.entities_agent_Address = entities_agent_Address;
	}

	public String getEntities_stateName() {
		return entities_stateName;
	}

	public void setEntities_stateName(String entities_stateName) {
		this.entities_stateName = entities_stateName;
	}

	public String getEntities_stateCode() {
		return entities_stateCode;
	}

	public void setEntities_stateCode(String entities_stateCode) {
		this.entities_stateCode = entities_stateCode;
	}

	public String getEntities_districtCode() {
		return entities_districtCode;
	}

	public void setEntities_districtCode(String entities_districtCode) {
		this.entities_districtCode = entities_districtCode;
	}

	public String getEntities_districtName() {
		return entities_districtName;
	}

	public void setEntities_districtName(String entities_districtName) {
		this.entities_districtName = entities_districtName;
	}

	public String getEntities_talukaName() {
		return entities_talukaName;
	}

	public void setEntities_talukaName(String entities_talukaName) {
		this.entities_talukaName = entities_talukaName;
	}

	public String getEntities_pincode() {
		return entities_pincode;
	}

	public void setEntities_pincode(String entities_pincode) {
		this.entities_pincode = entities_pincode;
	}

	public String getEntities_groupHistory() {
		return entities_groupHistory;
	}

	public void setEntities_groupHistory(String entities_groupHistory) {
		this.entities_groupHistory = entities_groupHistory;
	}

	public String getEntities_experienceGroupEntity() {
		return entities_experienceGroupEntity;
	}

	public void setEntities_experienceGroupEntity(String entities_experienceGroupEntity) {
		this.entities_experienceGroupEntity = entities_experienceGroupEntity;
	}

	public String getEntities_experienceInState() {
		return entities_experienceInState;
	}

	public void setEntities_experienceInState(String entities_experienceInState) {
		this.entities_experienceInState = entities_experienceInState;
	}

	public String getEntities_experienceInOtherState() {
		return entities_experienceInOtherState;
	}

	public void setEntities_experienceInOtherState(String entities_experienceInOtherState) {
		this.entities_experienceInOtherState = entities_experienceInOtherState;
	}

	public String getEntities_totalProjects() {
		return entities_totalProjects;
	}

	public void setEntities_totalProjects(String entities_totalProjects) {
		this.entities_totalProjects = entities_totalProjects;
	}

	public String getEntities_totalAreaConstructed() {
		return entities_totalAreaConstructed;
	}

	public void setEntities_totalAreaConstructed(String entities_totalAreaConstructed) {
		this.entities_totalAreaConstructed = entities_totalAreaConstructed;
	}

	public String getEntities_noOfProjectsCompleted() {
		return entities_noOfProjectsCompleted;
	}

	public void setEntities_noOfProjectsCompleted(String entities_noOfProjectsCompleted) {
		this.entities_noOfProjectsCompleted = entities_noOfProjectsCompleted;
	}

	public String getEntities_areaConstructed() {
		return entities_areaConstructed;
	}

	public void setEntities_areaConstructed(String entities_areaConstructed) {
		this.entities_areaConstructed = entities_areaConstructed;
	}

	public String getEntities_ongoingProjects() {
		return entities_ongoingProjects;
	}

	public void setEntities_ongoingProjects(String entities_ongoingProjects) {
		this.entities_ongoingProjects = entities_ongoingProjects;
	}

	public String getEntities_proposedAreaConstructed() {
		return entities_proposedAreaConstructed;
	}

	public void setEntities_proposedAreaConstructed(String entities_proposedAreaConstructed) {
		this.entities_proposedAreaConstructed = entities_proposedAreaConstructed;
	}

	public String getEntities_facebookLink() {
		return entities_facebookLink;
	}

	public void setEntities_facebookLink(String entities_facebookLink) {
		this.entities_facebookLink = entities_facebookLink;
	}

	public String getEntities_twitterHandle() {
		return entities_twitterHandle;
	}

	public void setEntities_twitterHandle(String entities_twitterHandle) {
		this.entities_twitterHandle = entities_twitterHandle;
	}

	public String getEntities_youTubeLink() {
		return entities_youTubeLink;
	}

	public void setEntities_youTubeLink(String entities_youTubeLink) {
		this.entities_youTubeLink = entities_youTubeLink;
	}

}
