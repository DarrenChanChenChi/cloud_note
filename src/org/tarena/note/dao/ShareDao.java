package org.tarena.note.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.Share;
import org.tarena.note.util.Page;

public interface ShareDao {
	//保存到共享笔记
	public int save(Share share);
	//修改共享笔记
	public int updateShare(Share share);
	//通过noteId来查找
	public Share findByNoteId(String noteId);
	//通过shareId来查找
	public Share findByShareId(String shareId);
	//通过关键字检索
	public List<Share> findLikeTitle(Page page);
//	//分页查询
//	public List<Share> findPage(Page page);
}
