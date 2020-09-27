package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0) {
      app.contact().createContact(new ContactData()
          .withFirstName("Tima")
          .withLastName("Puba")
          .withMail("azino333@mail.ru")
          .withGroup("Test1")
          .withMobilePhone("24443")
          .withHomePhone("5533333")
          .withWorkPhone("222311"), true);
      app.goTo().gotoHome();
    }
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.contact().all();
    ContactData deleteContact = before.iterator().next();
    app.contact().delete(deleteContact);
    app.goTo().gotoHome();
    Contacts after = app.contact().all();
    assertThat(after.size(),equalTo(before.size() -1));
    assertThat(after, equalTo(before.withOut(deleteContact)));
  }

}
