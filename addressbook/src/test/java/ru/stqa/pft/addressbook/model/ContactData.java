package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private String firstName;
  private String lastname;
  private String mail;
  private String number;
  private String group;


  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }
  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }
  public ContactData withMail(String mail) {
    this.mail = mail;
    return this;
  }
  public ContactData withNumber(String number) {
    this.number = number;
    return this;
  }
  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  public ContactData withId(int id) {
    this.id = id;
    return this;
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
  public int getId() {
    return id;
  }


  @Override
  public String toString() {
    return "ContactData{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastname='" + lastname + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
        Objects.equals(firstName, that.firstName) &&
        Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastname);
  }
}
