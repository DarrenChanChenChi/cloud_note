package org.tarena.note.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.util.NoteResult;

@Controller("loadUserBooksController")
@RequestMapping("/notebook")
public class LoadUserBooksController {
	private NoteBookService bookService;

	public NoteBookService getBookService() {
		return bookService;
	}

	@Resource(name="noteBookServiceImpl")
	public void setBookService(NoteBookService bookService) {
		this.bookService = bookService;
	}
	
	@RequestMapping("/loadbooks.do")
	@ResponseBody
	public NoteResult execute(String userId){
		return bookService.loaduserBooks(userId);
	}
	
}
