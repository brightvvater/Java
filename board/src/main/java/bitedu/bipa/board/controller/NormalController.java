package bitedu.bipa.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("board")
@RequestMapping("/normal")
public class NormalController {
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView listBoard() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("./board/index");
		
		return mav;
	}
	

}
