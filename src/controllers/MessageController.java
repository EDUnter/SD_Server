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

public class MessageController {
	private ObjectMapper objectMapper;
	private PrintWriter out;
	private MessageRepository messageRepository;
	private UserRepository userRepository;

	public MessageController(PrintWriter out, MessageRepository messageRepository, UserRepository userRepository, ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		this.out = out;
		this.messageRepository = messageRepository;
		this.userRepository = userRepository;
	}

	public void getAllMessages() {
		ArrayList<User> users = userRepository.getUsers();
		ArrayList<Message> messages = messageRepository.getMessages();
		MessagesResponse messagesResponse = new MessagesResponse(users, messages);

		String response = null;

		try {
			response = objectMapper.writeValueAsString(messagesResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		RootController.sendResponse(out, response);

	}

	public void postMessage(long id, String msg) {
		ArrayList<User> users = userRepository.getUsers();
		User user = userRepository.getUser(id);
		ArrayList<Message> messages = messageRepository.addMessage(user, msg);
		MessagesResponse messagesResponse = new MessagesResponse(users, messages);

		String response = null;

		try {
			response = objectMapper.writeValueAsString(messagesResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		RootController.sendResponse(out, response);

	}

}
