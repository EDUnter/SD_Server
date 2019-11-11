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

  public static long getID() {
    return ID;
  }

  public static void setID(long ID) {
    UserRepository.ID = ID;
  }

  public User addUser(String nickname) {

    //Verififca se já existe um user com o mesmo "nickname"
    for(User user: getUsers()) {
      if(user.getNickname().equals(nickname)) {
        return user;
      }
    }

    //Adciciona um "User" com o "nickname" ao chat e atribui-lhe um id
    User user = new User(nickname, getID());
    getUsers().add(user);
    setID(++ID);

    return user;
  }

}
