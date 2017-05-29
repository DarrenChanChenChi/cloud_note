package org.tarena.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteResult;

@Controller("recycleNoteController")
@RequestMapping("/note")
public class RecycleNoteController {
	@Resource
	private NoteService service;
	
	@RequestMapping("/recycle.do")
	@ResponseBody
	public NoteResult execute(String noteId){
		NoteResult result = service.recycleNote(noteId);
		return result;
	}
}
