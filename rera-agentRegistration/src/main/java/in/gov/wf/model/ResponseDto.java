package in.gov.wf.model;

import java.util.Map;

public class ResponseDto {

	private Integer status;
	private String message;
	private Object data;

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResponseDto() {
	}

	public ResponseDto(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public ResponseDto(int status, String message, Object ent) {
		this.status = status;
		this.message = message;
		this.data = ent;
	}

	public static ResponseDto ok() {
		return new ResponseDto(200, "Success");
	}

	public static ResponseDto ok(Object message) {
		return new ResponseDto(200, "Success", message);
	}

	public static ResponseDto ok(String message, Object ent) {
		return new ResponseDto(200, message, ent);
	}

	public static ResponseDto error(String message) {
		return new ResponseDto(500, message);
	}

	public static ResponseDto error(int staus, String message) {
		return new ResponseDto(staus, message);
	}
}
