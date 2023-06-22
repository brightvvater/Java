package bitedu.bipa.exception;

public class RentedBookException extends RuntimeException{
	
	public static final String MESSAGE = "해당 도서는 대출중입니다.";
	
	public RentedBookException() {
		super(MESSAGE);
	}

}
