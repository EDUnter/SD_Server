package payload.responses;


import model.User;

import java.util.ArrayList;

public class UsersResponse {
	private ArrayList<User> users;

	public UsersResponse(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
}
