package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import payload.responses.RegisterResponse;
import payload.responses.UsersResponse;
import repository.UserRepository;

import java.io.PrintWriter;
import java.util.ArrayList;

public class UserController {
	private ObjectMapper objectMapper;
	private UserRepository userRepository;
	private PrintWriter out;

	public UserController(PrintWriter out, UserRepository userRepository, ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		this.userRepository = userRepository;
		this.out = out;
	}

	public void getAllUsers() {
		ArrayList<User> users = userRepository.getUsers();
		UsersResponse usersResponse = new UsersResponse(users);

		String response = null;
		try {
			response = objectMapper.writeValueAsString(usersResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		RootController.sendResponse(out, response);
	}

	public void postUser(String nickname) {
		User user = userRepository.addUser(nickname);
		RegisterResponse registerResponse = new RegisterResponse(user.getId());

		String response = null;

		try {
			response = objectMapper.writeValueAsString(registerResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		RootController.sendResponse(out, response);

	}
}
