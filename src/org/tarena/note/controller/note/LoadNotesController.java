package org.tarena.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteResult;

@Controller("loadNotesController")
@RequestMapping("/note")
public class LoadNotesController {
	private NoteService noteService;
	
	public NoteService getNoteService() {
		return noteService;
	}

	@Resource(name="noteServiceImpl")
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}

	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult execute(String bookId){
		return noteService.loadBookNotes(bookId);
	}
}
