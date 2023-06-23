package bitedu.bipa.exception;

public class ServiceStopException extends RuntimeException{
	
	public static final String MESSAGE = "연체일자만큼 대출 불가합니다.";
	
	public ServiceStopException() {
		super(MESSAGE);
	}

}
