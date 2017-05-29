package org.tarena.note.controller.note;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteResult;

@Controller("addNoteController")
@RequestMapping("/note")
public class AddNoteController {
	private NoteService service;

	public NoteService getService() {
		return service;
	}

	@Resource(name="noteServiceImpl")
	public void setService(NoteService service) {
		this.service = service;
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult execute(String noteName, String userId, String bookId){
		return service.addNote(noteName, userId, bookId);
	}
	
}
