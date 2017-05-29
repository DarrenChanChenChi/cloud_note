package org.tarena.note.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.BookJoinDao;
import org.tarena.note.dao.NoteBookDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.util.NoteResult;
import org.tarena.note.util.NoteUtil;

@Service("noteBookServiceImpl")
@Transactional
public class NoteBookServiceImpl implements NoteBookService {
	
	private NoteBookDao bookDao;

	public NoteBookDao getBookDao() {
		return bookDao;
	}

	@Resource(name="noteBookDao")
	public void setBookDao(NoteBookDao bookDao) {
		this.bookDao = bookDao;
	}


	@Transactional(readOnly=true)
	public NoteResult loaduserBooks(String userId) {
		List<NoteBook> list = bookDao.findByUserId(userId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(list);
		return result;
	}

	public NoteResult addBook(String bookName, String userId) {
		//可以追加笔记本重名检测
		List<NoteBook> list = bookDao.findByUserId(userId);
		NoteResult result = new NoteResult();
		for (NoteBook noteBook : list) {
			if(noteBook.getCn_notebook_name().equals(bookName)){
				result.setStatus(3);//名称重复
				result.setMsg("笔记本名称重复");
				result.setData(null);
				return result;
			}
		}
		NoteBook book = new NoteBook();
		book.setCn_notebook_name(bookName);//设置笔记本名
		book.setCn_user_id(userId);//设置用户ID
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);//设置笔记本ID
		book.setCn_notebook_desc("");//设置描述
		book.setCn_notebook_type_id("5");//normal
		Timestamp time = new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);
		bookDao.save(book);
		result.setStatus(0);
		result.setMsg("创建笔记本成功");
		result.setData(bookId);//返回新建的笔记本ID
		return result;
	}

	public NoteResult renameBook(String bookName, String bookId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookName", bookName);
		map.put("bookId", bookId);
		bookDao.renameBook(map);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("更改笔记本名成功");
		result.setData(null);
		return result;
	}

	public NoteResult deleteBook(String bookId) {
		bookDao.deleteByBookId(bookId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("删除笔记本成功");
		result.setData(null);
		return result;
	}

	@Resource
	private BookJoinDao joinDao;
	public NoteResult loadBooks() {
		List<NoteBook> list = joinDao.findJoinUser();
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(list);
		return result;
	}

}
