package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @Test ()
  public void testContactCreation() {
    ContactData contact = new ContactData()
        .withFirstName("Tima")
        .withLastname("Puba")
        .withMail("azino333@mail.ru")
        .withNumber("89111226644")
        .withGroup("Test1");
    Contacts before = app.contact().all();
    app.contact().createContact(contact, true);
    app.goTo().gotoHome();
    Contacts after = app.contact().all();
    assertThat(after.size(),equalTo(before.size() +1));
    assertThat(after, equalTo(
        before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
  }
}
