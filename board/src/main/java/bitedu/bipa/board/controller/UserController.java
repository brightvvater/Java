package bitedu.bipa.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.board.service.UserService;
import bitedu.bipa.board.vo.UserVO;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.POST, value = "/login.do")
	public ModelAndView login(@ModelAttribute UserVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		if(service.login(vo)) {
			System.out.println("성공");
			mav.addObject("flag", service.login(vo));
			mav.setViewName("redirect:/layer/list.do");
			session.setAttribute("userId",vo.getUserId());
		}else {
			System.out.println("실패");
			mav.setViewName("./member/login");
			mav.addObject("flag", service.login(vo));
		}
		return mav;
	}

}
