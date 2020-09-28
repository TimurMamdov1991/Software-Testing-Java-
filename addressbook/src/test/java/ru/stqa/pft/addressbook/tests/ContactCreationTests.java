package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @Test ()
  public void testContactCreation() {
    File photo = new File("src\\test\\resources\\2020-01-13_21-38-52.png");
    ContactData contact = new ContactData()
        .withFirstName("Tima")
        .withLastName("Puba")
        .withPhoto(photo)
        .withAddress("azino333@mail.ru")
        .withMobilePhone("24443")
        .withHomePhone("5533333")
        .withWorkPhone("222311")
        .withEmail("Appp")
        .withEmail2("Ypp")
        .withEmail3("Eepp");
    Contacts before = app.contact().all();
    app.contact().createContact(contact, true);
    app.goTo().gotoHome();
    Contacts after = app.contact().all();
    assertThat(after.size(),equalTo(before.size() +1));
    assertThat(after, equalTo(
        before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
  }

  @Test
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src\\test\\resources\\2020-01-13_21-38-52.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}
