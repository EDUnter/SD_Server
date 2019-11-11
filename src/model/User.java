package model;

import java.io.Serializable;

public class User implements Serializable {

  private long id;
  private String nickname;

  public User(String nickname, long id) {
    this.nickname = nickname;
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
}
