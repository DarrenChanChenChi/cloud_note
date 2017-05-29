package org.tarena.note.dao.test;

import java.util.List;

import org.junit.Test;
import org.tarena.note.dao.NoteDao;
import org.tarena.note.entity.Note;
import org.tarena.note.util.SpringTest;

public class TestNoteDao extends SpringTest {
	@Test
	public void test1(){
		NoteDao dao = getContext().getBean("noteDao", NoteDao.class);
		List<Note> list = dao.findByBookId("fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		for (Note note : list) {
			System.out.println(note);
		}
	}
	
	@Test
	public void test2(){
		NoteDao dao = getContext().getBean("noteDao", NoteDao.class);
		Note note = dao.findById("ebd65da6-3f90-45f9-b045-782928a5e2c0");
		System.out.println(note);
	}
	
	
}
