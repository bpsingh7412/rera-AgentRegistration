package in.gov.wf.model;

import java.util.Map;

public class FormResponse {

	private String wfoId;
	private Map<String, Boolean> sections;
	private Map<String, Map<String, Object>> actions;
	private WFEntity entity;
	private Integer status;
	private String message;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getWfoId() {
		return wfoId;
	}

	public void setWfoId(String wfoId) {
		this.wfoId = wfoId;
	}

	public Map<String, Boolean> getSections() {
		return sections;
	}

	public void setSections(Map<String, Boolean> sections) {
		this.sections = sections;
	}

	public Map<String, Map<String, Object>> getActions() {
		return actions;
	}

	public void setActions(Map<String, Map<String, Object>> actions) {
		this.actions = actions;
	}

	public WFEntity getEntity() {
		return entity;
	}

	public void setEntity(WFEntity entity) {
		this.entity = entity;
	}

	public FormResponse() {
	}

	public FormResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public FormResponse(int status, String message, WFEntity ent) {
		this.status = status;
		this.message = message;
		this.entity = ent;
	}

	public static FormResponse ok() {
		return new FormResponse(200, "Success");
	}

	public static FormResponse ok(String message) {
		return new FormResponse(200, message);
	}

	public static FormResponse ok(String message, WFEntity ent) {
		return new FormResponse(200, message, ent);
	}

	public static FormResponse error(String message) {
		return new FormResponse(500, message);
	}

	public static FormResponse error(int staus, String message) {
		return new FormResponse(staus, message);
	}
}
