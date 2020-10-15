package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0) {
      app.contact().createContact(new ContactData()
          .withFirstName("Tima")
          .withLastName("Puba")
          .withAddress("azino333@mail.ru")
          .withGroup("Test1")
          .withMobilePhone("24443")
          .withHomePhone("5533333")
          .withWorkPhone("222311"), true);
      app.goTo().gotoHome();
    }
  }


  @Test
  public void testContactModification(){
    Contacts before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    app.contact().modifyContactById(modifyContact.getId());
    ContactData contact = new ContactData()
        .withId(modifyContact.getId())
        .withFirstName("qwe")
        .withLastName("asdq")
        .withAddress("azino333@mail.ru")
        .withMobilePhone("222333")
        .withHomePhone("555333")
        .withWorkPhone("222311")
        .withGroup(null);
    app.contact().modifyContact(contact);
    app.goTo().gotoHome();
    assertThat(app.group().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
  }


}
