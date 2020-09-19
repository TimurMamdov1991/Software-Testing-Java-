package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test(enabled = false)
  public void testContactModification(){
    app.getContactHelper().getContactPage();
    if (! app.getContactHelper().isTheareAContact()) {
      app.getContactHelper().createContact(new ContactData("Timon", "Puba", "azino333@mail.ru", "89111226644", "Test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectEdit(before.size() -1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Qe", "qwe", "qwe@mail.ru", "111111111", null);
    app.getContactHelper().fillContactPage( contact,false);
    app.getContactHelper().updateContactPage();
    app.getContactHelper().getContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size() -1);
    before.add(contact);


    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }
}
