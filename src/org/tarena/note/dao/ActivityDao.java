package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tarena.note.entity.Activity;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteActivity;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.util.Page;
import org.tarena.note.vo.SearchBean;

public interface ActivityDao {
	//查询所有的activity
	public List<Activity> findActivities();
	//按照activityId查询所有的noteActivity(分页查询)
	public List<NoteActivity> findByActivityId(Page page);
	//按照noteActivityId查询noteActivity
	public NoteActivity findByNoteActivityId(String noteActivityId);
	//按照noteId查询noteActivity
	public NoteActivity findByNoteId(String noteId);
	//按照用户查询noteActivity
	public List<NoteActivity> findByUser(String userId);
	//新增活动笔记
	public int save(NoteActivity noteActivity);
	//更改活动笔记
	public int update(NoteActivity noteActivity);
	//删除活动笔记
	public int delete(String noteActivityId);
}
