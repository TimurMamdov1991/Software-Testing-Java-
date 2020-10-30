package qa.java_cource.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;


public class ContactAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Anna").withLastName("Bozsik")
              .withAddress("11 Test street, ABC").withMobilePhone("22-22").withEmail("ab@gmail.com")
              .withAddress2("22/a Test street, XYZ")/*.withGroup("test_1")*/);
      app.goTo().gotoHome();
    }
  }

  @Test
  public void testContactAddresses () {
    app.goTo().gotoHome();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllAddresses(), equalTo(mergeAddresses(contactInfoFromEditForm)));
  }

  private String mergeAddresses (ContactData contact) {
    return Arrays.asList(contact.getAddress(), contact.getAddress2())
            .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
  }
}
