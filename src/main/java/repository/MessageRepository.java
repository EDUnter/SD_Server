package repository;

import model.Message;
import model.User;

import java.util.ArrayList;

public class MessageRepository {
	private ArrayList<Message> messages;

	public MessageRepository() {
		this.messages = new ArrayList<>();
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public ArrayList<Message> addMessage(User user, String msg) {
		Message message = new Message(user, msg);
		messages.add(message);

		return getMessages();
	}
}
