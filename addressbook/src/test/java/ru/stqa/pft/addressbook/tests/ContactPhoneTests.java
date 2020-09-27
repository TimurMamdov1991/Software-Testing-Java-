package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

  @Test
  public void testContactPhones(){
    app.goTo().gotoHome();

    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFormEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

  }

  private String mergeEmails(ContactData contacts) {
    return Arrays.asList(contacts.getEmail(), contacts.getEmail2())
        .stream().filter((s) -> ! s.equals(""))
        .collect(Collectors.joining("\n"));
  }

  private String mergePhones(ContactData contact) {
   return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
       .stream().filter((s) -> ! s.equals(""))
       .map(ContactPhoneTests::cleaned)
       .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
