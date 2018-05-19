package cn.bsnt.web.order.util;

import java.io.Serializable;

/**
 * 该类用于封装返回给客户端的数据
 * status等于0表明请求成功,其他值表示请求失败
 * 封装后的结果形式如:{status:0,data:"",message:""}
 * @author Administrator
 *
 */
public class JsonResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer status;
	private Object data;
	private String message;
	public JsonResult() {
	}
	public JsonResult(Integer status, Object data, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}
	public JsonResult(Integer status, Exception e) {
		this.status = status;
		this.data = null;
		this.message = e.getMessage();
	}
	public JsonResult(Integer status,Object data){
		this.status = status;
		this.data = data;
		this.message = "";
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "JsonResult [status=" + status + ", data=" + data + ", message="
				+ message + "]";
	}
	
	
}
