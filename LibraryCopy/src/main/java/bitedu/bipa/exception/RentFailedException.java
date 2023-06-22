package bitedu.bipa.exception;

public class RentFailedException extends RuntimeException{
	
	public static final String MESSAGE = "대출권수또는 미납도서를 확인하세요.";
	
	public RentFailedException() {
		super(MESSAGE);
	}

}
