package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tarena.note.entity.NoteBook;

public interface NoteBookDao {
	//根据用户ID查询笔记本
	public List<NoteBook> findByUserId(String userId);
	//新增笔记本
	public int save(NoteBook book);
	//重命名笔记本
	public int renameBook(Map<String, Object> map);
	//删除笔记本
	public int deleteByBookId(String bookId);
}
