package repository;

import model.Message;

import java.util.ArrayList;

public class MessageRepository {
	private ArrayList<Message> messages;

	public MessageRepository() {
		this.messages = new ArrayList<>();
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public ArrayList<Message> addMessage(long id, String msg) {
		Message message = new Message(id, msg);
		getMessages().add(message);

		return getMessages();
	}
}
