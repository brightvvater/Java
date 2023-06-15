package bitedu.bipa.book.service;




import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import bitedu.bipa.book.dao.BookDAO;
import bitedu.bipa.book.dao.RentalHistoryDAO;
import bitedu.bipa.book.dto.BookDTO;
import bitedu.bipa.book.exception.CategoryNotFoundException;


public class BookService {
    private final Scanner scanner;
    private final RentalHistoryDAO rentalHistoryDAO;
    private final BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
        this.rentalHistoryDAO = new RentalHistoryDAO();
        this.scanner = new Scanner(System.in);
    }

    public void findCategories() {
        boolean resultFlag = true;
        while (resultFlag) {
            try {
                List<String> categories = bookDAO.selectCategories();
                printCategoryList(categories);

                System.out.println("검색할 카테고리명 입력 (로그아웃 : 0): ");
                String checkCategoryName = scanner.next();

                if (checkCategoryName.equals("0")) {
                    System.out.println("로그아웃 합니다.");
                    resultFlag = false;
                } else {
                    String categoryInfo = findCategoryByName(categories, checkCategoryName);

                    List<BookDTO> books = findBooksByCategory(categoryInfo);
                    resultFlag = searchBook(books);
                }
            } catch (CategoryNotFoundException | SQLException e) {
                System.err.println("에러 : " + e.getMessage());
            }
        }
    }

    private void printCategoryList(List<String> categories) {
        System.out.println("도서 카테고리 목록:");
        for (String category : categories) {
            System.out.println(category);
        }
    }

    private String findCategoryByName(List<String> categories, String categoryName) throws CategoryNotFoundException {
        return categories.stream()
            .filter(name -> name.equals(categoryName))
            .findFirst()
            .orElseThrow(CategoryNotFoundException::new);
    }

    public List<BookDTO> findBooksByCategory(String category) throws SQLException {
        List<BookDTO> list = bookDAO.selectSearchList(0, category);
        printBookList(list);
        return list;
    }

    private void printBookList(List<BookDTO> books) throws SQLException {
        System.out.println("[도서 목록]");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.printf("%-6s%-30s%-20s%n", "no", "제목", "저자");
        System.out.println("-----------------------------------------------------------------------------------------------");
        if (books.size() > 0) {
            for (int i = 1; i <= books.size(); i++) {
                BookDTO book = books.get(i - 1);
                System.out.printf("%-6s%-30s%-20s%n",
                    i,
                    truncateString(book.getTitle(), 20),
                    truncateString(book.getAuthor(), 10)
                );
            }
            System.out.println();
        } else {
            System.out.println("조회된 도서가 없습니다.");
        }
    }

    private String truncateString(String str, int maxLength) {
        if (str.length() >= maxLength) {
            return str.substring(0, maxLength - 3) + "...";
        }
        return str;
    }

    public void searchType() throws SQLException {
        boolean flagResult = true;
        while (flagResult) {
            System.out.println();
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("검색 조건: 1.제목 | 2.내용 | 3.저자 | 4.대출 가능 도서 ");
            System.out.print("검색 조건 선택: ");
            int menuNo = scanner.nextInt();
            String word = null;

            scanner.nextLine();
        /*
        TODO 4: maic number 의 사용은 지양합니다 ~ (혜윤)
         */
            if (menuNo != 4) {
                System.out.println();
                System.out.print("검색어: ");
                word = scanner.nextLine();
            } else {
                word = "F";
            }

            List<BookDTO> list = bookDAO.selectSearchList(menuNo, word);
            printBookList(list);
            flagResult = searchBook(list);
        }
    }

    private boolean searchBook(List<BookDTO> books) throws SQLException {
        System.out.print("검색할 책 번호 입력: ");
        int bookNo = scanner.nextInt();

        if (bookNo > 0 && bookNo <= books.size()) {
        	BookDTO bookDTO = bookDAO.selectBookByBookNo(books.get(bookNo-1).getBookNo());//기존 : bookNo 수정
            System.out.println(bookDTO.toString());
            System.out.println("0. 나가기");
            System.out.println("1. 대여하기");
            System.out.println("2. 예약하기");
            System.out.print("사용자 입력: ");

            int rentNo = scanner.nextInt();
            switch (rentNo) {
                case 0:
                    System.out.println("나갑니다.");
                    break;
                case 1:
                    return rentalHistoryDAO.rentBook(bookNo);
                case 2:
                    System.out.println("예약은 안만듭니다.");
                    break;
                default:
                    break;
            }
        }
        return false;
    }
}
