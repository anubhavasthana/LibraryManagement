package model;

public class BookItem extends Book {
	BookStatus bookStatus;

	public BookItem(String bookName, BookStatus available) {
		super(bookName);
		this.bookStatus = available;
	}

	public BookStatus getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(BookStatus bookStatus) {
		this.bookStatus = bookStatus;
	}

	@Override
	public String toString() {
		return "BookItem [bookStatus=" + bookStatus + "]";
	}

}
