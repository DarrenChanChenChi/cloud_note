package org.tarena.note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.CommentDao;
import org.tarena.note.dao.UserDao;
import org.tarena.note.entity.Comment;
import org.tarena.note.entity.User;
import org.tarena.note.util.NoteResult;
import org.tarena.note.util.NoteUtil;

@Service("commentServiceImpl")
@Transactional
public class CommentServiceImpl implements CommentService {
	
	private CommentDao commentDao;
	private UserDao userDao;
	
	public CommentDao getCommentDao() {
		return commentDao;
	}

	@Resource(name="commentDao")
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public NoteResult addComment(String sectionId, String userId,
			String commentContent) {
		NoteResult result = new NoteResult();
		User user = userDao.findAll(userId);
		String userName = user.getCn_user_name();
		String commentId = NoteUtil.createId();
		Comment comment = new Comment();
		comment.setCn_comment_id(commentId);
		comment.setCn_section_id(sectionId);
		comment.setCn_user_id(userId);
		comment.setCn_user_name(userName);
		comment.setCn_comment_content(commentContent);
		commentDao.save(comment);
		
		result.setStatus(0);
		result.setMsg("添加评论成功");
		result.setData(comment);
		return result;
	}

}
