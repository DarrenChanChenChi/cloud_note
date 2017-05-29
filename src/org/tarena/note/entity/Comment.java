package org.tarena.note.entity;

public class Comment {
	private String cn_comment_id;
	private String cn_section_id;
	private String cn_user_id;
	private String cn_user_name;
	private String cn_comment_content;
	
	public String getCn_comment_content() {
		return cn_comment_content;
	}
	public void setCn_comment_content(String cn_comment_content) {
		this.cn_comment_content = cn_comment_content;
	}
	public String getCn_comment_id() {
		return cn_comment_id;
	}
	public void setCn_comment_id(String cn_comment_id) {
		this.cn_comment_id = cn_comment_id;
	}
	public String getCn_section_id() {
		return cn_section_id;
	}
	public void setCn_section_id(String cn_section_id) {
		this.cn_section_id = cn_section_id;
	}
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_user_name() {
		return cn_user_name;
	}
	public void setCn_user_name(String cn_user_name) {
		this.cn_user_name = cn_user_name;
	}
	
	@Override
	public String toString() {
		return "Comment [cn_comment_id=" + cn_comment_id + ", cn_section_id="
				+ cn_section_id + ", cn_user_id=" + cn_user_id
				+ ", cn_user_name=" + cn_user_name + ", cn_comment_content="
				+ cn_comment_content + "]";
	}
	
}
