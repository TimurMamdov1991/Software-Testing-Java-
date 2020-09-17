package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletion(){
    if (! app.getContactHelper().isTheareAContact()) {
      app.getContactHelper().createContact(new ContactData("Timon", "Puba", "azino333@mail.ru", "89111226644", "Test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().alertContact();
    app.getContactHelper().getContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() -1);

    before.remove(before.size() -1);
    for (int i = 0; i<after.size();i++){
      Assert.assertEquals(before.get(i), after.get(i));
    }
  }
}
