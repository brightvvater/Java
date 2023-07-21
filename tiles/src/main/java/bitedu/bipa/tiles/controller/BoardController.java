package bitedu.bipa.tiles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.tiles.service.GuestService;
import bitedu.bipa.tiles.vo.Guest;

@Controller
@RequestMapping("guestbook")
public class BoardController {
	
	private GuestService service;
	
	@Autowired
	public BoardController(GuestService service) {
		this.service = service;
	}
	
	@RequestMapping("/list.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		List<Guest> list =  service.showList();
		
		mav.addObject("list",list);
		mav.setViewName("/board/list");
		return mav;
	}
	
	@RequestMapping("/search.do")
	public ModelAndView searchList(@RequestParam("selectBox") String selectBox, @RequestParam("searchBox")String searchBox ) {
		ModelAndView mav = new ModelAndView();
		List<Guest> list =  service.showList();
		System.out.println("검색");
		mav.addObject("list",list);
		mav.setViewName("/board/list");
		return mav;
	}

}
