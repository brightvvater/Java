package bitedu.bipa.tiles.vo;

import java.sql.Date;

public class BookCopy {
	
	private int bookSeq;
	private String bookPosition;
	private String bookStatus;
	private String isbn;
	private String title;
	private String author;
	private Date publishDate;
	private String bookImage;
	
	public BookCopy() {}

	public BookCopy(int bookSeq, String bookPosition, String bookStatus, String isbn, String title, String author,
			Date publishDate, String bookImage) {
		super();
		this.bookSeq = bookSeq;
		this.bookPosition = bookPosition;
		this.bookStatus = bookStatus;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.bookImage = bookImage;
	}

	public int getBookSeq() {
		return bookSeq;
	}

	public void setBookSeq(int bookSeq) {
		this.bookSeq = bookSeq;
	}

	public String getBookPosition() {
		return bookPosition;
	}

	public void setBookPosition(String bookPosition) {
		this.bookPosition = bookPosition;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
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

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

	@Override
	public String toString() {
		return "BookCopy [bookSeq=" + bookSeq + ", bookPosition=" + bookPosition + ", bookStatus=" + bookStatus
				+ ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", publishDate=" + publishDate
				+ ", bookImage=" + bookImage + "]";
	}

	
	

}
