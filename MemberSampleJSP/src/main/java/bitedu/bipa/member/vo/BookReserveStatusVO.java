package bitedu.bipa.member.vo;

import java.sql.Timestamp;

public class BookReserveStatusVO {
	
	private int bookSeq;
	private String userId;
	private Timestamp reserveDate;
	private Timestamp borrowPassDate;
	private Timestamp reserveCancelDate;
	
	public BookReserveStatusVO() {}

	public BookReserveStatusVO(int bookSeq, String userId, Timestamp reserveDate) {
		super();
		this.bookSeq = bookSeq;
		this.userId = userId;
		this.reserveDate = reserveDate;
	}

	public int getBookSeq() {
		return bookSeq;
	}

	public void setBookSeq(int bookSeq) {
		this.bookSeq = bookSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Timestamp reserveDate) {
		this.reserveDate = reserveDate;
	}

	public Timestamp getBorrowPassDate() {
		return borrowPassDate;
	}

	public void setBorrowPassDate(Timestamp borrowPassDate) {
		this.borrowPassDate = borrowPassDate;
	}

	public Timestamp getReserveCancelDate() {
		return reserveCancelDate;
	}

	public void setReserveCancelDate(Timestamp reserveCancelDate) {
		this.reserveCancelDate = reserveCancelDate;
	}
	
	
}
