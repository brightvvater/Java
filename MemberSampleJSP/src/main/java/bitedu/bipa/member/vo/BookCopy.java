package bitedu.bipa.member.vo;

import java.sql.Date;

public class BookCopy {
	private int bookSeq;
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private Date publishDate;
	private String bookPosition;
	private String bookStaus;
	
	
	public int getBookSeq() {
		return bookSeq;
	}
	public void setBookSeq(int bookSeq) {
		this.bookSeq = bookSeq;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getBookPosition() {
		return bookPosition;
	}
	public void setBookPosition(String bookPosition) {
		this.bookPosition = bookPosition;
	}
	public String getBookStaus() {
		return bookStaus;
	}
	public void setBookStaus(String bookStaus) {
		this.bookStaus = bookStaus;
	}
	@Override
	public String toString() {
		return "BookCopy [bookSeq=" + bookSeq + ", isbn=" + isbn + ", title=" + title + ", author=" + author
				+ ", publisher=" + publisher + ", publishDate=" + publishDate + ", bookPosition=" + bookPosition
				+ ", bookStaus=" + bookStaus + "]";
	}
	
	
	
}
