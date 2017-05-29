package org.tarena.note.dao.test;

import java.util.List;

import org.junit.Test;
import org.tarena.note.dao.BookJoinDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.User;
import org.tarena.note.util.SpringTest;

public class TestBookJoin extends SpringTest {
	@Test
	public void test1(){
		BookJoinDao dao = getContext().getBean("bookJoinDao", BookJoinDao.class);
		List<NoteBook> list = dao.findJoinUser();
		for (NoteBook noteBook : list) {
			System.out.println(noteBook);
		}
	}
	
	@Test
	public void test2(){
		BookJoinDao dao = getContext().getBean("bookJoinDao", BookJoinDao.class);
		List<NoteBook> list = dao.findJoinUser1();
		for (NoteBook noteBook : list) {
			System.out.println(noteBook);
		}
	}
	
	@Test
	public void test3(){
		BookJoinDao dao = getContext().getBean("bookJoinDao", BookJoinDao.class);
		User user = dao.findJoinBooks("ea09d9b1-ede7-4bd8-b43d-a546680df00b");
		System.out.println("该用户信息如下");
		System.out.println(user.getCn_user_name());
		System.out.println(user.getCn_user_password());
		System.out.println("包含的笔记本信息如下");
		for(NoteBook book : user.getBooks()){
			System.out.println(book.getCn_notebook_name());
		}
	}
	
	@Test
	public void test4(){
		BookJoinDao dao = getContext().getBean("bookJoinDao", BookJoinDao.class);
		User user = dao.findJoinBooks1("ea09d9b1-ede7-4bd8-b43d-a546680df00b");
		System.out.println("该用户信息如下");
		System.out.println(user.getCn_user_name());
		System.out.println(user.getCn_user_password());
		System.out.println("包含的笔记本信息如下");
		for(NoteBook book : user.getBooks()){
			System.out.println(book.getCn_notebook_name());
		}
	}
	
	
}
