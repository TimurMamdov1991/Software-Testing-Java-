package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private String firstName;
  private String lastname;
  private String mail;
  private String group;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;


  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }
  public ContactData withLastName(String lastname) {
    this.lastname = lastname;
    return this;
  }
  public ContactData withMail(String mail) {
    this.mail = mail;
    return this;
  }
  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }
  public ContactData withMobilePhone(String mobile) {
    this.mobilePhone = mobile;
    return this;
  }
  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }
  public ContactData withId(int id) {
    this.id = id;
    return this;
  }



  public String getFirstName() { return firstName; }
  public String getMiddleName() { return lastname; }
  public String getMail() { return mail; }
  public String getGroup() { return group; }
  public String getHomePhone() { return homePhone; }
  public String getMobilePhone() { return mobilePhone; }
  public String getWorkPhone() { return workPhone; }
  public int getId() { return id; }


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
