package org.tarena.note.dao;


import java.util.List;

import org.springframework.stereotype.Repository;
import org.tarena.note.entity.Comment;

public interface CommentDao {
	//保存评论
	public int save(Comment comment);
	//加载评论
	public List<Comment> findBySectionId(String sectionId);
}
