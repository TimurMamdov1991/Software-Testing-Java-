package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase{
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactPage(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getMiddleName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());


    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContact() {
    click(By.linkText("add new"));
  }

  public void updateContactPage() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void createContact(ContactData contactData, boolean b) {
    initContact();
    fillContactPage(contactData, true);
    submitContactCreation();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    alertContact();
  }

  public void modifyContact(ContactData contact) { ;
    fillContactPage(contact,false);
    updateContactPage();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void modifyContactById(int id) {
    wd.findElement(By.xpath("//a[contains(@href,'edit.php?id=" + id + "')]")).click();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String address = row.findElement(By.cssSelector("td:nth-child(4)")).getText();
      String allEmails = row.findElement(By.cssSelector("td:nth-child(5)")).getText();
      String allPhones = row.findElement(By.cssSelector("td:nth-child(6)")).getText();
      contacts.add(new ContactData()
          .withId(id)
          .withFirstName(firstName)
          .withLastName(lastName)
          .withAddress(address)
          .withAllPhones(allPhones)
          .withAllEmails(allEmails));
    }
    return contacts;
  }


  public ContactData infoFormEditForm(ContactData contact) {
    modifyContactById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
        .withId(contact.getId())
        .withFirstName(firstName)
        .withLastName(lastname)
        .withAddress(address)
        .withHomePhone(home)
        .withMobilePhone(mobile)
        .withWorkPhone(work)
        .withEmail(email)
        .withEmail2(email2)
        .withEmail3(email3);
  }

}
