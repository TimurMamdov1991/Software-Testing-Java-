package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.ArrayList;
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


  public void createContact(ContactData contactData, boolean b) {
    initContact();
    fillContactPage(contactData, true);
    submitContactCreation();
    returnHomePage();
  }

  public boolean isTheareAContact() {
    return isElementPresent(By.name("selected[]"));
  }


  public List<ContactData> getContactList() {
    List <ContactData> contacts = new ArrayList<ContactData>();
    List <WebElement> elements = wd.findElements(By.name("entry"));

    for(WebElement element : elements) {

      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      String id = element.findElement(By.tagName("input")).getAttribute("value");

      ContactData contactData = new ContactData(id, firstName, lastname,null,null, null);
      contacts.add(contactData);
    }
    return contacts;
  }

  public int countContacts() {
    return  wd.findElements(By.name("selected[]")).size();
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void getContactPage() {
    click(By.linkText("home"));
  }

  public void selectEdit(int i) {
    wd.findElements(By.xpath("(//img[@alt='Edit'])")).get(i).click();
  }
}
