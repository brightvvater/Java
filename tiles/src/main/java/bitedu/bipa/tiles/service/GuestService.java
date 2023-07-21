package bitedu.bipa.tiles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.tiles.dao.GuestDao;
import bitedu.bipa.tiles.vo.Guest;

@Service
public class GuestService {
	
	private GuestDao dao;
	
	@Autowired
	public GuestService(GuestDao dao) {
		this.dao = dao;
	}

	public List<Guest> showList() {
		return dao.selectList();
	}
}
