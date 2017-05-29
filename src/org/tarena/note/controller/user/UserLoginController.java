package org.tarena.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteResult;

@Controller("userLoginController")
@RequestMapping("/user")
public class UserLoginController {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Resource(name="userServiceImpl")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult execute(String username, String password){
		//调用业务组件处理
		NoteResult result = userService.checkLogin(username, password);
		return result;
	}
	
}
