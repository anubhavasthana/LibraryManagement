package service;

public interface CallingEndpoints {
	public void loadInitialBooks();

	public void display();

	public String createAccount(String username);

	public void reserveBook(String username, String bookname);

	public void unreserveBook(String username, String bookname);

	public void issueBook(String username, String bookname);

	public void returnBook(String username, String bookname);

	public void checkItemAvailibilityToBeReserved(String username);

	public void checkItemAvailibilityToBeUnReserved(String username);

	public void checkItemAvailibilityToBeIssued(String username);

	public void checkItemAvailibilityToBeUnIssued(String username);

}
