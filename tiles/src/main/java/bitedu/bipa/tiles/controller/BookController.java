package bitedu.bipa.tiles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.tiles.service.BookService;
import bitedu.bipa.tiles.vo.BookCopy;
import bitedu.bipa.tiles.vo.User;

@Controller
@RequestMapping("book")
public class BookController {
	
	private final BookService service;
	
	@Autowired
	public BookController(BookService service) {
		this.service = service;
	}
	
	@RequestMapping("/list.do")
	public ModelAndView getList() {
		ModelAndView mav = new ModelAndView();
		List<BookCopy> list =  service.showList();
		mav.addObject("list", list);
		mav.setViewName("/book/list");
		return mav;
	}

}
