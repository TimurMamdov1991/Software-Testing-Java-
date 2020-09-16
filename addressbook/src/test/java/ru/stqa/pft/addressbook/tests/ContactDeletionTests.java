package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;



public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletion(){
    if (! app.getContactHelper().isTheareAContact()) {
      app.getContactHelper().createContact(new ContactData("Timon", "Puba", "azino333@mail.ru", "89111226644", "Test1"), true);
    }
    int before = app.getContactHelper().countContacts();
    app.getContactHelper().selectContact(before -1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().alertContact();
    app.getContactHelper().getContactPage();
    int after = app.getContactHelper().countContacts();
    Assert.assertEquals(after, before -1);
  }
}
