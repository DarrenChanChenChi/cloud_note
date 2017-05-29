package org.tarena.note.service;

import org.tarena.note.util.NoteResult;

public interface NoteBookService {
	//关联查询（两个表）
	public NoteResult loadBooks();
	//加载笔记本
	public NoteResult loaduserBooks(String userId);
	//新增笔记本
	public NoteResult addBook(String bookName, String userId);
	//重命名笔记本
	public NoteResult renameBook(String bookName, String bookId);
	//删除笔记本
	public NoteResult deleteBook(String bookId);
}
