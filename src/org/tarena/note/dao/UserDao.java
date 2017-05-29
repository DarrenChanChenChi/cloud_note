package org.tarena.note.dao;

import org.tarena.note.entity.User;

public interface UserDao {
	//用户名查询用户
	public User findByName(String name);
	//用户ID查询密码
	public String findPwd(String userId);
	//用户ID查询所有
	public User findAll(String userId);
	//保存用户
	public int save(User user);
	//更改用户密码
	public int updatePwd(String userPwd);
}
