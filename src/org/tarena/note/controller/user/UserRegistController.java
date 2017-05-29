package org.tarena.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteResult;

@Controller("userRegistController")
@RequestMapping("/user")
public class UserRegistController {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Resource(name="userServiceImpl")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public NoteResult execute(String username,String password,String nickname){
		return userService.registUser(username, password, nickname);
	}
}
