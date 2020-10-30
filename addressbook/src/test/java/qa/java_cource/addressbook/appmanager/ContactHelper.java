package qa.java_cource.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import qa.java_cource.addressbook.model.ContactData;
import qa.java_cource.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper (WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitNewContactForm() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    //attach(By.name("photo"), contactData.getPhoto());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("address2"), contactData.getAddress2());

    /*if (contactData.getGroups().size() > 0) {
      Assert.assertTrue(contactData.getGroups().size() == 1);
      new Select(wd.findElement((By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName()));
    }*/
  }

  public void gotoEditPage() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initContactDeletion() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeConfirmationWindow() {
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void initContactEditingById(int id) {
    wd.findElement(By.xpath("//a[contains(@href,'edit.php?id=" + id + "')]")).click();
  }

  public void initContactUpdate() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void addToGroup () {
    wd.findElement(By.name("add")).click();
  }

  public void create(ContactData contact) {
    gotoEditPage();
    fillContactForm(contact);
    submitNewContactForm();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactEditingById(contact.getId());
    fillContactForm(contact);
    initContactUpdate();
    contactCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    initContactDeletion();
    closeConfirmationWindow();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactEditingById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address2 = wd.findElement(By.name("address2")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
            .withAddress(address).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress2(address2);
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String allAddresses = row.findElement(By.cssSelector("td:nth-child(4)")).getText();
      String allEmails = row.findElement(By.cssSelector("td:nth-child(5)")).getText();
      String allPhones = row.findElement(By.cssSelector("td:nth-child(6)")).getText();
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllAddresses(allAddresses).withAllPhones(allPhones).withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }
}

