package org.tarena.note.entity;

import java.io.Serializable;

public class Like implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cn_like_id;
	private String cn_user_id;
	private String cn_note_id;
	private String cn_like_type_id;
	private Long cn_like_last_modify_time;
	private String cn_share_title;
	private String cn_share_body;
	public String getCn_share_title() {
		return cn_share_title;
	}
	public void setCn_share_title(String cn_share_title) {
		this.cn_share_title = cn_share_title;
	}
	public String getCn_share_body() {
		return cn_share_body;
	}
	public void setCn_share_body(String cn_share_body) {
		this.cn_share_body = cn_share_body;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCn_like_id() {
		return cn_like_id;
	}
	public void setCn_like_id(String cn_like_id) {
		this.cn_like_id = cn_like_id;
	}
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_note_id() {
		return cn_note_id;
	}
	public void setCn_note_id(String cn_note_id) {
		this.cn_note_id = cn_note_id;
	}
	public String getCn_like_type_id() {
		return cn_like_type_id;
	}
	public void setCn_like_type_id(String cn_like_type_id) {
		this.cn_like_type_id = cn_like_type_id;
	}
	public Long getCn_like_last_modify_time() {
		return cn_like_last_modify_time;
	}
	public void setCn_like_last_modify_time(Long cn_like_last_modify_time) {
		this.cn_like_last_modify_time = cn_like_last_modify_time;
	}
	@Override
	public String toString() {
		return "Like [cn_like_id=" + cn_like_id + ", cn_user_id=" + cn_user_id
				+ ", cn_note_id=" + cn_note_id + ", cn_like_type_id="
				+ cn_like_type_id + ", cn_like_last_modify_time="
				+ cn_like_last_modify_time + ", cn_share_title="
				+ cn_share_title + ", cn_share_body=" + cn_share_body + "]";
	}
	
	
	
}
