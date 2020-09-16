package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String middleName;
  private final String mail;
  private final String number;
  private String group;



  public ContactData(String firstName, String middleName, String mail, String number, String group) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.mail = mail;
    this.number = number;
    this.group = group;
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
  public String getGroup() {
    return group;
  }

}
