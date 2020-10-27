package ru.stqa.pft.mantis.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserData {
  private int id = Integer.MAX_VALUE;
  private String username;
  private String email;
  private String password;

  private Set<UserData> users = new HashSet<UserData>();

  public Users getUsers() {
    return new Users(users);
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withUserName(String username) {
    this.username = username;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData withPassword(String password) {
    this.password = password;
    return this;
  }

  @Override
  public String toString() {
    return "UserData{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
        Objects.equals(username, userData.username) &&
        Objects.equals(email, userData.email) &&
        Objects.equals(password, userData.password) &&
        Objects.equals(users, userData.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password, users);
  }
}
