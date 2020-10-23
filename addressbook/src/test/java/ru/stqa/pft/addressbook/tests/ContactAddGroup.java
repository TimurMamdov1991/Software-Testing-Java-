package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactAddGroup extends TestBase {





  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test111"));
    }
    if (app.db().contacts().size() == 0) {
      app.contact().createContact(new ContactData().withFirstName("Tima"), true);
    }
  }


  @Test
  public void testContactAddGroup() {
    Contacts before = app.db().contacts();
    ContactData contactData = before.iterator().next();
    app.goTo().gotoHome();
    app.contact().contAddGroup(contactData);
    app.goTo().gotoHome();

    System.out.println(contactData);
    System.out.println(contactData.getGroups());

  }
}
