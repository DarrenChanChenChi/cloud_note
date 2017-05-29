package org.tarena.note.service.test;

import junit.framework.Assert;

import org.junit.Test;
import org.tarena.note.service.NoteService;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteResult;
import org.tarena.note.util.SpringTest;

public class TestUserService extends SpringTest {
	@Test
	public void testcheckLogin(){
		UserService service = getContext().
				getBean("userServiceImpl", UserService.class);
		System.out.println(service.getClass().getName());
		NoteResult result = service.checkLogin("scott", "1234");
		//System.out.println(result.getStatus()+","+result.getMsg());
		//断言(预期结果，实际结果)
		Assert.assertEquals(1, result.getStatus());
		Assert.assertEquals("用户不存在", result.getMsg());
	}
	
	@Test
	public void testcheckLogin1(){
		UserService service = getContext().
				getBean("userServiceImpl", UserService.class);
		NoteResult result = service.checkLogin("scott", "1234");
		//System.out.println(result.getStatus()+","+result.getMsg());
		//断言(预期结果，实际结果)
		Assert.assertNull(result.getData());
	}
	
	@Test
	public void test3(){
		NoteService service = getContext().getBean("noteServiceImpl", NoteService.class);
		NoteResult result = service.loadNote("326f17e8-ff04-4a79-b33b-ae4bb3bb5f1b");
		System.out.println(result);
	}
	
	@Test
	public void test4(){
		NoteService service = getContext().getBean("noteServiceImpl", NoteService.class);
		NoteResult result = service.modifyNote("qq", "qq", "2085fed9-6040-414d-903d-b2a0ff869026");
		System.out.println(result);
	}
	
}
