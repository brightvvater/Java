package bitedu.bipa.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.board.service.BoardLayerService2;
import bitedu.bipa.board.vo.BoardVO;
import bitedu.bipa.board.vo.ReplyVO;


@Controller("layerController")
@RequestMapping("/layer")
public class LayerContrtoller {
	
	@Autowired
	private BoardLayerService2 service;
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView listBoard() {
		ModelAndView mav = new ModelAndView();
		
		List<BoardVO> list =  service.showList();
		System.out.println(list);
		mav.addObject("list", list);
		mav.setViewName("./layer/index");
		return mav;
	}
	
	@RequestMapping(value = "/regist.do", method = RequestMethod.GET)
	public ModelAndView viewRegist() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("./layer/regist");
		return mav;
	}
	
	
	@RequestMapping(value = "/regist.do", method = RequestMethod.POST)
	public ModelAndView regist(@ModelAttribute("board") BoardVO vo) {
		ModelAndView mav = new ModelAndView();
		vo.setUserId("user1");
		boolean flag = service.registBoard(vo);
		mav.setViewName("redirect:list.do");
		return mav;
	}
	
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public ModelAndView viewDetail(@RequestParam("boardSeq")int boardSeq) {
		ModelAndView mav = new ModelAndView();
		BoardVO vo =  service.showDetail(boardSeq);
		List<ReplyVO> list  = service.readReply(boardSeq);
		mav.addObject("board", vo);
		mav.addObject("replyList", list);
		
		mav.setViewName("./layer/detail");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/reply", method = RequestMethod.POST, produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> registReply(@RequestBody ReplyVO reply) {
		//System.out.println(reply);
		service.registReply(reply);
		ReplyVO vo =  service.readRepl();
		System.out.println(vo);
		return new ResponseEntity<ReplyVO>(vo, HttpStatus.OK);
		
	}
	
	

	

}
