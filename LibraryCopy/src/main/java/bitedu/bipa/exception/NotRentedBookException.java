package bitedu.bipa.exception;

public class NotRentedBookException extends RuntimeException{
	
	public static final String MESSAGE = "미납된 도서가 있습니다.";
	
	public NotRentedBookException() {
		super(MESSAGE);
	}

}
