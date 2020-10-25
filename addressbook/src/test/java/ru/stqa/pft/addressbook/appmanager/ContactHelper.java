package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
    //attach(By.name("photo"), contactData.getPhoto());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());


  /*if (contactData.getGroups().size() > 0) {
      Assert.assertTrue(contactData.getGroups().size() == 1);
      new Select(wd.findElement((By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName()));
    }*/
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

  public void modifyContact(ContactData contact) {
    modifyContactById(contact.getId());
    fillContactPage(contact,false);
    updateContactPage();
  }

  public void addTo() {
    wd.findElement(By.xpath("//input[@type='submit']")).click();
  }

  public void takeGroup() {
    wd.findElement(By.xpath("//select[@name='group']")).click();
  }

  public void takeGroupClick() {
    wd.findElement(By.xpath("//select[@name='group']//*[text()='Test111']")).click();
  }

  public void remove() {
    wd.findElement(By.xpath("  //*[@name='remove']")).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void modifyContactById(int id) {
    wd.findElement(By.xpath("//a[contains(@href,'edit.php?id=" + id + "')]")).click();
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
      String address = row.findElement(By.cssSelector("td:nth-child(4)")).getText();
      String allEmails = row.findElement(By.cssSelector("td:nth-child(5)")).getText();
      String allPhones = row.findElement(By.cssSelector("td:nth-child(6)")).getText();
      contactCache.add(new ContactData()
          .withId(id)
          .withFirstName(firstName)
          .withLastName(lastName)
          .withAddress(address)
          .withAllPhones(allPhones)
          .withAllEmails(allEmails));
    }
    return contactCache;
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

  public int count() {
    return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
  }

  public GroupData addContactToGroup(ContactData contact, Groups groupsInDB) {
    for (GroupData groupInDB: groupsInDB){
      if (!contact.getGroups().contains(groupInDB)) {
        addToGroup(contact.getId(), groupInDB.getId());
        return groupInDB;
      }
    }
    return null;
  }

  public void addToGroup(int id, int groupId) {
    gotoHome();
    selectContactById(id);
    click(By.xpath("//select[@name='to_group']//option[@value='"+groupId+"']"));
    click(By.name("add"));
    gotoHome();
  }

  public void gotoHome() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public ContactData deleteContactFromGroup(Contacts contacts, Groups groupsInDB) {
    for (ContactData contact: contacts) {
      for (GroupData group : groupsInDB) {
        if (group.getContacts().contains(contact)){
          deleteFromGroup(contact.getId(), group.getId());
          return contact;
        }
      }
    }
    return null;
  }

  public void deleteFromGroup(int id, int groupID) {
    gotoHome();
    click(By.xpath("//select[@name='group']//option[@value='"+groupID+"']"));
    selectContactById(id);
    click(By.name("remove"));
    gotoHome();
    click(By.xpath("//select[@name='group']//option[@value='']"));
  }
}
