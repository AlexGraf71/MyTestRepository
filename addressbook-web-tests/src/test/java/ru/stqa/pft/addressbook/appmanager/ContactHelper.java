package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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
    type(By.name("home"), contactData.getHomePhoneNumber());
    type(By.name("mobile"), contactData.getMobilePhoneNumber());
    type(By.name("work"), contactData.getWorkPhoneNumber());
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

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }
  public int count() {
    return wd.findElements(By.name("selected[]")).size();
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

  public ContactData infoFromEditForm(ContactData contact){
    openModifyForm(contact);
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId()).withName(firstName).withInitials(null).withLastName(lastName).withAddress(address)
            .withHomePhoneNumber(home).withMobilePhoneNumber(mobile).withWorkPhoneNumber(work).withEmail(email);

  }

  public void create(ContactData contact) {
    initCreateContact();
    fillContactForm(contact, true);
    submitCreateContact();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initEditContact();
    fillContactForm(contact, false);
    submitEditContact();
  }

  public void openModifyForm(ContactData contact) {
    selectContactById(contact.getId());
    initEditContact();
  }
  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    closeDialog();
  }

  public boolean isThereAContact() {
    return isElementPresent((By.name("selected[]")));
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null){
      return  new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String firstName = element.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
      String lastName = element.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
      String address = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[4]")).getText();
      String email = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[5]")).getText();
      String[] phones = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[6]")).getText().split("\n");
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData()
              .withId(id).withName(firstName).withInitials(null).withLastName(lastName).withAddress(address)
              .withHomePhoneNumber(phones[0]).withMobilePhoneNumber(phones[1]).withWorkPhoneNumber(phones[2]).withEmail(email));

    }
    return new Contacts(contactCache);
  }
}
