package bitedu.bipa.board;

import java.util.Scanner;

public class BoardMain {
	
	private BoardService boardService;

	
	public BoardMain() {
		boardService = new BoardService();
	}

	public static void main(String[] args) {
		
		BoardMain boardMain = new BoardMain();
		boardMain.boardService.list();
	}
	
	public void mainMenu() {
		
		Scanner scanner = new Scanner(System.in);
		boolean bool = true;
		while(bool) {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("메인메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
		System.out.print("메뉴선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();
		
		switch(menuNo) {
			case "1" -> boardService.create();
			case "2" -> boardService.read(); 
			case "3" -> boardService.clear();
			case "4" -> boardService.exit();
		}
		}
	}	
	

}
