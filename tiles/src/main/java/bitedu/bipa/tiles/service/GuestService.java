package bitedu.bipa.tiles.service;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.tiles.dao.GuestDao;
import bitedu.bipa.tiles.vo.Guest;

@Service
public class GuestService {
	
	private final GuestDao dao;
	
	@Autowired
	public GuestService(GuestDao dao) {
		this.dao = dao;
	}

	public List<Guest> showList() {
		return dao.selectList();
	}
	
	public List<Guest> showListWithSearch(String selectBox, String searchBox) {
		return dao.selectListWithSearch(selectBox, searchBox);
	}
	
	public boolean registGuestBook(Guest guest) {
		int affectedCount = dao.insertGuest(guest);
		
		if(affectedCount>0) {
			return true;
		}
		
		return false;
	}
	
	
	public Guest upload(List<FileItem> items) throws Exception {
		// TODO Auto-generated method stub
		Guest guest = null;
		guest = new Guest();
		for(FileItem item : items) {
			if(item!=null&item.isFormField()) {
				//일반적인 Form값 추출
				String fieldName = item.getFieldName();
				if(fieldName.equals("title")) {
					guest.setTitle(item.getString("UTF-8"));
				} else if(fieldName.equals("writer")) {
					guest.setWriter(item.getString("UTF-8"));
				} else if(fieldName.equals("content")) {
					guest.setContent(item.getString("UTF-8"));
				} 
			} else {
				// uplode할 데이터에 대한 정보 추출
				String fieldName = item.getFieldName();
				if(fieldName.equals("book_image")) {
					String temp = item.getName();
					System.out.println("book_image "+temp);
					int index = temp.lastIndexOf("\\");
					String fileName = temp.substring(index+1);
					guest.setBoardImage(fieldName);
					File uploadFile = new File("d:\\dev\\upload_files\\images\\"+fileName);
					item.write(uploadFile);
				}
			}
		}
		return guest;
	}
}
