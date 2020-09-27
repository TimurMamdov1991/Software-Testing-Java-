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
    type(By.name("address"), contactData.getMail());
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
      String[] phones = row.findElement(By.cssSelector("td:nth-child(6)")).getText().split("\n");
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
          .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
    }
    return contacts;
  }


  public ContactData infoFormEditForm(ContactData contact) {
    modifyContactById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
        .withId(contact.getId())
        .withFirstName(firstName)
        .withLastName(lastname)
        .withHomePhone(home)
        .withMobilePhone(mobile)
        .withWorkPhone(work);
  }
}
