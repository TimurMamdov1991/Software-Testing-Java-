package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Groups groups = app.db().groups();
    ContactData contactData = before.iterator().next();
    Groups contactGroups = contactData.getGroups();
    int contactGroupSizeBefore = contactGroups.size();

    GroupData addedGroup = app.contact().addContactToGroup(contactData,groups);

    if (addedGroup==null){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test111").withHeader("Header").withFooter("Footer"));
      addedGroup = app.contact().addContactToGroup(contactData,groups);
    }

    Contacts after = app.db().contacts();
    assertThat(after.stream().filter((c)->c.getId()==contactData.getId()).findFirst().get()
        .getGroups().size(),equalTo(contactGroupSizeBefore+1));
    assertThat(after.stream().filter((c)->c.getId()==contactData.getId()).findFirst().get()
        .getGroups(), equalTo(contactData.withAddedGroup(addedGroup).getGroups()));
  }
}
