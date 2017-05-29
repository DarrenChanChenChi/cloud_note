package org.tarena.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteResult;

@Controller
@RequestMapping("/user")
public class UserGetNameController {
	private UserService service;

	public UserService getService() {
		return service;
	}

	@Resource(name="userServiceImpl")
	public void setService(UserService service) {
		this.service = service;
	}
	
	@RequestMapping("/getname.do")
	@ResponseBody
	public NoteResult execute(String userId){
		return service.findName(userId);
	}
	
}
