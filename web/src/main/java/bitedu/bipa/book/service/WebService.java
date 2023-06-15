package bitedu.bipa.book.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bitedu.bipa.book.dao.WebDao;
import bitedu.bipa.book.dto.BookDTO;

public class WebService {
	
	public static void main(String[] args) {
		WebService service = new WebService();
		List<BookDTO> list =  service.selectBookList();
		
		JSONObject obj;
		JSONArray jlist = new JSONArray();
		
		for(int i=0;i<list.size();i++) {
			BookDTO book = list.get(i);
			obj = new JSONObject();
			obj.put("title",book.getTitle());
			obj.put("category", book.getCategory());
			jlist.add(obj);
			//System.out.println(jlist.get(i));
		}
		System.out.println(jlist);
		
		

		//System.out.println(obj.toJSONString());
		try {
			FileWriter fw = new FileWriter("C:/Users/금정산2PC06/eclipse-workspace/web/src/main/webapp/jsonfile.json");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(jlist.toJSONString());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	private WebDao dao;
	
	public WebService() {
		dao = new WebDao();
	}
	
	public List<BookDTO> selectBookList() {
		List<BookDTO> list = new ArrayList();
		try {
			 list =  dao.selectBookList();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
