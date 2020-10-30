package qa.java_cource.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;


public class ContactEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Anna").withLastName("Bozsik")
              .withAddress("11 Test street, ABC").withMobilePhone("22-22").withEmail("ab@gmail.com")
              .withEmail2("yy_uu@gmail.com").withEmail3("rr@gmail.com")/*.withGroup("test_1")*/);
      app.goTo().gotoHome();
    }
  }

  @Test
  public void testContactEmails () {
    app.goTo().gotoHome();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails (ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
  }
}
