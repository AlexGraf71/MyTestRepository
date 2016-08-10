package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitCreateContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("middlename"), contactData.getInitials());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNik());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephonenumber());
    type(By.name("mobile"), contactData.getMobilephonenumber());
    type(By.name("work"), contactData.getWorkphonenumber());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse((isElementPresent(By.name("new_group"))));
    }
  }

  public void initCreateContact() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void closeDialog() {
    wd.switchTo().alert().accept();
  }

  public void initEditContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }


  public void submitEditContact() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact) {
    initCreateContact();
    fillContactForm(contact, true);
    submitCreateContact();


  }

  public boolean isThereAContact() {
    return isElementPresent((By.name("selected[]")));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
