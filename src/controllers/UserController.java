package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import payload.responses.RegisterResponse;
import payload.responses.UsersResponse;
import repository.UserRepository;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class UserController {
  private ObjectMapper objectMapper;
  private UserRepository userRepository;
  private PrintWriter out;

  public UserController(PrintWriter out, UserRepository userRepository) {
    this.objectMapper = new ObjectMapper();
    this.userRepository = userRepository;
    this.out = out;
  }

  private ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  private UserRepository getUserRepository() {
    return userRepository;
  }

  public PrintWriter getOut() {
    return out;
  }

  public void getAllUsers() {
    ArrayList<User> users = getUserRepository().getUsers();
    Map<Long, String> map = RootController.mapUsers(users);
    UsersResponse usersReponse = new UsersResponse(map);

    String response = null;
    try {
      response = getObjectMapper().writeValueAsString(usersReponse);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    RootController.sendResponse(getOut(), response);
  }

  public void postUser(String nickname) {
    User user = getUserRepository().addUser(nickname);
    RegisterResponse registerResponse = new RegisterResponse(user.getId());

    String response = null;

    try {
      response = getObjectMapper().writeValueAsString(registerResponse);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    RootController.sendResponse(getOut(), response);

  }

}
