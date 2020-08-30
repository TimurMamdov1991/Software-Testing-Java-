package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.initContact();
    app.fillContactPage(new ContactData("Timon", "Puba", "azino333@mail.ru", "89111226644"));
    app.submitContactCreation();
    app.returnHomePage();
  }

}
