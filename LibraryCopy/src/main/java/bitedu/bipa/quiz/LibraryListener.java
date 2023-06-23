package bitedu.bipa.quiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bitedu.bipa.quiz.dao.LibraryDAO;
import bitedu.bipa.quiz.service.LibraryBookService;
import bitedu.bipa.quiz.vo.BookUseStatusVO;
import bitedu.bipa.quiz.vo.UserBookStatusVO;

public class LibraryListener extends HttpServlet {
	
	private LibraryBookService service;
	
	public LibraryListener() {
		service = new LibraryBookService();
	} 
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("name:" +req.getParameter("name"));
		String name = req.getParameter("name");
		String bookSeq = req.getParameter("bookSeq");
		
		
		if(bookSeq !=null) {
			//System.out.println("a: "+ bookSeq);
			List<Integer> bookSeqList = new ArrayList<Integer>();
			String[] books = bookSeq.split(",");
			if(books.length==0) {
				throw new RuntimeException("반납할 책을 선택해주세요");
			}
			for(String book: books) {
				try {
					int bookNum = Integer.parseInt(book);
					bookSeqList.add(bookNum);
				}catch(NumberFormatException e) {
					throw new RuntimeException("숫자를 입력해주세요.");
				}
							
			}
			for(int i=0;i<bookSeqList.size();i++) {
				service.returnBook(name, bookSeqList.get(i));
			}
		}
		
		
		String data = this.getData(name);
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(data);
		out.close();
		
		//System.out.println(id);
		
		
	}
	
	public String getData(String userId) {
		String result = null;
		LibraryBookService lbs = new LibraryBookService();
		try {
			HashMap<String, Object> data = lbs.getUserStatus(userId, "6"); 
			HashMap<String,UserBookStatusVO>  map1 = (HashMap<String,UserBookStatusVO>)data.get("userInfo");
			HashMap<String,ArrayList<BookUseStatusVO>>  map2 = (HashMap<String,ArrayList<BookUseStatusVO>>)data.get("bookInfo");
			
			UserBookStatusVO status = map1.get("user");
			ArrayList<BookUseStatusVO> list = map2.get("total");
			ArrayList<BookUseStatusVO> allReturned = map2.get("allReturned");
			ArrayList<BookUseStatusVO> notReturned = map2.get("notReturned");
			ArrayList<BookUseStatusVO> expectingReturn = map2.get("expectingReturn");
			

			JSONObject json = new JSONObject();
			JSONObject info = new JSONObject();
			JSONObject user = new JSONObject();
			JSONObject book = new JSONObject();
			
			JSONArray array1 = new JSONArray();
			JSONArray array2 = new JSONArray();
			JSONArray array3 = new JSONArray();
			JSONArray array4 = new JSONArray();
			
			JSONArray array5 = new JSONArray();
			array5.add(status);
			
			user.put("user", array5);
			
			for(BookUseStatusVO vo : list) {
				array1.add(vo);
			}
			book.put("list", array1);
			
			for(BookUseStatusVO vo : allReturned) {
				array2.add(vo);
			}
			book.put("returned", array2);
			
			for(BookUseStatusVO vo : notReturned) {
				array3.add(vo);
			}
			book.put("notReturned", array3);
			
			for(BookUseStatusVO vo : expectingReturn) {
				array4.add(vo);
				//System.out.println(vo);
			}
			book.put("expectingReturn", array4);
			
			
			info.put("userInfo", user);
			info.put("bookInfo", book);
			
			json.put("data", info);

			//System.out.println(json.toJSONString());
			
			result = json.toJSONString();
		}catch (RuntimeException e) {
			System.out.println("해당 유저는 대출 내역이 존재하지 않습니다.");
		}
		
		
		return result;
	}
}
