package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tarena.note.entity.Like;

public interface LikeDao {
	//保存到收藏笔记
	public int save(Like like);
	//修改收藏笔记
	public int updateLike(Like like);
	//通过noteId来查找
	public Like findByNoteId(String noteId);
	//通过userId来查找
	public List<Like> findByUserId(String userId);
	//通过userId和noteId来查找
	public Like findByNoteUserId(Map<String, Object> map);
	//通过likeId来查找
	public Like findByLikeId(String likeId);
	//删除收藏笔记
	public int deleteLike(String likeId);
}
