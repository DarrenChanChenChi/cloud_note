package org.tarena.note.vo;

import java.io.Serializable;
import java.sql.Date;

public class SearchBean implements Serializable {
	
	private String title;//标题
	private String status;//状态
	private String beginTime;//开始日期
	private String endTime;//结束日期
	private String userId;//用户ID
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	//#{longBeginTime}将请求传入的String类型日期转成Long表示
	public Long getLongBeginTime(){
		if(beginTime == null || "".equals(beginTime)){
			return null;
		}else{
			Date time = Date.valueOf(beginTime);
			return time.getTime(); 
		}
	}
	
	public Long getLongEndTime(){
		if(endTime == null || "".equals(endTime)){
			return null;
		}else{
			Date time = Date.valueOf(endTime);
			return time.getTime(); 
		}
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
