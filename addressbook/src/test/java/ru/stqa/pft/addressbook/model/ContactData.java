package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private String id;
  private final String firstName;
  private final String lastname;
  private final String mail;
  private final String number;
  private String group;

  public ContactData(String id, String firstName, String lastname, String mail, String number, String group) {
    this.id = id;
    this.firstName = firstName;
    this.lastname = lastname;
    this.mail = mail;
    this.number = number;
    this.group = group;
  }

  public ContactData(String firstName, String lastname, String mail, String number, String group) {
    this.id = null;
    this.firstName = firstName;
    this.lastname = lastname;
    this.mail = mail;
    this.number = number;
    this.group = group;
  }




  public String getFirstName() {
    return firstName;
  }
  public String getMiddleName() {
    return lastname;
  }
  public String getMail() {
    return mail;
  }
  public String getNumber() {
    return number;
  }
  public String getGroup() {
    return group;
  }
  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
        "id='" + id + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastname='" + lastname + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(firstName, that.firstName) &&
        Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastname);
  }

}
