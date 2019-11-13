package payload.responses;

import model.Message;
import model.User;

import java.util.ArrayList;

public class MessagesResponse {
	private ArrayList<User> users;
	private ArrayList<Message> messages;

	public MessagesResponse(ArrayList<User> users, ArrayList<Message> messages) {
		this.users = users;
		this.messages = messages;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
}
