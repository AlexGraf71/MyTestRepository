package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withLastname("Ivan").withInitials("I.I.I.").withLastname("Ivanov").withNik("Vano")
              .withTitle("Title").withCompany("Smartech").withHomePhoneNumber("8999999999").withWorkPhoneNumber("8999999999").withGroup("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testEditContact() {

    Contacts before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifyContact.getId()).withLastname("Сергей").withInitials("С.С.").withLastname("Сергеев").withNik("Vano")
            .withTitle("Title").withCompany("Smartech").withHomePhoneNumber("8999999999").withWorkPhoneNumber("8999999999").withGroup("test2");
    app.contact().modify(modifyContact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    equalTo(before.without(modifyContact).withAdded(contact));
  }

}
