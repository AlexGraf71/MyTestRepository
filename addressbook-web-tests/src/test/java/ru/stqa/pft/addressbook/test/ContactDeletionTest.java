package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withName("Ivan").withInitials("I.I.I.").withLastName("Ivanov").withNik("Vano")
              .withTitle("Title").withCompany("Smartech").withAddress("Тургениевская 67")
              .withHomePhoneNumber("8999999999").withMobilePhoneNumber("99999999009909")
              .withWorkPhoneNumber("8999999999").withEmail("test@mail.ru").withGroup("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void ContactDeletionTest() {
    app.goTo().gotoHome2();
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().gotoHome();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
