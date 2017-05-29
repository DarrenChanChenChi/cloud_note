package org.tarena.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteResult;

@Controller("userChangePwdController")
@RequestMapping("/user")
public class UserChangePwdController {
	private UserService service;

	public UserService getService() {
		return service;
	}

	@Resource(name="userServiceImpl")
	public void setService(UserService service) {
		this.service = service;
	}
	
	@RequestMapping("/changepwd.do")
	@ResponseBody
	public NoteResult execute(String lastPassword, String newPassword, String userId){
		return service.changePwd(lastPassword, newPassword, userId);
	}
	
}
