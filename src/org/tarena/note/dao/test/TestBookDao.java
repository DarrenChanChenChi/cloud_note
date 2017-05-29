package org.tarena.note.dao.test;

import java.util.List;

import org.junit.Test;
import org.tarena.note.dao.NoteBookDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.util.SpringTest;

public class TestBookDao extends SpringTest {
	@Test
	public void test1(){
		NoteBookDao dao = getContext().getBean("noteBookDao", NoteBookDao.class);
		List<NoteBook> list = dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		for (NoteBook noteBook : list) {
			System.out.println(noteBook);
		}
	}
}
