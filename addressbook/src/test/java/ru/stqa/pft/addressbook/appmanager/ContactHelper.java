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

  public void returnHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactPage(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getMiddleName());
    type(By.name("address"), contactData.getMail());
    type(By.name("mobile"), contactData.getNumber());

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
    click(By.xpath("(//input[@name='update'])"));
  }



  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    alertContact();
    getContactPage();
  }

  public void createContact(ContactData contactData, boolean b) {
    initContact();
    fillContactPage(contactData, true);
    submitContactCreation();
    returnHomePage();
  }

  public void modifyContact(ContactData contact) {
    selectEdit();
    fillContactPage(contact,false);
    updateContactPage();
    getContactPage();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void getContactPage() {
    click(By.linkText("home"));
  }

  public void selectEdit() {
    wd.findElement(By.xpath("(//img[@alt='Edit'])")).click();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List <WebElement> elements = wd.findElements(By.name("entry"));
    for(WebElement element : elements) {
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastname(lastname));
    }
    return contacts;
  }
}
