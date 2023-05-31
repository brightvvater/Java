package bitedu.bipa.board;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherBoardService {
	
	private TeacherBoardDao dao;
	
	 public TeacherBoardService() {
		this.dao = new TeacherBoardDao();
	}
	
	public void registerItem(BoardDTO item) {
		try {
			dao.insert(item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BoardDTO readItem(int bno) {
		BoardDTO item = null;
		try {
			item = dao.select(bno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
		
	}
	
	public boolean removeAll() {
		boolean flag = false;
		String sql = "delete from boards";
		try {
			flag = dao.delete(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public ArrayList<BoardDTO> readAll() {
		ArrayList<BoardDTO> list = null;
		try {
			list = dao.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean modify(BoardDTO item) {
		boolean flag = false;
		try {
			flag = dao.update(item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean remove(int bno) {
		boolean flag = false;
		String sql ="delete from boards where bno =?";
	
		try {
			flag = dao.delete(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
