package org.tarena.note.controller.comment;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.CommentService;
import org.tarena.note.util.NoteResult;

@Controller("addCommentController")
@RequestMapping("/comment")
public class AddCommentController {
	private CommentService service;

	public CommentService getService() {
		return service;
	}

	@Resource(name="commentServiceImpl")
	public void setService(CommentService service) {
		this.service = service;
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult execute(String sectionId, String userId, String commentContent){
		return service.addComment(sectionId, userId, commentContent);
	}
	
}
