package org.tarena.note.controller.activity;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.ActivityService;
import org.tarena.note.util.NoteResult;

@Controller
@RequestMapping("/activity")
public class LoadUserActivityNoteController {
	private ActivityService service;

	public ActivityService getService() {
		return service;
	}

	@Resource(name="activityServiceImpl")
	public void setService(ActivityService service) {
		this.service = service;
	}
	
	@RequestMapping("/loaduser.do")
	@ResponseBody
	public NoteResult execute(String userId){
		return service.loadUserActivityNotes(userId);
	}
	
}
