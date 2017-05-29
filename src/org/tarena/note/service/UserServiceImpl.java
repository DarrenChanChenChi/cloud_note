package org.tarena.note.service;

import javax.annotation.Resource;

import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.UserDao;
import org.tarena.note.entity.User;
import org.tarena.note.util.NoteResult;
import org.tarena.note.util.NoteUtil;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public NoteResult checkLogin(String username, String password) {
		NoteResult result = new NoteResult();
		//检测用户名
		User user = userDao.findByName(username);
		if(user == null){
			result.setStatus(1);
			result.setMsg("用户不存在");
			result.setData(null);
			return result;
		}
		//检测密码
		//将用户收入的密码加密
		String md5_password = NoteUtil.md5(password);
		//将加密结果和数据表中的密文进行对比
		if(!user.getCn_user_password().equals(md5_password)){
			result.setStatus(2);
			result.setMsg("密码错误");
			result.setData(null);
			return result;
		}
		result.setStatus(0);
		result.setMsg("用户名和密码正确");
		result.setData(user.getCn_user_id());
		return result;
	}

	public NoteResult registUser(String username, String password,
			String nickname) {
		NoteResult result = new NoteResult();
		//检测用户名是否注册
		User dbUser = userDao.findByName(username);
		if(dbUser != null){
			result.setStatus(1);
			result.setMsg("用户名已存在");
			result.setData(null);
			return result;
		}
		//注册操作
		User user = new User();
		String id = NoteUtil.createId();
		user.setCn_user_id(id);//设置ID
		user.setCn_user_name(username);//设置用户名
		String md5_pwd = NoteUtil.md5(password);
		user.setCn_user_password(md5_pwd);//设置密码
		user.setCn_user_token(null);//设置令牌
		user.setCn_user_desc(nickname);//设置昵称
		userDao.save(user);//保存到cn_user表
		result.setStatus(0);
		result.setMsg("注册成功");
		result.setData(null);
		
//		//模拟一个异常
//		String str = null;
//		str.length();
		return result;
	}

	public NoteResult changePwd(String lastPassword, String newPassword, String userId) {
		String password = userDao.findPwd(userId);
		NoteResult result = new NoteResult();
		//将用户收入的密码加密
		String md5_lastPassword = NoteUtil.md5(lastPassword);
		String md5_newPassword = NoteUtil.md5(newPassword);
		if(!md5_lastPassword.equals(password)){
			result.setStatus(2);
			result.setMsg("原密码错误");
			result.setData(null);
			return result;
		}
		userDao.updatePwd(md5_newPassword);
		result.setStatus(0);
		result.setMsg("修改密码成功");
		result.setData(null);
		return result;
	}

	@Override
	public NoteResult findName(String userId) {
		User user = userDao.findAll(userId);
		String userName = user.getCn_user_name();
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("获取姓名成功");
		result.setData(userName);
		return result;
	}

}
