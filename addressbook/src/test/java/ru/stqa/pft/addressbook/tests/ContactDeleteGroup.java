package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsGroupsExists() {
    if(app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Group name").withHeader("Header").withFooter("Footer"));
    }
  }

  @BeforeMethod
  public void ensurePreconditionsContactExists() {
    app.contact().gotoHome();
    if(app.db().contacts().size() == 0) {
      app.contact().createContact(new ContactData().withFirstName("name").withLastName("gro"), true);
    }
  }


  @Test
  public void testContactDeleteGroup() {

    Groups beforeGroupsInDb = app.db().groups();
    List<AddressInGroupsData> before =app.db().addressInGroups();
    Contacts beforeAllContacts = app.db().contacts();


    if (before.size()==0){
      app.contact().addContactToGroup(app.db().contacts().iterator().next(),beforeGroupsInDb);
      before =app.db().addressInGroups();
    }

    ContactData deletedContact =
        app.contact().deleteContactFromGroup(beforeAllContacts,beforeGroupsInDb);


    List<AddressInGroupsData> after =app.db().addressInGroups();
    Contacts afterAllContacts = app.db().contacts();
    assertThat(after.size(),equalTo(before.size()-1));

    assertThat(afterAllContacts.stream().filter((c)->c.getId()==deletedContact.getId()).findFirst().get().getGroups()
        , equalTo(deletedContact.getGroups()));

  }
}