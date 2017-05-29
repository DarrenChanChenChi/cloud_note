package org.tarena.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteResult;

@Controller("loadNoteController")
@RequestMapping("/note")
public class LoadNoteController {
	private NoteService noteService;

	public NoteService getNoteService() {
		return noteService;
	}

	@Resource(name="noteServiceImpl")
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}
	
	@RequestMapping("/load.do")
	@ResponseBody
	public NoteResult execute(String noteId){
		return noteService.loadNote(noteId);
	}
	
}
