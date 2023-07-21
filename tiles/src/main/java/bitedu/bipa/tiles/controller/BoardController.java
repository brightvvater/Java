package bitedu.bipa.tiles.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		System.out.println("list");
		ModelAndView mav = new ModelAndView();
		List<Guest> list =  service.showList();
		
		mav.addObject("list",list);
		mav.setViewName("/board/list");
		return mav;
	}
	
	@RequestMapping("/search.do")
	public ModelAndView searchList(@RequestParam(required =false) String selectBox, @RequestParam(required = false)String searchBox ) {
		
		System.out.println("searchList");
		ModelAndView mav = new ModelAndView();
//		if(selectBox.equals("")) {
//			list =  service.showList();
//		}else {
//			list =  service.showListWithSearch(selectBox, searchBox);
//		}
		selectBox = selectBox == "" ? "title" : selectBox;
		List<Guest> list =  service.showListWithSearch(selectBox, searchBox);
		
		System.out.println("검색");
		mav.addObject("list",list);
		mav.setViewName("/board/list");
		return mav;
	}
	
	@RequestMapping(value = "/regist.do", method = RequestMethod.GET)
	public ModelAndView viewRegist() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/board/regist");
		return mav;
	}
	
	@RequestMapping(value = "/regist.do", method = RequestMethod.POST)
	public ModelAndView regist(@ModelAttribute Guest guest) {
		System.out.println(guest);
		ModelAndView mav = new ModelAndView();
		boolean flag = service.registGuestBook(guest);
		System.out.println(flag);
		String url = "/board/regist";
		if(flag) {
			url = "redirect:list.do";
		}
		mav.setViewName(url);
		return mav;
	}
	
	
	@RequestMapping(value="/upload.do", method=RequestMethod.POST)
	public ModelAndView upload(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		System.out.println("upload");
		String path = "d:\\dev\\upload_files";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024*1024*10);
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Guest guest = null;
		try {
			guest = service.upload(items);
			System.out.println(guest);
			service.registGuestBook(guest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("redirect:list.do");
		
		return mav;
	}
	
	
	@RequestMapping(value="/download.do", method = RequestMethod.GET)
	public void download(@RequestParam("fileName") String fileName,HttpServletResponse resp) {
		File downloadFile = new File("d:\\dev\\upload_files\\images\\"+fileName);
		
		try {
			fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setContentType("text/html; charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.addHeader("Content-Disposition", "attatchment;filename="+fileName);
		
		try {
			FileInputStream fis = new FileInputStream(downloadFile);
			OutputStream os = resp.getOutputStream();
			byte[] buffer = new byte[256];
			int length = 0;
			while((length=fis.read(buffer))!=-1) {
				os.write(buffer, 0, length);
			}
			os.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
