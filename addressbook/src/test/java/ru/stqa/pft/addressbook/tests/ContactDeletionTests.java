package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletion(){
    if (! app.getContactHelper().isTheareAContact()) {
      app.getContactHelper().createContact(new ContactData("Timon", "Puba", "azino333@mail.ru", "89111226644", "Test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().alertContact();
    app.getSessionHelper().logout();
  }
}
