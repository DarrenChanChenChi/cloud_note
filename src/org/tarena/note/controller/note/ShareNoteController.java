package org.tarena.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteResult;

@Controller("shareNoteController")
@RequestMapping("/note")
public class ShareNoteController {
	private NoteService service;

	public NoteService getService() {
		return service;
	}

	@Resource(name="noteServiceImpl")
	public void setService(NoteService service) {
		this.service = service;
	}
	
	@RequestMapping("/share.do")
	@ResponseBody
	public NoteResult execute(String noteId){
		return service.shareNote(noteId);
	}
	
}
