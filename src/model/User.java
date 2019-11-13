package model;

public class User {

  private long id;
  private String nickname;

  public User(String nickname, long id) {
    this.nickname = nickname;
    this.id = id;
  }

  public long getId() {
    return id;
  }


  public String getNickname() {
    return nickname;
  }

}
