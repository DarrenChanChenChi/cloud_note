package org.tarena.note.dao.test;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.UserDao;
import org.tarena.note.entity.User;
import org.tarena.note.util.NoteUtil;
import org.tarena.note.util.SpringTest;

public class TestUserDao extends SpringTest {
	@Test
	public void test1(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = ac.getBean("userDao", UserDao.class);
		User user = userDao.findByName("demo");
		System.out.println(user);
	}
	@Test
	public void test2(){
		User user = new User();
		String id = NoteUtil.createId();
		user.setCn_user_id(id);
		user.setCn_user_name("tarena");
		String md5_pwd = NoteUtil.md5("1234");
		user.setCn_user_password(md5_pwd);
		user.setCn_user_token(null);
		user.setCn_user_desc("tarena");
		UserDao userDao = getContext().getBean("userDao", UserDao.class);
		userDao.save(user);
		//取出
		User db_user = userDao.findByName(user.getCn_user_name());
		//断言
		Assert.assertEquals(id, db_user.getCn_user_id());
		Assert.assertEquals("tarena", db_user.getCn_user_name());
		Assert.assertEquals(md5_pwd, db_user.getCn_user_password());
		Assert.assertEquals("tarena", db_user.getCn_user_desc());
		Assert.assertNull(db_user.getCn_user_token());
	}
	@Test
	public void test3(){
		UserDao dao = getContext().getBean("userDao", UserDao.class);
		User user = dao.findByName("陈驰");
		System.out.println(user);
	}
}
