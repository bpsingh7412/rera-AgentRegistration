package in.gov.wf.model;

import java.util.List;

import in.gov.wf.tasklist.AgentSummaryModel;

public class TaskListResponse {

	private int status;
	private String message;
	private List<AgentSummaryModel> records;
	private int noOfRecords;

	private TaskListResponse(int status, String message, List<AgentSummaryModel> lst) {
		this.status = status;
		this.message = message;
		this.records = lst;
		if (this.records != null) {
			this.noOfRecords = this.records.size();
		} else {
			this.noOfRecords = 0;
		}
	}

	private TaskListResponse(int status, String message) {
		this.status = status;
		this.message = message;
		this.noOfRecords = 0;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<AgentSummaryModel> getRecords() {
		return records;
	}

	public void setRecords(List<AgentSummaryModel> records) {
		this.records = records;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public static TaskListResponse ok(List<AgentSummaryModel> listObject) {

		return new TaskListResponse(200, "Success", listObject);
	}

	public static TaskListResponse ok(String message) {
		return new TaskListResponse(200, message);
	}

	public static TaskListResponse error(String message) {
		return new TaskListResponse(500, message);
	}

	public static TaskListResponse noRecords() {
		return new TaskListResponse(404, "No Records Found!");
	}
}
