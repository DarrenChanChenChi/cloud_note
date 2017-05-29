package org.tarena.note.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class NoteBook implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cn_notebook_id;
	private String cn_user_id;
	private String cn_notebook_type_id;
	private String cn_notebook_name;
	private String cn_notebook_desc;
	private Timestamp cn_notebook_createtime;
	//追加关联属性,用来装载关联的User信息
	private User user;
	public String getCn_notebook_id() {
		return cn_notebook_id;
	}
	public void setCn_notebook_id(String cnNotebookId) {
		cn_notebook_id = cnNotebookId;
	}
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cnUserId) {
		cn_user_id = cnUserId;
	}
	public String getCn_notebook_type_id() {
		return cn_notebook_type_id;
	}
	public void setCn_notebook_type_id(String cnNotebookTypeId) {
		cn_notebook_type_id = cnNotebookTypeId;
	}
	public String getCn_notebook_name() {
		return cn_notebook_name;
	}
	public void setCn_notebook_name(String cnNotebookName) {
		cn_notebook_name = cnNotebookName;
	}
	public String getCn_notebook_desc() {
		return cn_notebook_desc;
	}
	public void setCn_notebook_desc(String cnNotebookDesc) {
		cn_notebook_desc = cnNotebookDesc;
	}
	public Timestamp getCn_notebook_createtime() {
		return cn_notebook_createtime;
	}
	public void setCn_notebook_createtime(Timestamp cnNotebookCreatetime) {
		cn_notebook_createtime = cnNotebookCreatetime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "NoteBook [cn_notebook_createtime=" + cn_notebook_createtime
				+ ", cn_notebook_desc=" + cn_notebook_desc
				+ ", cn_notebook_id=" + cn_notebook_id + ", cn_notebook_name="
				+ cn_notebook_name + ", cn_notebook_type_id="
				+ cn_notebook_type_id + ", cn_user_id=" + cn_user_id
				+ ", user=" + user + "]";
	}
	
}
