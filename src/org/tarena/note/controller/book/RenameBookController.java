package org.tarena.note.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.util.NoteResult;

@Controller("renameBookController")
@RequestMapping("/book")
public class RenameBookController {
	private NoteBookService service;

	public NoteBookService getService() {
		return service;
	}

	@Resource(name="noteBookServiceImpl")
	public void setService(NoteBookService service) {
		this.service = service;
	}
	
	@RequestMapping("/rename.do")
	@ResponseBody
	public NoteResult execute(String bookName, String bookId){
		return service.renameBook(bookName, bookId);
	}
	
}
