package repository;

import model.User;

import java.util.ArrayList;

public class UserRepository {
  private static long ID = 1;
  private ArrayList<User> users;

  public UserRepository() {
    this.users = new ArrayList();
  }

  public ArrayList<User> getUsers() {
    return users;
  }

  public User getUser(long id) {
    for(User user: users) {
      if(user.getId() == id) {
        return user;
      }
    }

    return null;
  }

  public synchronized User addUser(String nickname) {
    //Adciciona um "User" com o "nickname" ao chat e atribui-lhe um id
    User user = new User(nickname, ID);
    users.add(user);
    ID++;

    return user;
  }

}
