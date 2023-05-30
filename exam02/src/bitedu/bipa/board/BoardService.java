package bitedu.bipa.board;

import java.util.Scanner;

public class BoardService {
	
	private BoardDao boardDao;
	
	private Scanner scanner = new Scanner(System.in);
	
	private BoardDTO board = new BoardDTO();
	
	public BoardService() {
		boardDao = new BoardDao();
	}

	// Method
	public void list() {
		// 타이틀 및 컬럼명 출력
		System.out.println();
		System.out.println("[게시물 목록]");
		System.out.println("-----------------------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "date", "title");
		System.out.println("-----------------------------------------------------------------------");
		
		boardDao.list();
		  
		// 메인 메뉴 출력
		BoardMain boardMain = new BoardMain();
		boardMain.mainMenu();
	}

	public void create() {
		System.out.println("[새 게시물 입력]"); 
		System.out.print("제목: ");
		board.setBtitle(scanner.nextLine()); 
		System.out.print("내용: ");
		board.setBcontent(scanner.nextLine()); 
		System.out.print("글쓴이: ");
		board.setBwriter(scanner.nextLine());
		  
		//보조메뉴 출력 System.out.println(
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("보조메뉴: 1.Ok | 2.Cancel"); 
		System.out.print("메뉴선택: ");
		String menuNo = scanner.nextLine(); 
		if(menuNo.equals("1")) { 
			//boards 테이블에 게시물 정보 저장 
			boardDao.create(board);
		
		}
	}

	public void read() {
		System.out.print("bno: "); 
		int bno = Integer.parseInt(scanner.nextLine());
		  
		  ///boards 테이블에서 해당 게시물을 가져와 출력
		
		  BoardDTO board = boardDao.read(bno);
		  System.out.println("#############");
		  System.out.println("번호: " + board.getBno()); 
		  System.out.println("제목: " +board.getBtitle()); 
		  System.out.println("내용: " + board.getBcontent());
		  System.out.println("쓴이: " + board.getBwriter()); 
		  System.out.println("날짜: " + board.getBdate()); 
		  //보조메뉴 출력 
		  System.out.println("-------------------------------------------------------------------");
		  System.out.println("보조메뉴: 1.Update | 2.Delete | 3.List");
		  System.out.print("메뉴선택: "); String menuNo = scanner.nextLine();
		  System.out.println();
		  
		  
		  if(menuNo.equals("1")) {
			  this.update(board); 
		  } 
		  else if(menuNo.equals("2")) {
			  this.delete(board); 
		  }else {
			  this.list();
		  }
		  exit();
		  
	}
		  
    public void update(BoardDTO board) {
      System.out.println("[수정 내용 입력]"); 
      System.out.print("제목: ");
   	  board.setBtitle(scanner.nextLine());
   	  System.out.print("내용: ");
   	  board.setBcontent(scanner.nextLine()); 
   	  System.out.print("글쓴이: ");
   	  board.setBwriter(scanner.nextLine());
   	  
   	  //보조메뉴 출력 
   	  System.out.println("-------------------------------------------------------------------");
   	  System.out.println("보조메뉴: 1.Ok | 2.Cancel"); 
   	  System.out.print("메뉴선택: ");
   	  String menuNo = scanner.nextLine(); 
   	  if(menuNo.equals("1")) { 
   		  //boards 테이블에서게시물 정보 수정 
   		 boardDao.update(board);
   	  }
    }
    
    public void delete(BoardDTO board) {
    	 //boards 테이블에 게시물 정보 삭제 
    	boardDao.delete(board);
    }

	public void clear() {
		System.out.println("[게시물 전체 삭제]"); 
		System.out.println("-------------------------------------------------------------------");
		System.out.println("보조메뉴: 1.Ok | 2.Cancel"); 
		System.out.print("메뉴선택: ");
		String menuNo = scanner.nextLine(); 
		if(menuNo.equals("1")) { 
			//boards 테이블에 게시물 정보 전체 삭제 
			boardDao.clear();
		}
		
	}

	public void exit() {
	
		System.out.println("** 게시판 종료 **"); 
		System.exit(0); 
	}
	

}
