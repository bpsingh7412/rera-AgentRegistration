package in.gov.wf.model;

import java.util.List;
import java.util.Map;

public class FormRequest {

	private String wfoId;
	private Map<String, Object> formdata;
	private String outcome;
	private String reason;
	private String remarks;
	private List<Map<String, Object>> datalist;

	public Map<String, Object> getFormdata() {
		return formdata;
	}

	public void setFormdata(Map<String, Object> formdata) {
		this.formdata = formdata;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<Map<String, Object>> getDatalist() {
		return datalist;
	}

	public void setDatalist(List<Map<String, Object>> datalist) {
		this.datalist = datalist;
	}

	public String getWfoId() {
		return wfoId;
	}

	public void setWfoId(String wfoId) {
		this.wfoId = wfoId;
	}

}
