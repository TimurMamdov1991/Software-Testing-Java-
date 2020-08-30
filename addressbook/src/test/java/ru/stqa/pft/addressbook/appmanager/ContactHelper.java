package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

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

  public void fillContactPage(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("address"), contactData.getMail());
    type(By.name("mobile"), contactData.getNumber());
  }

  public void initContact() {
    click(By.linkText("add new"));
  }

  public void selectEdit() {
    click(By.xpath("(//img[@alt='Edit'])[2]"));
  }

  public void updateContactPage() {
    click(By.xpath("(//input[@name='update'])"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deletContact() {
    click(By.xpath("//input[@value='Delete']"));
  }


}
