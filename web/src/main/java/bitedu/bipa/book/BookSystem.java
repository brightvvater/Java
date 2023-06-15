package bitedu.bipa.book;

import java.sql.SQLException;
import java.util.Scanner;

import bitedu.bipa.book.dao.UserDao;
import bitedu.bipa.book.dto.UserDTO;
import bitedu.bipa.book.service.BookService;
import bitedu.bipa.book.service.RentalHistoryService;



/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class BookSystem {
    private static final Scanner scanner = new Scanner(System.in);
    public static ThreadLocal<Integer> THREAD_LOCAL_USER_NO = new ThreadLocal<>();
    private final BookService bookService;
    private final UserDao userDao;
    private final RentalHistoryService rentalHistoryService;

    public BookSystem() {
        bookService = new BookService();
        userDao = new UserDao();
        rentalHistoryService = new RentalHistoryService();
    }

    public static void main(String[] args) {
        BookSystem bs = new BookSystem();

        try {
            while (true) {
               bs.start();               // DONE : 메인 메뉴 선호출 후로그인, 선택적 로그인화(반납시 필수 로그인, 조회 후 대출시 로그인 필)
//                bs.login();
//                if (!bs.start()) {
//                    break;
//                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
    }

    public void login() {
        System.out.print("아이디를 입력하세요: ");
        int userNo = scanner.nextInt();
        UserDTO user = userDao.login(userNo);
        
        if(user!= null) {                     //DONE : 회원 정보안에 회원이 존재한다면 로그인
        THREAD_LOCAL_USER_NO.set(userNo);
        
        System.out.println("회원 로그인 되었습니다.");
        System.out.print("유저 이름: " + user.getUserNo() + "\t");
        System.out.print("유저 등급: " + user.getGradeCode() + "\t");
        System.out.println("연체 일자: " + user.getOverdueDate() + "\t");
        }else {
            System.out.println("회원이 아닙니다.");
           
        }
        
    }

    public boolean start() throws SQLException {
        System.out.println("---------------------------------------");
        System.out.println("1. 카테고리 조회" + "| \t" + "2. 검색" + "| \t" + "3. 반납" + "| \t" + "4. 로그인" + "| \t" + "5. 종료" + "\t");
        int menuNo = scanner.nextInt();
        UserDTO userdto = new UserDTO(0, 0, null);

        switch (menuNo) {
            case 1:
                bookService.findCategories();
                break;
            case 2:
                bookService.searchType();
                break;
            case 3:
               //DONE : 반납
               if(THREAD_LOCAL_USER_NO.get()==null) { // 회원 로그인 상태가 아니면 로그인 호출
                  login();

               }
              if(THREAD_LOCAL_USER_NO.get()!=null) { // 회원 로그인 상태면 바로 반납 호출
                  rentalHistoryService.findRentalHistoriesByUserNo(THREAD_LOCAL_USER_NO.get());
              }
                
                break;
            case 4:
               //DONE : 로그인 완성
               if(THREAD_LOCAL_USER_NO.get()==null) { // 회원 로그인 상태가 아니면 로그인 호출
                  login();
               }else {
                  System.out.println("이미 로그인이 되어있습니다.");
               }
                
                return true;
            case 5:
                return false;
            default:
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                break;
        }

        return true;
    }

    public void logout() {
        THREAD_LOCAL_USER_NO.remove();
        System.out.println("로그아웃 되었습니다.");
    }
}