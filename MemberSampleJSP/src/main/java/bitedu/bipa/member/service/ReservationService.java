package bitedu.bipa.member.service;

import java.sql.Timestamp;
import java.util.Calendar;

import bitedu.bipa.member.dao.ReservationDao;
import bitedu.bipa.member.vo.BookReserveStatusVO;
import bitedu.bipa.member.vo.BookUser;

public class ReservationService {
	
	private ReservationDao dao;
	
	public ReservationService() {
		this.dao = new ReservationDao();
	}
	
	public static void main(String[] args) {
		ReservationService service = new ReservationService();
		service.reserveBook("user1", 4);
	}
 
	//책 예약하기
	public boolean reserveBook(String userId, int bookSeq) {
		boolean flag = true;
		//user가 연체일자가 없는지 확인
		//user의 예약권수가 3권이 넘지 않는지 확인
		BookUser user =  dao.selectUserServiceStopAndReserveCount(userId);
		if(user.getServiceStop() !=null || user.getReserveBook()<=0) {
			return false;
		}
		
		
		//book이 대출된 책인지 한번 더 확인(이건 jas에서 검사 통해 예약 버튼 안나오게 할 예정임)
		String bookStatus = dao.selectBookStatus(bookSeq);
		//System.out.println(bookStatus);
		if(bookStatus.equals("BM-0001")) {
			return false;
		}
		
		//book이 예약 가능한지 확인(예약한 사람이 두명 이상이 아닌지)
		int reserveCount = dao.selectReserveCount(bookSeq);
		//System.out.println(reserveCount);
		if(reserveCount >=2) {
			return false;
		}
		
		
		if(flag) {
			//예약하기
			Calendar c = Calendar.getInstance();
			c.set(2023, 06, 04);
			BookReserveStatusVO vo = new BookReserveStatusVO(bookSeq, userId, new Timestamp(c.getTimeInMillis()));
			
			//예약 데이터 넣기
			int affectedCount = dao.insertReserveStatus(vo);
			
			//user 예약 가능권수 1권 줄이기
			boolean isReduced = dao.reduceReserveBookCount(userId);
			
			if(affectedCount<=0 || !isReduced) {
				flag = false;
			}
		}
		
		return flag;
		
	}

	
	//1.예약 취소하기(user가 직접 취소)
	//2.예약 가능일자가 14일 지나면 자동 예약 취소
	public void cancelReservation(String userId, int bookSeq) {
		
		
	}
	
	//책 반납 시 예약내역이 있는지 확인하고 알림 보내기(이건 반납 컨트롤러에서 반납 시 이 서비스 사용하게 만들어야 함) 
	
	
	
	
	
	
	
	

}
