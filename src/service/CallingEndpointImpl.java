package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.BookItem;
import model.BookStatus;
import model.UserManager;

public class CallingEndpointImpl implements CallingEndpoints {

	Map<String, UserManager> hm = new HashMap<>();
	List<BookItem> lb = new ArrayList<>();
	List<String> bookNames = new ArrayList<>();
	List<String> reservedBooks = new ArrayList<>();

	@Override
	public void loadInitialBooks() {
		lb.add(new BookItem(Messages.getString("CallingEndpointImpl.BOOK1"), BookStatus.AVAILABLE)); //$NON-NLS-1$
		lb.add(new BookItem(Messages.getString("CallingEndpointImpl.BOOK2"), BookStatus.AVAILABLE)); //$NON-NLS-1$
		lb.add(new BookItem(Messages.getString("CallingEndpointImpl.BOOK3"), BookStatus.AVAILABLE)); //$NON-NLS-1$

		bookNames.add(Messages.getString("CallingEndpointImpl.BOOK1")); //$NON-NLS-1$
		bookNames.add(Messages.getString("CallingEndpointImpl.BOOK2")); //$NON-NLS-1$
		bookNames.add(Messages.getString("CallingEndpointImpl.BOOK3")); //$NON-NLS-1$
	}

	@Override
	public void display() {
		System.out.println(Messages.getString("CallingEndpointImpl.MENU")); //$NON-NLS-1$

	}

	@Override
	public String createAccount(String username) {

		if (!hm.containsKey(username)) {
			UserManager userManager = new UserManager(username, new ArrayList<String>(), new ArrayList<String>());
			hm.put(username, userManager);
			System.out.println(Messages.getString("CallingEndpointImpl.USER_CREATED")); //$NON-NLS-1$
		} else {
			System.out.println(Messages.getString("CallingEndpointImpl.USER_EXISTS") + username); //$NON-NLS-1$
		}
		return username;
	}

	@Override
	public void checkItemAvailibilityToBeReserved(String username) {
		System.out.println(Messages.getString("CallingEndpointImpl.AVAILABLE_BOOKS_TO_BE_RESERVED")); //$NON-NLS-1$
		// Printing available books
		Iterator<BookItem> iterator = lb.iterator();
		while (iterator.hasNext()) {
			BookItem bookItem = iterator.next();

			if (bookItem.getBookStatus().equals(BookStatus.AVAILABLE)) {
				System.out.println(bookItem.getBookName());
			}
		}

	}

	@Override
	public void reserveBook(String username, String bname) {
		if (bookNames.contains(bname)) {
			bookNames.remove(bname);
			Iterator<BookItem> iterator1 = lb.iterator();
			while (iterator1.hasNext()) {
				BookItem book = iterator1.next();
				if (book.getBookName().equals(bname)) {
					book.setBookStatus(BookStatus.RESERVED);
					List<String> booksIssuedToUser = hm.get(username).getReserved();
					booksIssuedToUser.add(bname);
					System.out.println(Messages.getString("CallingEndpointImpl.BOOK_LIBRARY_UPDATED")); //$NON-NLS-1$
					reservedBooks.add(bname);
					break;
				}
			}

		} else {
			System.out.println(Messages.getString("CallingEndpointImpl.BOOK_NOT_FOUND")); //$NON-NLS-1$
		}
	}

	@Override
	public void unreserveBook(String username, String bookname) {
		List<String> booksIssuedToUser = hm.get(username).getReserved();
		if (booksIssuedToUser.contains(bookname)) {
			booksIssuedToUser.remove(bookname);
			bookNames.add(bookname);
			reservedBooks.remove(bookname);
			if (bookNames.contains(bookname)) {
				bookNames.remove(bookname);
				Iterator<BookItem> iterator11 = lb.iterator();
				while (iterator11.hasNext()) {
					BookItem books = iterator11.next();
					if (books.getBookName().equals(bookname)) {
						books.setBookStatus(BookStatus.AVAILABLE);
						break;
					}
				}
			}
			System.out.println(Messages.getString("CallingEndpointImpl.BOOK_UNRESERVED"));
		} else {
			System.out.println(Messages.getString("CallingEndpointImpl.WRONG_ENTRY")); //$NON-NLS-1$
		}
	}

	@Override
	public void checkItemAvailibilityToBeUnReserved(String username) {
		List<String> booksIssuedToUser = hm.get(username).getReserved();
		if (!booksIssuedToUser.isEmpty()) {
			Iterator<String> iterator1 = booksIssuedToUser.iterator();
			while (iterator1.hasNext()) {
				System.out.println(iterator1.next());
			}
		}
	}

	@Override
	public void checkItemAvailibilityToBeIssued(String username) {
		System.out.println(Messages.getString("CallingEndpointImpl.AVAILABLE_BOOKS_TO_BE_ISSUED")); //$NON-NLS-1$
		// Printing available books
		Iterator<BookItem> iterator = lb.iterator();
		while (iterator.hasNext()) {
			BookItem bookItem = iterator.next();

			if (bookItem.getBookStatus().equals(BookStatus.AVAILABLE)) {
				System.out.println(bookItem.getBookName());
			}
			if (bookItem.getBookStatus().equals(BookStatus.RESERVED)
					&& hm.get(username).getReserved().contains(bookItem.getBookName())) {
				System.out.println(bookItem.getBookName());
			}
		}
	}

	@Override
	public void issueBook(String username, String bname) {
		if (bookNames.contains(bname)) {
			bookNames.remove(bname);
			Iterator<BookItem> iterator1 = lb.iterator();
			while (iterator1.hasNext()) {
				BookItem book = iterator1.next();
				if (book.getBookName().equals(bname)) {
					book.setBookStatus(BookStatus.ISSUED);
					List<String> booksIssuedToUser = hm.get(username).getIssued();
					booksIssuedToUser.add(bname);
					print();
				}
			}
			
		}
		UserManager um = hm.get(username);
		List<String> reservedBooksToUser = um.getReserved();
		List<String> issuedBooks = um.getIssued();
		if (reservedBooksToUser.contains(bname)) {
			reservedBooksToUser.remove(bname);
			issuedBooks.add(bname);
			reservedBooks.remove(bname);
			print();
		}
		
	}

	@Override
	public void checkItemAvailibilityToBeUnIssued(String username) {
		List<String> booksIssuedToUser = hm.get(username).getIssued();
		Iterator<String> iterator1 = booksIssuedToUser.iterator();
		while (iterator1.hasNext()) {
			System.out.println(iterator1.next());
		}
	}

	@Override
	public void returnBook(String username, String bookname) {
		List<String> booksIssuedToUser = hm.get(username).getIssued();
		if (booksIssuedToUser.contains(bookname)) {
			System.out.println("ok1");
			booksIssuedToUser.remove(bookname);
			bookNames.add(bookname);
			if (bookNames.contains(bookname)) {
				bookNames.remove(bookname);
				Iterator<BookItem> iterator11 = lb.iterator();
				while (iterator11.hasNext()) {
					BookItem books = iterator11.next();
					if (books.getBookName().equals(bookname)) {
						books.setBookStatus(BookStatus.AVAILABLE);
						System.out.println("ok2");
					}
				}
			}

		} else {
			System.out.println(Messages.getString("CallingEndpointImpl.WRONG_ENTRY")); //$NON-NLS-1$
		}
	}
	void print() {
		System.out.println(Messages.getString("CallingEndpointImpl.BOOK_LIBRARY_UPDATED")); 
		System.out.println(Messages.getString("CallingEndpointImpl.USERCLASS_UPDATED")); 
	}
}