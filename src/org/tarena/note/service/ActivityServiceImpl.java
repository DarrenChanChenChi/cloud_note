package org.tarena.note.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.ActivityDao;
import org.tarena.note.dao.NoteDao;
import org.tarena.note.entity.Activity;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteActivity;
import org.tarena.note.util.NoteResult;
import org.tarena.note.util.NoteUtil;
import org.tarena.note.util.Page;

@Service("activityServiceImpl")
@Transactional
public class ActivityServiceImpl implements ActivityService {
	
	private ActivityDao activityDao;
	private NoteDao noteDao;

	public ActivityDao getActivityDao() {
		return activityDao;
	}

	@Resource
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}
	
	public NoteDao getNoteDao() {
		return noteDao;
	}

	@Resource
	public void setNoteDao(NoteDao noteDao) {
		this.noteDao = noteDao;
	}

	/**
	 * 加载所有的活动
	 */
	@Override
	public NoteResult loadActivities() {
		List<Activity> list = activityDao.findActivities();
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("显示活动列表成功");
		result.setData(list);
		return result;
	}

	/**
	 * 加载参加该活动的笔记
	 */
	@Override
	public NoteResult loadActivityNotes(String activityId, int current, int pageSize) {
		Page page = new Page();
		page.setKeyword(activityId);
		page.setCurrent(current);
		page.setPageSize(pageSize);
		List<NoteActivity> list = activityDao.findByActivityId(page);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("显示参与活动笔记列表成功");
		result.setData(list);
		return result;
	}

	/**
	 * 加载活动笔记内容
	 */
	@Override
	public NoteResult loadActivityNote(String noteActivityId) {
		NoteActivity noteActivity = activityDao.findByNoteActivityId(noteActivityId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("加载笔记内容成功");
		result.setData(noteActivity);
		return result;
	}

	/**
	 * 新增活动笔记
	 */
	@Override
	public NoteResult addActivityNote(String noteId, String activityId) {
		
		NoteActivity has_note_activity = activityDao.findByNoteId(noteId);
		Note note = noteDao.findById(noteId);
		NoteActivity noteActivity = new NoteActivity();
		if(has_note_activity != null){
			noteActivity.setCn_note_activity_up(0);
			noteActivity.setCn_note_activity_down(0);
			noteActivity.setCn_note_id(noteId);
			noteActivity.setCn_note_activity_title(has_note_activity.getCn_note_activity_title());
			noteActivity.setCn_note_activity_body(has_note_activity.getCn_note_activity_body());
			activityDao.update(noteActivity);
			NoteResult result = new NoteResult();
			result.setStatus(3);
			result.setMsg("笔记重新参加活动成功");
			result.setData(null);
			return result;
		}
		
	    noteActivity.setCn_activity_id(activityId);
	    noteActivity.setCn_note_id(noteId);
	    noteActivity.setCn_note_activity_id(NoteUtil.createId());
	    noteActivity.setCn_note_activity_up(0);
	    noteActivity.setCn_note_activity_down(0);
	    noteActivity.setCn_note_activity_title(note.getCn_note_title());
	    noteActivity.setCn_note_activity_body(note.getCn_note_body());
	    activityDao.save(noteActivity);
	    NoteResult result = new NoteResult();
	    result.setStatus(0);
	    result.setMsg("新增活动笔记成功");
	    result.setData(null);
		return result;
	}

	/**
	 * 加载某一用户的全部活动笔记
	 */
	@Override
	public NoteResult loadUserActivityNotes(String userId) {
		List<NoteActivity> list = activityDao.findByUser(userId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("加载用户活动笔记成功");
		result.setData(list);
		return result;
	}

	/**
	 * 删除活动笔记
	 */
	@Override
	public NoteResult deleteActivityNote(String noteActivityId) {
		int num = activityDao.delete(noteActivityId);
		NoteResult result = new NoteResult();
		if(num > 0){
			result.setStatus(0);
			result.setMsg("删除活动笔记成功");
			result.setData(null);
			return result;
		}
		return null;
	}

	/**
	 * 更改活动笔记信息
	 */
	@Override
	public NoteResult updateActivityNote(String noteActivityId, Integer upNum, Integer downNum) {
		NoteActivity noteActivity = activityDao.findByNoteActivityId(noteActivityId);
		noteActivity.setCn_note_activity_up(upNum);
		noteActivity.setCn_note_activity_down(downNum);
		int num = activityDao.update(noteActivity);
		if(num > 0){
			NoteResult result = new NoteResult();
			result.setStatus(0);
			result.setMsg("更新活动笔记成功");
			result.setData(null);
			return result;
		}
		return null;
	}

}
