package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import repository.MessageRepository;
import repository.UserRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class RootController {
  private PrintWriter out;
  private ObjectMapper objectMapper;
  private MessageController messageController;
  private ServerController serverController;
  private UserController userController;

  public RootController(Socket socket, UserRepository userRepository, MessageRepository messageRepository) {
    try {
      this.out = new PrintWriter(socket.getOutputStream());
    } catch (IOException e) {
      System.out.println("Erro na execução do servidor: " + e);
      System.exit(1);
    }

    this.objectMapper = new ObjectMapper();
    this.messageController = new MessageController(out, messageRepository, userRepository, objectMapper);
    this.serverController = new ServerController(out, objectMapper);
    this.userController = new UserController(out, userRepository, objectMapper);
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

}
