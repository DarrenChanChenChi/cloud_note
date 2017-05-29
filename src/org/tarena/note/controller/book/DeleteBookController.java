package org.tarena.note.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.util.NoteResult;

@Controller("deleteBookController")
@RequestMapping("/book")
public class DeleteBookController {
	private NoteBookService service;

	public NoteBookService getService() {
		return service;
	}

	@Resource(name="noteBookServiceImpl")
	public void setService(NoteBookService service) {
		this.service = service;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public NoteResult execute(String bookId){
		return service.deleteBook(bookId);
	}
	
}
