package org.tarena.note.dao;

import java.util.List;

import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.User;

public interface BookJoinDao {
	public List<NoteBook> findJoinUser();
	public List<NoteBook> findJoinUser1();
	public User findJoinBooks(String userId);
	public User findJoinBooks1(String userId);
}
