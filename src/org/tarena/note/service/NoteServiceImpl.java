package org.tarena.note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.LikeDao;
import org.tarena.note.dao.NoteDao;
import org.tarena.note.dao.ShareDao;
import org.tarena.note.entity.Like;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.Share;
import org.tarena.note.util.NoteResult;
import org.tarena.note.util.NoteUtil;
import org.tarena.note.util.Page;
import org.tarena.note.vo.SearchBean;

@Service("noteServiceImpl")
@Transactional
public class NoteServiceImpl implements NoteService {
	
	private NoteDao noteDao;
	private ShareDao shareDao;
	private LikeDao likeDao;
	
	public NoteDao getNoteDao() {
		return noteDao;
	}

	public ShareDao getShareDao() {
		return shareDao;
	}
	
	public LikeDao getLikeDao() {
		return likeDao;
	}


	@Resource(name="shareDao")
	public void setShareDao(ShareDao shareDao) {
		this.shareDao = shareDao;
	}

	@Resource(name="noteDao")
	public void setNoteDao(NoteDao noteDao) {
		this.noteDao = noteDao;
	}

	@Resource(name="likeDao")
	public void setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
	}
	
	/**
	 * 加载笔记列表
	 */
	public NoteResult loadBookNotes(String bookId) {
		List<Note> list = noteDao.findByBookId(bookId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("查询笔记成功");
		result.setData(list);
		return result;
	}

	/**
	 * 加载笔记信息
	 */
	public NoteResult loadNote(String noteId) {
		Note note = noteDao.findById(noteId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("加载笔记信息成功");
		result.setData(note);
		return result;
	}

	/**
	 * 新建笔记
	 */
	public NoteResult addNote(String noteName, String userId, String bookId) {
		//可以追加笔记重名检测
		List<Note> list = noteDao.findByBookId(bookId);
		NoteResult result = new NoteResult();
		for (Note note : list) {
			if(note.getCn_note_title().equals(noteName)){
				result.setStatus(3);//名称重复
				result.setMsg("笔记名称重复");
				result.setData(null);
				return result;
			}
		}
		Note note = new Note();
		note.setCn_user_id(userId);//设置用户ID
		note.setCn_notebook_id(bookId);//设置笔记本ID
		note.setCn_note_title(noteName);//设置笔记标题
		note.setCn_note_body("");//设置笔记内容
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_type_id("1");//normal
		note.setCn_note_status_id("1");//normal
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);//设置笔记ID
		noteDao.save(note);
		result.setStatus(0);
		result.setMsg("创建笔记成功");
		result.setData(noteId);
		return result;
	}

	/**
	 * 修改保存笔记
	 */
	public NoteResult modifyNote(String noteName, String noteBody, String noteId) {
		//可以追加笔记重名检测
		NoteResult result = new NoteResult();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", noteName);
		map.put("body", noteBody);
		map.put("time", System.currentTimeMillis());
		map.put("id", noteId);
       noteDao.modify(map);
		result.setStatus(0);
		result.setMsg("保存笔记成功");
		result.setData(map);
		return result;
	}

	/**
	 * 将笔记放入回收站
	 */
	public NoteResult recycleNote(String noteId) {
		noteDao.updateStatus(noteId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("放入回收站成功");
		result.setData(null);
		return result;
	}

	/**
	 * 分享笔记
	 */
	public NoteResult shareNote(String noteId) {
		
		NoteResult result = new NoteResult();
		//检查是否已经分享过
		Share has_share = shareDao.findByNoteId(noteId);
		Note note = noteDao.findById(noteId);
		if(has_share != null){
			has_share.setCn_share_title(note.getCn_note_title());
			has_share.setCn_share_body(note.getCn_note_body());
			
			shareDao.updateShare(has_share);//添加cn_share记录
			result.setStatus(1);
			result.setMsg("重新分享成功");
			result.setData(null);
			return result;
		}
		Share share = new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		String shareId = NoteUtil.createId();
		share.setCn_share_id(shareId);
		shareDao.save(share);//添加cn_share记录
		result.setStatus(0);
		result.setMsg("分享笔记成功");
		result.setData(null);
		
		return result;
	}
	
	
	/**
	 * 加载共享笔记
	 */
	public NoteResult loadShareNote(String shareId) {
		Share share = shareDao.findByShareId(shareId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("加载共享笔记信息成功");
		result.setData(share);
		return result;
	}

	/**
	 * 加载回收站笔记列表
	 */
	public NoteResult loadRecycle(String userId) {
		List<Note> list = noteDao.findRecycle(userId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("加载回收站列表成功");
		result.setData(list);
		return result;
	}

	/**
	 * 移动笔记
	 */
	public NoteResult moveNote(String noteId, String bookId) {
		
		NoteResult result = new NoteResult();
		//可以追加笔记重名检测
		List<Note> list = noteDao.findByBookId(bookId);
		Note note2 = noteDao.findById(noteId);
		for (Note note : list) {
			if(note.getCn_note_title().equals(note2.getCn_note_title())){
				result.setStatus(3);//名称重复
				result.setMsg("笔记名称重复");
				result.setData(null);
				return result;
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookId", bookId);
		map.put("noteId", noteId);
		noteDao.updateBookId(map);
		
		
		result.setStatus(0);
		result.setMsg("转移笔记成功");
		result.setData(null);
			
		return result;
	}

	/**
	 * 检索笔记，并进行分页
	 */
	public NoteResult searchNote(String keyword, int current, int pageSize) {
		String title = "";
		if(keyword==null || "".equals(keyword)){
			title = "%";
		}else{
			title = "%"+keyword.trim()+"%";
		}
		keyword = title;
		Page page = new Page();
		page.setKeyword(keyword);
		page.setPageSize(pageSize);
		page.setCurrent(current);
		List<Share> list = shareDao.findLikeTitle(page);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("搜索成功");
		result.setData(list);
		return result;
	}

	/**
	 * 将回收站中的笔记进行恢复
	 */
	public NoteResult recoverNote(String noteId, String bookId) {
		NoteResult result = new NoteResult();
		//可以追加笔记重名检测
		List<Note> list = noteDao.findByBookId(bookId);
		Note note2 = noteDao.findById(noteId);
		for (Note note : list) {
			if(note.getCn_note_title().equals(note2.getCn_note_title())){
				result.setStatus(3);//名称重复
				result.setMsg("笔记名称重复");
				result.setData(null);
				return result;
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookId", bookId);
		map.put("noteId", noteId);
		noteDao.recoverStatus(map);
		
		result.setStatus(0);
		result.setMsg("从回收站恢复成功");
		result.setData(null);
		
		return result;
	}

	/**
	 * 彻底删除笔记
	 */
	public NoteResult deleteNote(String noteId) {
		noteDao.deleteByNoteId(noteId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("删除笔记成功");
		result.setData(null);
		return result;
	}

//	public NoteResult findPage(int current, int pageSize) {
//		Page page = new Page();
//		page.setCurrent(current);
//		page.setPageSize(pageSize);
//		List<Share> list = shareDao.findPage(page);
//		
//		NoteResult result = new NoteResult();
//		result.setStatus(0);
//		result.setMsg("分页成功");
//		result.setData(list);
//		return result;
//	}

	

	/**
	 * 清空回收站
	 */
	public NoteResult deleteAllNotes(String userId) {
		noteDao.deleteAll(userId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("清空回收站成功");
		result.setData(null);
		return result;
	}
	
	/**
	 * 收藏笔记
	 */
	public NoteResult likeNote(String shareId, String userId){
		NoteResult result = new NoteResult();
		Share share = shareDao.findByShareId(shareId);
		String noteId = share.getCn_note_id();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("noteId", noteId);
		//检查是否收藏过
		Like has_like = likeDao.findByNoteUserId(map);
		if(has_like != null){
			//已经收藏过
			has_like.setCn_share_title(share.getCn_share_title());
			has_like.setCn_share_body(share.getCn_share_body());
			likeDao.updateLike(has_like);
			result.setStatus(1);
			result.setMsg("重新收藏成功");
			result.setData(null);
			return result;
		}
		Like like = new Like();
		like.setCn_user_id(userId);
		String likeId = NoteUtil.createId();
		like.setCn_like_id(likeId);
		like.setCn_note_id(noteId);
		like.setCn_like_type_id("0");
		like.setCn_like_last_modify_time(System.currentTimeMillis());
		like.setCn_share_title(share.getCn_share_title());
		like.setCn_share_body(share.getCn_share_body());
		likeDao.save(like);
		
		result.setStatus(0);
		result.setMsg("收藏笔记成功");
		result.setData(null);
		return result;
	}

	/**
	 * 加载收藏笔记列表
	 */
	public NoteResult loadLikeNotes(String userId) {
		NoteResult result = new NoteResult();
		List<Like> list = likeDao.findByUserId(userId);
		result.setStatus(0);
		result.setMsg("加载收藏笔记列表成功");
		result.setData(list);
		return result;
	}

	/**
	 * 加载收藏笔记信息
	 */
	public NoteResult loadLikeNote(String likeId) {
		Like like = likeDao.findByLikeId(likeId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("加载收藏笔记成功");
		result.setData(like);
		return result;
	}

	/**
	 * 取消收藏笔记
	 */
	public NoteResult deleteLikeNote(String likeId) {
		likeDao.deleteLike(likeId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("取消收藏成功");
		result.setData(null);
		return result;
	}
	
	/**
	 * 组合查询（高级查询）
	 */
	public NoteResult searchNotes(SearchBean params) {
		//执行搜索处理
		//处理内容要么有内容不为NULL,要么为NULL
		if(params.getTitle()!=null && !"".equals(params.getTitle())){
			String title = "%"+params.getTitle()+"%";
			params.setTitle(title);
		}else{
			params.setTitle(null);
		}
		//选全部-all,选项设置为Null
		if("0".equals(params.getStatus())){
			params.setStatus(null);
		}
		
		List<Note> list = noteDao.findNotes(params);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("检索成功");
		result.setData(list);
		return result;
	}

}
