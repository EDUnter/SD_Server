package repository;

public class RootRepository {

  private UserRepository userRepository;
  private MessageRepository messageRepository;

  public RootRepository() {
    this.userRepository = new UserRepository();
    this.messageRepository = new MessageRepository();
  }

  public UserRepository getUserRepository() {
    return userRepository;
  }

  public MessageRepository getMessageRepository() {
    return messageRepository;
  }
}
