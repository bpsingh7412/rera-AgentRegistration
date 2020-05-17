package in.gov.wf.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.gov.wf.util.ServiceResult;

public abstract class JavaService {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServiceResult result;

	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.result = new ServiceResult();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public ServiceResult getResult() {
		return result;
	}

	public void setResult(ServiceResult result) {
		this.result = result;
	}

	public abstract void execute();
}
