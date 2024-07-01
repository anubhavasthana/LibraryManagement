package model;

import java.util.List;

public class UserManager extends User {
	List<String> issued;
	List<String> reserved;

	public UserManager(String username, List<String> issued, List<String> reserved) {
		super(username);
		this.issued = issued;
		this.reserved = reserved;
	}

	public List<String> getIssued() {
		return issued;
	}

	public void setIssued(List<String> issued) {
		this.issued = issued;
	}

	public List<String> getReserved() {
		return reserved;
	}

	public void setReserved(List<String> reserved) {
		this.reserved = reserved;
	}

	@Override
	public String toString() {
		return "UserManager [issued=" + issued + ", reserved=" + reserved + "]";
	}

}
