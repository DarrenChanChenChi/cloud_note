package org.tarena.note.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.util.NoteResult;

@Controller("addBookController")
@RequestMapping("/notebook")
public class AddBookController {
	private NoteBookService service;
	
	public NoteBookService getService() {
		return service;
	}

	@Resource(name="noteBookServiceImpl")
	public void setService(NoteBookService service) {
		this.service = service;
	}

	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult execute(String bookName, String userId){
		return service.addBook(bookName, userId);
	}
}
