package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
          .withName("Ivan").withInitials("I.I.I.").withLastName("Ivanov").withNik("Vano")
          .withTitle("Title").withCompany("Smartech").withAddress("Turgenievskaya 67")
          .withHomePhoneNumber("8999999999").withMobilePhoneNumber("99999999009909")
          .withWorkPhoneNumber("8999999999").withEmail("test@mail.ru"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testEditContact() {
    app.goTo().gotoHome();
    Contacts before = app.db().contacts();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData()
        .withId(modifyContact.getId()).withName("Sergey").withInitials("С.С.").withLastName("Sergeev").withNik("Vano")
        .withTitle("Title").withCompany("Smartech").withHomePhoneNumber("8999999999").withWorkPhoneNumber("8999999999");
    app.contact().modify(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
  }

}
