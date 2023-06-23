package bitedu.bipa.exception;

public class RentFailedException extends RuntimeException{
	
	public static final String MESSAGE = "대출권수를 초과했습니다.";
	
	public RentFailedException() {
		super(MESSAGE);
	}

}
