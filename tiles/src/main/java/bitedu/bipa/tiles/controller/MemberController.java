package bitedu.bipa.tiles.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.tiles.service.MemberService;
import bitedu.bipa.tiles.vo.User;

@Controller
@RequestMapping("member")
public class MemberController {
	
	private final MemberService service;
	
	@Autowired
	public MemberController(MemberService service) {
		this.service = service;
	}

	@RequestMapping("/list.do")
	public ModelAndView getList() {
		ModelAndView mav = new ModelAndView();
		List<User> list =  service.showList();
		mav.addObject("list",list);
		mav.setViewName("/member/list");
		return mav;
	}
	
	
	@RequestMapping("/detail.do")
	public ModelAndView getDetailView(@RequestParam("userId") String userId) {
		ModelAndView mav = new ModelAndView();
		User user = service.showDetail(userId);
		mav.addObject("user",user);
		mav.setViewName("/member/detail");
		return mav;
	}
	
	@RequestMapping("/viewRegist.do")
	public ModelAndView viewRegist() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/member/join");
		return mav;
	}
	
	@RequestMapping("/join.do")
	public ModelAndView regist(@ModelAttribute User user) {
		ModelAndView mav = new ModelAndView();
		boolean flag = service.registUser(user);
		String url = "redirect:viewRegist.do";
		if(flag) {
			url = "main";
		}
		mav.setViewName(url);
		return mav;
	}

	
	
	@RequestMapping("/viewLogin.do")
	public ModelAndView viewLogin() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/member/loginForm");
		return mav;
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		//System.out.println("logout");
		session.invalidate();
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value = "/login.do", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute User user ,HttpSession session) {
		System.out.println(user);
		ModelAndView mav = new ModelAndView();
		String url = "redirect:viewLogin.do";
		boolean flag = service.confirmUser(user);
		System.out.println("flag:" + flag);
		if(flag) {
			url = "main";
			session.setAttribute("user", user);
		}
		mav.setViewName(url);
		return mav;
	}
}
