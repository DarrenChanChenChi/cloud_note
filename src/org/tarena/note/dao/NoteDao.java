package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.vo.SearchBean;

public interface NoteDao {
	//根据笔记本ID查询所有的笔记
	public List<Note> findByBookId(String bookId);
	//根据笔记ID查询笔记
	public Note findById(String id);
	//插入笔记
	public int save(Note note);
	//修改笔记
	public int modify(Map<String, Object> map);
	//将笔记放入回收站
	public int updateStatus(String noteId);
	//查询回收站中的笔记
	public List<Note> findRecycle(String userId);
	//将笔记转移到其他笔记本
	public int updateBookId(Map<String, Object> map);
	//将回收站中的笔记恢复
	public int recoverStatus(Map<String, Object> map);
	//彻底删除笔记
	public int deleteByNoteId(String noteId);
	//清空回收站
	public int deleteAll(String userId);
	//批量删除
	public int deleteNotes(String[] ars);
	//动态更新(属性不为null的参与更新)
	public int dynamicUpdate(Note note);
	//组合查询
	public List<Note> findNotes(SearchBean params);
}
