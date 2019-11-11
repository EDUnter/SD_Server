package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Message;
import model.User;
import payload.responses.MessagesResponse;
import repository.MessageRepository;
import repository.UserRepository;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class MessageController {
	private ObjectMapper objectMapper;
	private PrintWriter out;
	private String response;
	private MessageRepository messageRepository;
	private UserRepository userRepository;

	public MessageController(PrintWriter out, MessageRepository messageRepository, UserRepository userRepository) {
		this.objectMapper = new ObjectMapper();
		this.out = out;
		this.response = null;
		this.messageRepository = messageRepository;
		this.userRepository = userRepository;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public PrintWriter getOut() {
		return out;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public MessageRepository getMessageRepository() {
		return messageRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void getAllMessages() {
		ArrayList<User> users = getUserRepository().getUsers();
		ArrayList<Message> messages = getMessageRepository().getMessages();

		Map<Long, String> mapUsers = RootController.mapUsers(users);
		Map<Long, String> mapMessages = RootController.mapMessages(messages);

		MessagesResponse messagesResponse = new MessagesResponse(mapUsers, mapMessages);

		try {
			setResponse(getObjectMapper().writeValueAsString(messagesResponse));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		RootController.sendResponse(out, response);

	}

	public void postMessage(long id, String msg) {
		ArrayList<Message> messages = getMessageRepository().addMessage(id, msg);
		ArrayList<User> users = getUserRepository().getUsers();

		Map<Long, String> mapUsers = RootController.mapUsers(users);
		Map<Long, String> mapMessages = RootController.mapMessages(messages);

		MessagesResponse messagesResponse = new MessagesResponse(mapUsers, mapMessages);

		String response = null;
		try {
			response = getObjectMapper().writeValueAsString(messagesResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		RootController.sendResponse(out, response);
	}

}
