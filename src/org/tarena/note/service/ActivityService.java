package org.tarena.note.service;

import org.tarena.note.entity.NoteActivity;
import org.tarena.note.util.NoteResult;

public interface ActivityService {
	//显示所有活动信息
	public NoteResult loadActivities();
	//显示参与活动笔记列表
	public NoteResult loadActivityNotes(String activityId, int current, int pageSize);
	//单击活动笔记显示笔记内容
	public NoteResult loadActivityNote(String noteActivityId);
	//新增活动笔记
	public NoteResult addActivityNote(String noteId, String activityId);
	//显示某一用户的全部活动笔记
	public NoteResult loadUserActivityNotes(String userId);
	//删除活动笔记
	public NoteResult deleteActivityNote(String noteActivityId);
	//更改活动笔记信息
	public NoteResult updateActivityNote(String noteActivityId, Integer upNum, Integer downNum);
}
