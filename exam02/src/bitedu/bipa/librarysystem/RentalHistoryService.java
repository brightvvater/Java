package bitedu.bipa.librarysystem;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RentalHistoryService {
	
	private Scanner sc = new Scanner(System.in);
	
	private RentalHistoryDAO dao;
	
	public RentalHistoryService() {
		this.dao = new RentalHistoryDAO();
	}
	
	//해당 userNo의 대출내역 불러오기
	public void findRentalHistoriesByUserNo(int userNo) {
		try {
			List<ReturnDTO> list =  dao.selectRentalHistories(userNo);
			
			System.out.println("대출번호\t작가\t대출일자\t\t반납예정일");
			if(list.size() ==0) {
				System.out.println("==========================================");
				System.out.println("대출한 도서내역이 없습니다.");
			}else  {
				System.out.println("==========================================");
				System.out.println(list);
				
				
				System.out.println("==========================================");
				System.out.print("반납할 도서의 번호를 선택하세요.>>");
				int historyNo = sc.nextInt();
				
				for(ReturnDTO dto: list) {
					if(historyNo == dto.getHistoryNo()) {
						returnBook(userNo, historyNo, dto.getBookNo());
					}
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	//대출반납하기(대출이력에 반납일 추가하기, 연체여부 확인하기, 대출 도서에 대출여부 변경하기)
	public void returnBook(int userNo, int historyNo, int bookNo) {
		System.out.print("정말로 반납하시겠습니까? y/n>>");
		String answer = sc.next();
		if(answer.equals("y")) {
			try {
				//데이터 업데이트하기
				boolean flag = dao.updateRentalHistories(historyNo,bookNo);
				
				if(flag==false) {
					System.out.println("반납 실패!!");
					findRentalHistoriesByUserNo(userNo);
				}else if(flag ==true) {
					//연체일자 알려주기
					int count = dao.checkOverDue(historyNo);
					if(count>14) {
						System.out.println("연체일자:" + (count-14));
						System.out.println("반납 완료되었습니다. 연체일자만큼 대출 불가합니다!!");
						System.out.println();
						System.out.println("==========================================");
						findRentalHistoriesByUserNo(userNo);
					}else {
						System.out.println("반납 완료되었습니다.");
						System.out.println();
						System.out.println("==========================================");
						findRentalHistoriesByUserNo(userNo);
					}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println();
			System.out.println("==========================================");
			findRentalHistoriesByUserNo(userNo);
		}
		
	}

}
