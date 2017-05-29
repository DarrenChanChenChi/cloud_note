package org.tarena.note.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.util.NoteResult;

@Controller
@RequestMapping("/notebook")
public class BookListController {
	@Resource
	private NoteBookService service;
	
	@RequestMapping("/list.do")
	public String execute(Model model){
		NoteResult result = service.loadBooks();
		//放入model传递到book_list.jsp
		model.addAttribute("result",result);
		return "book_list";//找book_list.jsp
	}
}
