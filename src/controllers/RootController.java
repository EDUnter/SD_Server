package controllers;

import model.Message;
import model.User;
import repository.MessageRepository;
import repository.UserRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RootController {
  private MessageController messageController;
  private ServerController serverController;
  private UserController userController;
  private PrintWriter out;

  public RootController(Socket socket, UserRepository userRepository, MessageRepository messageRepository) {
    try {
      this.out = new PrintWriter(socket.getOutputStream());
    } catch (IOException e) {
      System.out.println("Erro na execução do servidor: " + e);
      System.exit(1);
    }

    this.messageController = new MessageController(this.out, messageRepository, userRepository);
    this.serverController = new ServerController(this.out);
    this.userController = new UserController(this.out, userRepository);
  }

  public MessageController getMessageController() {
    return messageController;
  }

  public ServerController getServerController() {
    return serverController;
  }

  public UserController getUserController() {
    return userController;
  }

  public static void sendResponse(PrintWriter out, String response) {
    out.println("HTTP/1.1 200 OK");
    out.println("Content-type: application/json");
    out.println("Content-Length: " + response.length());
    out.write("\r\n");
    out.println(response);
    out.flush();
    out.close();
  }

  public static Map<Long, String> mapUsers(ArrayList<User> users) {
    Map<Long, String> map = new HashMap<>();

    for(User user: users) {
      map.put(user.getId(), user.getNickname());
    }

    return map;
  }


  public static Map<Long, String> mapMessages(ArrayList<Message> messages) {
    Map<Long, String> map = new HashMap<>();

    for(Message message: messages) {
      map.put(message.getId(), message.getMessage());
    }

    return map;
  }

}
