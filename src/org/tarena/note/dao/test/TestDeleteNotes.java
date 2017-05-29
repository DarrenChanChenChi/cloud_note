package org.tarena.note.dao.test;

import org.junit.Test;
import org.tarena.note.dao.NoteDao;
import org.tarena.note.util.SpringTest;

public class TestDeleteNotes extends SpringTest {
	@Test
	public void test1(){
		NoteDao dao = getContext().getBean("noteDao", NoteDao.class);
		String[] ars = {"fed920a0-573c-46c8-ae4e-368397846efd","ebd65da6-3f90-45f9-b045-782928a5e2c0"};
		int rows = dao.deleteNotes(ars);
		System.out.println(rows);
	}
}
