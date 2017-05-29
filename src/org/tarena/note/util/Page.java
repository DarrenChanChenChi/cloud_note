package org.tarena.note.util;

import java.io.Serializable;

public class Page implements Serializable {
	private int current;//第几页
	private int pageSize;//一页几条
	private String keyword;//关键词
	
	//SQL中通过#{begin}获取
	public int getBegin(){
		return (current - 1) * pageSize;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
