package bitedu.bipa.librarysystem;

import java.time.LocalDate;

public class RentalHistory {
	
	private int historyNo;
	
	private int bookNo;
	
	private int userNo;
	
	private LocalDate rentalDate;
	
	private LocalDate returnDate;
	
	private LocalDate expectedReturnDate;
	
	public RentalHistory() {}
	
	

	public RentalHistory(int historyNo, int bookNo, int userNo, LocalDate rentalDate, LocalDate returnDate,
			LocalDate expectedReturnDate) {
		super();
		this.historyNo = historyNo;
		this.bookNo = bookNo;
		this.userNo = userNo;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.expectedReturnDate = expectedReturnDate;
	}



	public int getHistoryNo() {
		return historyNo;
	}

	public void setHistoryNo(int historyNo) {
		this.historyNo = historyNo;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDate getExpectedReturnDate() {
		return expectedReturnDate;
	}

	public void setExpectedReturnDate(LocalDate expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	
	
	

}
