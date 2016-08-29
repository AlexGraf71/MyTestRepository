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
    if (contactData.getGroup() != null){
      if (creation) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
        Assert.assertFalse((isElementPresent(By.name("new_group"))));
      }
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

  public void openContactInfo(){
    click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[7]/a/img"));}

  public void submitEditContact() {
    click(By.name("update"));
  }

  public void create(ContactData contact) {
    initCreateContact();
    fillContactForm(contact, true);
    submitCreateContact();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initEditContact();
    fillContactForm(contact, false);
    submitEditContact();
    contactCache = null;
  }

  public void openModifyForm(ContactData contact) {
    selectContactById(contact.getId());
    initEditContact();
    contactCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    closeDialog();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent((By.name("selected[]")));
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

  public  ContactData allFromInfoForm(ContactData contact){
    selectContactById(contact.getId());
    openContactInfo();
    String allInfo = wd.findElement(By.id("content")).getText();
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId()).withAllInfo(allInfo);

  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null){
      return  new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String address = cells.get(3).getText();
      String email = cells.get(4).getText();
      String[] phones =cells.get(5).getText().split("\n");
      contactCache.add(new ContactData()
              .withId(id).withName(firstName).withInitials(null).withLastName(lastName).withAddress(address)
              .withHomePhoneNumber(phones[0]).withMobilePhoneNumber(phones[1]).withWorkPhoneNumber(phones[2]).withEmail(email));

    }
    return new Contacts(contactCache);
  }

  public Contacts incompleteContact() {
    if (contactCache != null){
      return  new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String address = cells.get(3).getText();
      String email = cells.get(4).getText();
      String allPhones =cells.get(5).getText();
      contactCache.add(new ContactData()
              .withId(id).withName(firstName).withLastName(lastName).withAddress(address)
              .withAllPhones(allPhones).withEmail(email));

    }
    return new Contacts(contactCache);
  }
}
