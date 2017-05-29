package org.tarena.note.util;

import java.io.Serializable;

/**
 * 作为服务器返回JSON格式的结果对象
 * @author soft01
 *
 */
public class NoteResult implements Serializable {
	private int status;//返回的状态
	private String msg;//返回的消息
	private Object data;//返回的数据
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "NoteResult [data=" + data + ", msg=" + msg + ", status="
				+ status + "]";
	}
	
}
