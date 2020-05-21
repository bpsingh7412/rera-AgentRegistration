package in.gov.agentregistration.model;

import java.io.Serializable;

public class ResponseModel implements Serializable {

	private static final long serialVersionUID = 1463124437645744569L;

	private String status;
	private String message;
	private Long id;
	private Long agenId;
	private Object data;

	private Integer startWith;
	private Integer dataSize;
	private String sortBy;
	private Integer totalPages;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAgenId() {
		return agenId;
	}

	public void setAgenId(Long agenId) {
		this.agenId = agenId;
	}

	public Integer getStartWith() {
		return startWith;
	}

	public void setStartWith(Integer startWith) {
		this.startWith = startWith;
	}

	public Integer getDataSize() {
		return dataSize;
	}

	public void setDataSize(Integer dataSize) {
		this.dataSize = dataSize;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

}
