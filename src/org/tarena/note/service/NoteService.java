package org.tarena.note.service;

import org.tarena.note.util.NoteResult;
import org.tarena.note.util.Page;
import org.tarena.note.vo.SearchBean;

public interface NoteService {
	//显示所有的笔记列表
	public NoteResult loadBookNotes(String bookId);
	//显示笔记内容
	public NoteResult loadNote(String noteId);
	//添加笔记
	public NoteResult addNote(String noteName, String userId, String bookId);
	//修改笔记
	public NoteResult modifyNote(String noteName, String noteBody, String noteId);
	//将笔记放入回收站
	public NoteResult recycleNote(String noteId);
	//分享笔记
	public NoteResult shareNote(String noteId);
	//显示共享笔记内容
	public NoteResult loadShareNote(String shareId);
	//收藏笔记
	public NoteResult likeNote(String shareId, String userId);
	//显示收藏笔记内容
	public NoteResult loadLikeNote(String likeId);
	//显示收藏笔记列表
	public NoteResult loadLikeNotes(String userId);
	//取消收藏
	public NoteResult deleteLikeNote(String likeId);
	//显示回收站笔记列表
	public NoteResult loadRecycle(String userId);
	//移动笔记
	public NoteResult moveNote(String noteId,String bookId);
	//检索共享笔记
	public NoteResult searchNote(String keyword, int current, int pageSize);
	//回收站中的笔记恢复
	public NoteResult recoverNote(String noteId, String bookId);
	//彻底删除笔记
	public NoteResult deleteNote(String noteId);
	//清空回收站
	public NoteResult deleteAllNotes(String userId);
//	//分页查询
//	public NoteResult findPage(int current, int pageSize);
	//高级查询（组合查询）
	public NoteResult searchNotes(SearchBean params);

}
