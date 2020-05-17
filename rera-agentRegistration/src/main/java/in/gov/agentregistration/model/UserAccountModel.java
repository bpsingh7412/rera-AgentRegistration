package in.gov.agentregistration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

@Component
public class UserAccountModel implements Serializable {
	private static final long serialVersionUID = 1463124430214748765L;
	private Long id;
	private String loginId;
	private String userName;
	private String userCategory;
	private String email;
	private String mobile;
	private String userType;
	private Long profileId;
	private String userStatus;
	private String image;
	private String profExperience;

	public String getProfExperience() {
		return profExperience;
	}

	public void setProfExperience(String profExperience) {
		this.profExperience = profExperience;
	}

	public UserAccountModel() {
	}

	public UserAccountModel(Long id, String loginId, String userName, String userCategory, String email, String mobile,
			String userType, String image) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.userName = userName;
		this.userCategory = userCategory;
		this.email = email;
		this.mobile = mobile;
		this.userType = userType;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
