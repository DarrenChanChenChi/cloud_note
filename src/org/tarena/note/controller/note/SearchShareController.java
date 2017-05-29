package org.tarena.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteResult;
import org.tarena.note.util.Page;

@Controller("searchShareController")
@RequestMapping("/note")
public class SearchShareController {
	private NoteService service;

	public NoteService getService() {
		return service;
	}

	@Resource(name="noteServiceImpl")
	public void setService(NoteService service) {
		this.service = service;
	}
	
	@RequestMapping("/search.do")
	@ResponseBody
	public NoteResult execute(String keyword, int current, int pageSize){
		return service.searchNote(keyword, current, pageSize);
	}
	
}
