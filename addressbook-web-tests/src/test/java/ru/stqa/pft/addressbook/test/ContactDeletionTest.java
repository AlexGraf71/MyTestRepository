package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "I.I.I.", "Ivanov", "Vano", "Title", "Smartech", "Moskow",
              "8999999999", "8999999999", "8777777777", "707070", "test@mail.ru", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
  }

  @Test
  public void ContactDeletionTest() {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().closeDialog();
    app.getNavigationHelper().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
