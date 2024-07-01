import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import service.CallingEndpointImpl;
import service.CallingEndpoints;

public class MainClass {
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		CallingEndpoints ce = new CallingEndpointImpl();
		ce.loadInitialBooks();
		
		String username = null;
		int flag = 1;
		while (flag == 1) {
			ce.display();
			try {
			int choice = Integer.parseInt(br.readLine());
			switch (choice) {
			case 1:
				System.out.println("Enter Username");
				username = ce.createAccount(br.readLine());
				break;
			case 2:
				ce.checkItemAvailibilityToBeReserved(username);
				System.out.println("Enter bookname");
				ce.reserveBook(username, br.readLine());
				break;
			case 3:
				ce.checkItemAvailibilityToBeUnReserved(username);
				System.out.println("Enter bookname");
				ce.unreserveBook(username, br.readLine());
				break;
			case 4:
				ce.checkItemAvailibilityToBeIssued(username);
				System.out.println("Enter bookname");
				ce.issueBook(username, br.readLine());
				break;
			case 5:
				ce.checkItemAvailibilityToBeUnIssued(username);
				System.out.println("Enter bookname");
				ce.returnBook(username, br.readLine());
				break;
			case 6:
				ce.checkItemAvailibilityToBeReserved(username);
				break;
			default:
				flag = 0;
				break;
			}
			}
			catch(NumberFormatException e) {
				System.out.println("Incorrect entry to switch menu");
			}
		}

	}
}