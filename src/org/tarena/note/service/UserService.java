package org.tarena.note.service;

import org.tarena.note.util.NoteResult;

public interface UserService {
	//检查登录
	public NoteResult checkLogin(String username, String password);
	//注册用户
	public NoteResult registUser(String username,String password,String nickname);
	//更改密码
	public NoteResult changePwd(String lastPassword, String newPassword, String userId);
	//根据用户ID查询姓名（所有信息）
	public NoteResult findName(String userId);
}
