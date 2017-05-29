package org.tarena.note.service;

import org.tarena.note.util.NoteResult;

public interface CommentService {
	//增加评论
	public NoteResult addComment(String sectionId, String userId, String commentContent);
}
