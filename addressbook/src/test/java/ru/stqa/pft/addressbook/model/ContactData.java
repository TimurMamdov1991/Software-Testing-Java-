package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String middleName;
  private final String mail;
  private final String number;

  public ContactData(String firstName, String middleName, String mail, String number) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.mail = mail;
    this.number = number;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getMail() {
    return mail;
  }

  public String getNumber() {
    return number;
  }
}
