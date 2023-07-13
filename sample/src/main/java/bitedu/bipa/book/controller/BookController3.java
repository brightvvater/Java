package bitedu.bipa.book.controller;


import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.book.service.BlmService;
import bitedu.bipa.book.service.BlmService2;
import bitedu.bipa.book.utils.PageDTO;
import bitedu.bipa.book.vo.BookCopy;

@Controller("bookController3")
@RequestMapping("mybatis")
public class BookController3 {

	@Autowired
	private BlmService2 service;
	
	public BookController3() {
		
	}
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String page = request.getParameter("page");
		page = page == null? "1": page;
		//PageDTO<BookCopy> list = service.serchBookWithPaging(Integer.parseInt(page));
		List<BookCopy> list = service.searchBookAll();
		mav.addObject("list", list);
		mav.setViewName("./manager/book_list");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/view_regist.do", method = RequestMethod.GET)
	public ModelAndView viewRegist() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("./manager/book_regist");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/regist.do", method = RequestMethod.POST)
	public ModelAndView regist(@ModelAttribute("book") BookCopy copy) {
		ModelAndView mav = new ModelAndView();
		
		service.registBook(copy);
		mav.setViewName("redirect:list.do");
		
		return mav;
	}
	
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public ModelAndView viewDetail(HttpServletRequest request) {
		String bookSeq = request.getParameter("bookSeq");
		ModelAndView mav = new ModelAndView();
		BookCopy copy = service.bookDetail(bookSeq);
		mav.addObject("book",copy);
		mav.setViewName("./manager/book_detail");
		
		return mav;
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public ModelAndView viewUpdate(@ModelAttribute("book")BookCopy book) {
		ModelAndView mav = new ModelAndView();
		int bookSeq = book.getBookSeq();
		BookCopy copy = service.bookDetail(String.valueOf(bookSeq));
		mav.addObject("book",copy);
		mav.setViewName("./manager/book_update");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public ModelAndView updateBook(@ModelAttribute("book")BookCopy book) {
		ModelAndView mav = new ModelAndView();
		service.bookModify(book);
		mav.setViewName("redirect:list.do");
		
		return mav;
	}
	
	@RequestMapping(value = "/remove.do", method = RequestMethod.GET)
	public ModelAndView removeBook(HttpServletRequest request) {
		String bookSeq = request.getParameter("bookSeq");
		ModelAndView mav = new ModelAndView();
		service.removeBook(bookSeq);
		mav.setViewName("redirect:list.do");
		
		return mav;
	}
	
}
