package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0) {
      app.contact().createContact(new ContactData().withFirstName("Tima").withLastname("Puba").withMail("azino333@mail.ru").withNumber("89111226644").withGroup("Test1"), true);
    }
  }


  @Test()
  public void testContactModification(){
    Contacts before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData().withFirstName("Owasy").withLastname("KpuBo").withMail("azino333@mail.ru").withNumber("89111226644").withGroup(null);
    app.contact().modifyContact(contact);
    Contacts after = app.contact().all();


    assertThat(after.size(),equalTo(before.size()));
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
  }


}
