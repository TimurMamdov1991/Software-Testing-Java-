package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactMidification(){
    app.getContactHelper().selectEdit();
    app.getContactHelper().fillContactPage(new ContactData("qwe", "qwe", "qwe@mail.ru", "111111111", null), false);
    app.getContactHelper().updateContactPage();
    app.getSessionHelper().logout();
  }
}
